package com.frlgrd.rssstream.ui.feed;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frlgrd.rssstream.R;
import com.frlgrd.rssstream.core.model.FeedItem;
import com.frlgrd.rssstream.utils.ImageUtils;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(value = R.layout.feed_item_view)
public class FeedItemView extends RelativeLayout {

	@ViewById
	ImageView image;
	@ViewById
	TextView title, description;

	public FeedItemView(Context context) {
		super(context);
	}

	public FeedItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FeedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setFeedItem(@Nullable FeedItem feedItem) {
		if (feedItem == null) {
			return;
		}

		ImageUtils.loadImage(getContext(), image, feedItem.getMediumThumbnail());
		title.setText(feedItem.getTitle());
		description.setText(feedItem.getDescription());
	}
}
