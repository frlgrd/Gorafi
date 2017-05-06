package com.frlgrd.rssstream;

import android.app.Application;

import org.androidannotations.annotations.EApplication;

import timber.log.Timber;

@EApplication
public class RssStreamApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Timber.plant(new Timber.DebugTree());
	}
}
