package com.frlgrd.rssstream.ui.detail;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.frlgrd.rssstream.R;
import com.frlgrd.rssstream.core.model.FeedItem;
import com.frlgrd.rssstream.ui.common.ToolbarActivity;
import com.frlgrd.rssstream.utils.DateUtils;
import com.frlgrd.rssstream.utils.ImageUtils;
import com.frlgrd.rssstream.utils.IntentUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(value = R.layout.article_detail_activity)
public class ArticleDetailActivity extends ToolbarActivity {

	@Extra
	FeedItem article;

	@ViewById
	ImageView headerImage;
	@ViewById
	TextView title, date, description, content;
	@ViewById
	CollapsingToolbarLayout collapsingToolbar;

	@AfterViews
	void afterViews() {
		canGoBack();
		if (article == null) {
			return;
		}

		ImageUtils.loadImage(this, headerImage, article.getLargeThumbnail());
		title.setText(article.getTitle());
		date.setText(DateUtils.toMediumDate(article.getPubDate()));
		description.setText(article.getDescription());
		content.setText(Html.fromHtml(article.getContent()));

		collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
	}

	@Click
	void fabClicked() {
		IntentUtils.share(this, article);
	}
}
