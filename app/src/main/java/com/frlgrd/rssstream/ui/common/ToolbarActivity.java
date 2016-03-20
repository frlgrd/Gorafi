package com.frlgrd.rssstream.ui.common;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.frlgrd.rssstream.R;
import com.frlgrd.rssstream.core.Logger;

import org.androidannotations.annotations.EActivity;

@EActivity
public abstract class ToolbarActivity extends BaseActivity {

	protected Toolbar toolbar;
	private boolean canGoBack = false;


	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initToolBar();
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		initToolBar();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				if (canGoBack) onBackPressed();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void canGoBack() {
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayShowHomeEnabled(true);
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			canGoBack = true;
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		toolbar.setTitle(title);
	}

	private void initToolBar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar == null) {
			Logger.error("Toolbar can't be null id you inherit " + ToolbarActivity.class.getSimpleName());
		}

		setSupportActionBar(toolbar);
	}

}

