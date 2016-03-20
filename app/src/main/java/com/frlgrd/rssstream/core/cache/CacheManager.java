package com.frlgrd.rssstream.core.cache;

import android.content.Context;

import com.frlgrd.rssstream.core.Logger;
import com.frlgrd.rssstream.core.cache.serializer.JodaPeriodTypeSerializer;
import com.snappydb.DB;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.joda.time.DateTime;
import org.joda.time.PeriodType;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import de.javakaffee.kryoserializers.jodatime.JodaDateTimeSerializer;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

@EBean(scope = EBean.Scope.Singleton)
public class CacheManager {

	@RootContext
	Context context;
	private DB db;

	public synchronized DB getDb() {
		if (db == null) {
			try {
				String path = context.getFilesDir().getAbsolutePath() + File.separator + "snappydb";

				db = new SnappyDB.Builder(context)
						.directory(path)
						.registerSerializers(DateTime.class, new JodaDateTimeSerializer())
						.registerSerializers(PeriodType.class, new JodaPeriodTypeSerializer())
						.build();
			} catch (SnappydbException e) {
				Logger.error("Can't open cache database. No data will be cached", e);
			}
		}
		return db;
	}

	/**
	 * Handle cache for the requested data. The asyncObservable should return a list of data if needed
	 *
	 * @param key             cache data identifier
	 * @param strategy        cache policy
	 * @param asyncObservable network stream
	 * @param <T>             Data type
	 */
	public <T> Observable<T> executeRx(final String key, final CacheStrategy strategy, final Observable<T> asyncObservable) {
		return loadData(key, strategy,
				asyncObservable.compose(observable -> observable.doOnNext(value -> {
					if (value != null) {
						put(key, new CacheWrapper<>(new Date(), value));
					}
				}))
		).subscribeOn(Schedulers.io());
	}

	/**
	 * @param key             cache data identifier
	 * @param strategy        cache policy
	 * @param asyncObservable network stream
	 * @param <T>             Data type
	 */
	private <T> Observable<T> loadData(final String key, final CacheStrategy strategy, final Observable<T> asyncObservable) {
		Observable<CacheWrapper<T>> cacheObservable = Observable.create(new Observable.OnSubscribe<CacheWrapper<T>>() {
			@Override
			@SuppressWarnings("unchecked")
			public void call(Subscriber<? super CacheWrapper<T>> subscriber) {
				try {
					CacheWrapper<T> cachedData = get(key, CacheWrapper.class);
					if (cachedData != null) {
						subscriber.onNext(cachedData);
					}
					subscriber.onCompleted();
				} catch (Exception e) {
					subscriber.onError(e);
				}
			}
		});

		switch (strategy) {
			case CACHE_THEN_ASYNC:
				return cacheObservable
						.map(CacheWrapper::getData)
						.concatWith(asyncObservable);
			case JUST_CACHE:
				return cacheObservable
						.map(CacheWrapper::getData);
			case NO_CACHE:
				return asyncObservable;
			case NO_CACHE_BUT_SAVED:
				return asyncObservable.onErrorResumeNext(cacheObservable.map(CacheWrapper::getData));
		}
		return asyncObservable;
	}

	public synchronized void put(String key, Serializable value) {
		try {
			getDb().put(key, value);
		} catch (SnappydbException e) {
			Logger.warn("Data with key %s couldn't be put in cache", e, key);
		}
	}

	public synchronized <T extends Serializable> T get(String key, Class<T> clazz) {
		try {
			if (getDb().exists(key)) {
				return getDb().get(key, clazz);
			} else {
				return null;
			}
		} catch (SnappydbException e) {
			Logger.warn("Data with key %s couldn't be retrieved from cache. Deleting it", e, key);
			del(key);
		}
		return null;
	}

	public synchronized void del(String key) {
		try {
			getDb().del(key);
		} catch (SnappydbException e) {
			Logger.warn("Data with key %s couldn't be deleted from cache", e, key);
		}
	}
}
