package com.frlgrd.rssstream.core;

import android.content.Context;

import com.frlgrd.rssstream.core.cache.CacheManager;
import com.frlgrd.rssstream.core.cache.CacheStrategy;
import com.frlgrd.rssstream.core.model.FeedItem;
import com.frlgrd.rssstream.core.rest.RestManager;
import com.frlgrd.rssstream.core.rest.dto.RssStreamDTO;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import rx.Observable;

@EBean
public class FeedManager {

	@RootContext
	Context context;

	@Bean
	RestManager restManager;

	@Bean
	CacheManager cacheManager;

	public Observable<List<FeedItem>> retrieveFeedItems() {
		return cacheManager.executeRx("feed", CacheStrategy.CACHE_THEN_ASYNC,
				restManager.getFeed()
						.map(RssStreamDTO::toFeedList)
		);
	}
}
