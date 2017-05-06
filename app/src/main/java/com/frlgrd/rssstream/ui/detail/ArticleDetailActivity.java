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
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(value = R.layout.article_detail_activity)
@OptionsMenu(R.menu.share_menu)
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
		toolbar.setTitle(article.getTitle());
		date.setText(DateUtils.toMediumDate(article.getPubDate()));
		description.setText(article.getDescription());
		//noinspection deprecation
		content.setText(Html.fromHtml(article.getContent()));
		collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
	}

	@OptionsItem
	void share() {
		IntentUtils.share(this, article);
	}
}
