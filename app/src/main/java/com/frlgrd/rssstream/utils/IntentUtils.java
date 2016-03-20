package com.frlgrd.rssstream.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.frlgrd.rssstream.R;
import com.frlgrd.rssstream.core.model.FeedItem;

public class IntentUtils {

	public static void share(@NonNull Context context, @Nullable FeedItem item) {
		if (item == null) {
			return;
		}

		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, item.getLink());
		sendIntent.setType("text/plain");
		context.startActivity(Intent.createChooser(sendIntent, context.getResources().getText(R.string.article_detail_share)));
	}
}
