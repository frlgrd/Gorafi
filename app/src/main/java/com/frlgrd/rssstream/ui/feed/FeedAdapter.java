package com.frlgrd.rssstream.ui.feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frlgrd.rssstream.R;
import com.frlgrd.rssstream.core.model.FeedItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.Holder> {

	@RootContext
	Context context;
	private List<FeedItem> items;

	private LayoutInflater inflater;

	private OnItemClickedListener onItemClickedListener;

	public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
		this.onItemClickedListener = onItemClickedListener;
	}

	@AfterViews
	void afterViews() {
		inflater = LayoutInflater.from(context);
		items = new ArrayList<>();
	}

	public void replaceAll(List<FeedItem> items) {
		this.items.clear();
		this.items.addAll(items);
		notifyDataSetChanged();
	}

	@Override
	public FeedAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.feed_item_layout, parent, false);
		return new Holder(view);
	}

	@Override
	public void onBindViewHolder(FeedAdapter.Holder holder, int position) {
		holder.setData(items.get(position));
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public interface OnItemClickedListener {
		void onItemClicked(FeedItem item);
	}

	public class Holder extends RecyclerView.ViewHolder {

		private FeedItemView feedItemView;

		public Holder(View itemView) {
			super(itemView);
			feedItemView = (FeedItemView) itemView.findViewById(R.id.feedItemView);
		}

		public void setData(FeedItem data) {
			feedItemView.setFeedItem(data);
			feedItemView.setOnClickListener(v -> {
				if (onItemClickedListener != null) {
					onItemClickedListener.onItemClicked(data);
				}
			});
		}
	}
}
