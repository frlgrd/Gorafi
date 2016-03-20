package com.frlgrd.rssstream.core.rest;

import android.content.Context;

import com.frlgrd.rssstream.BuildConfig;
import com.frlgrd.rssstream.R;
import com.frlgrd.rssstream.core.Logger;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

@EBean
public class CustomErrorHandler implements ErrorHandler {

	@RootContext
	Context context;

	@Override
	public Throwable handleError(RetrofitError cause) {
		String errorDescription;

		if (cause.getKind() == RetrofitError.Kind.NETWORK) {
			errorDescription = context.getString(R.string.error_network);
		} else {
			if (cause.getResponse() == null) {
				errorDescription = context.getString(R.string.error_no_response, BuildConfig.ROOT_URL);
			} else {

				try {
					ErrorResponse errorResponse = (ErrorResponse) cause.getBodyAs(ErrorResponse.class);
					errorDescription = errorResponse.error.data.message;
				} catch (Exception ex) {
					try {
						errorDescription = context.getString(R.string.error_network_http_error, cause.getResponse().getStatus());
					} catch (Exception ex2) {
						Logger.error("Error occurred", ex2.getLocalizedMessage());
						errorDescription = context.getString(R.string.error_unknown);
					}
				}
			}
		}
		return new Exception(errorDescription);
	}
}