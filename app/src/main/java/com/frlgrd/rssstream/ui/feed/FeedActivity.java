package com.frlgrd.rssstream.ui.feed;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.frlgrd.rssstream.R;
import com.frlgrd.rssstream.core.FeedManager;
import com.frlgrd.rssstream.core.model.FeedItem;
import com.frlgrd.rssstream.ui.common.ToolbarActivity;
import com.frlgrd.rssstream.ui.detail.ArticleDetailActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import rx.android.schedulers.AndroidSchedulers;

@EActivity(value = R.layout.feed_activity)
public class FeedActivity extends ToolbarActivity implements SwipeRefreshLayout.OnRefreshListener, FeedAdapter.OnItemClickedListener {

	@Bean
	FeedManager feedManager;
	@Bean
	FeedAdapter feedAdapter;

	@ViewById
	SwipeRefreshLayout swipeRefreshLayout;
	@ViewById
	RecyclerView recyclerView;
	@ViewById
	View emptyView;

	@AfterViews
	void afterViews() {
		setTitle(getString(R.string.feed_title));

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(feedAdapter);

		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

		feedAdapter.setOnItemClickedListener(this);

		swipeRefreshLayout.setRefreshing(true);

		loadData();
	}

	private void loadData() {
		emptyView.setVisibility(View.GONE);
		feedManager.retrieveFeedItems()
				.compose(bindToLifeCycle())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe(() -> swipeRefreshLayout.setRefreshing(true))
				.doOnTerminate(() -> swipeRefreshLayout.setRefreshing(false))
				.subscribe(feedItems -> {
					feedAdapter.replaceAll(feedItems);
					emptyView.setVisibility(feedItems.isEmpty() ? View.VISIBLE : View.GONE);
				}, throwable -> {
					emptyView.setVisibility(recyclerView.getAdapter().getItemCount() == 0 ? View.VISIBLE : View.GONE);
					Snackbar.make(toolbar, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
				});
	}

	@Override
	public void onRefresh() {
		loadData();
	}

	@Override
	public void onItemClicked(FeedItem item) {
		ArticleDetailActivity_.intent(this).article(item).start();
	}
}
