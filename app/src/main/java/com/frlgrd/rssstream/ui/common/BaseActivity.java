package com.frlgrd.rssstream.ui.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.RxLifecycle;

import org.androidannotations.annotations.EActivity;

import rx.Observable;
import rx.subjects.BehaviorSubject;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {
	private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

	public final <T> Observable.Transformer<T, T> bindToLifeCycle() {
		return RxLifecycle.bindActivity(lifecycleSubject);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lifecycleSubject.onNext(ActivityEvent.CREATE);
	}

	@Override
	protected void onStop() {
		lifecycleSubject.onNext(ActivityEvent.STOP);
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		lifecycleSubject.onNext(ActivityEvent.DESTROY);
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		lifecycleSubject.onNext(ActivityEvent.PAUSE);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		lifecycleSubject.onNext(ActivityEvent.RESUME);
	}

	@Override
	protected void onStart() {
		super.onStart();
		lifecycleSubject.onNext(ActivityEvent.START);
	}
}
