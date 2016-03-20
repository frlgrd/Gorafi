package com.frlgrd.rssstream.core.rest.dto;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.frlgrd.rssstream.core.model.FeedItem;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "channel", strict = false)
public class ChannelDTO {

	@ElementList(name = "channel", entry = "item", inline = true)
	List<FeedItemDTO> feedItems;

	public ChannelDTO() {
	}

	public static List<FeedItem> toFeedList(@Nullable ChannelDTO channelDTO) {
		if (channelDTO == null) {
			return null;
		}

		List<FeedItem> items = new ArrayList<>();
		if (channelDTO.getFeedItems() != null) {
			for (FeedItemDTO dto : channelDTO.getFeedItems()) {
				if (!TextUtils.isEmpty(dto.getDescription())) {
					items.add(FeedItemDTO.toEntity(dto));
				}
			}
		}
		return items;
	}

	public List<FeedItemDTO> getFeedItems() {
		return feedItems;
	}

	public void setFeedItems(List<FeedItemDTO> feedItems) {
		this.feedItems = feedItems;
	}

}
