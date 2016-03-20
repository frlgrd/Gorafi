package com.frlgrd.rssstream.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.frlgrd.rssstream.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageUtils {

	/**
	 * Load image from url with {@link Picasso}
	 *
	 * @param context associated context
	 * @param target  ImageView
	 * @param url     source of content. If url is null then target will display default placeholder
	 */
	public static void loadImage(@NonNull Context context, @NonNull ImageView target, @Nullable String url) {
		if (!TextUtils.isEmpty(url)) {
			picasso(context)
					.load(url)
					.placeholder(R.drawable.thumbnail_placeholder)
					.into(target, new Callback() {
						@Override
						public void onSuccess() {

						}

						@Override
						public void onError() {
							picasso(context).invalidate(url);
						}
					});
		} else {
			target.setImageResource(R.drawable.thumbnail_placeholder);
		}
	}

	private static Picasso picasso(Context context) {
		return Picasso.with(context);
	}
}
