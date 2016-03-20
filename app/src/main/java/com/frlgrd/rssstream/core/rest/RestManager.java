package com.frlgrd.rssstream.core.rest;

import com.frlgrd.rssstream.BuildConfig;
import com.frlgrd.rssstream.core.rest.dto.RssStreamDTO;
import com.squareup.okhttp.OkHttpClient;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.SimpleXMLConverter;
import rx.Observable;

@EBean
public class RestManager {

	@Bean
	CustomErrorHandler customErrorHandler;

	private RestClient restClient;


	/**
	 * Build a Rest adapter with Simple XML parser {@link SimpleXMLConverter}
	 */
	@AfterInject
	void afterInject() {
		RestAdapter.Builder builder = new RestAdapter.Builder()
				.setClient(new OkClient(new OkHttpClient()))
				.setConverter(new SimpleXMLConverter())
				.setEndpoint(BuildConfig.ROOT_URL)
				.setErrorHandler(customErrorHandler)
				.setLogLevel(RestAdapter.LogLevel.BASIC);

		restClient = builder.build().create(RestClient.class);
	}

	/**
	 * @return Observable stream of gorafi feed
	 */
	public Observable<RssStreamDTO> getFeed() {
		return restClient.getFeed();
	}
}
