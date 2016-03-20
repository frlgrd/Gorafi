package com.frlgrd.rssstream.core.rest.dto;

import com.frlgrd.rssstream.core.model.FeedItem;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss")
public class RssStreamDTO {

	@Attribute
	String version;

	@Element
	ChannelDTO channel;

	public RssStreamDTO() {
	}

	public static List<FeedItem> toFeedList(RssStreamDTO rssStreamDTO) {
		if (rssStreamDTO == null) {
			return null;
		}
		return ChannelDTO.toFeedList(rssStreamDTO.getChannel());
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ChannelDTO getChannel() {
		return channel;
	}

	public void setChannel(ChannelDTO channel) {
		this.channel = channel;
	}
}
