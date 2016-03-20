package com.frlgrd.rssstream.core.rest;

import com.frlgrd.rssstream.core.rest.dto.RssStreamDTO;

import retrofit.http.GET;
import rx.Observable;

public interface RestClient {

	@GET("/feed/")
	Observable<RssStreamDTO> getFeed();
}
