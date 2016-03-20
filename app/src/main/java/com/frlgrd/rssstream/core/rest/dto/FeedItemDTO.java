package com.frlgrd.rssstream.core.rest.dto;

import com.frlgrd.rssstream.core.model.FeedItem;
import com.frlgrd.rssstream.utils.DateUtils;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class FeedItemDTO {

	@Element(name = "title", required = false)
	String title;
	@Element(name = "description", required = false)
	String description;
	@Element(name = "thumbnail", required = false)
	String thumbnail;
	@Element(name = "link", required = false)
	String link;
	@Element(name = "pubDate", required = false)
	String pubDate;
	@Element(name = "encoded", required = false, data = true)
	String content;
	@Element(name = "mediumthumbnail", required = false)
	String mediumThumbnail;
	@Element(name = "largethumbnail", required = false)
	String largeThumbnail;
	@Element(name = "guid", required = false)
	String guid;

	public FeedItemDTO() {
	}

	public static FeedItem toEntity(FeedItemDTO dto) {
		if (dto == null) {
			return null;
		}
		FeedItem item = new FeedItem();
		item.setTitle(dto.getTitle());
		item.setDescription(dto.getDescription());
		item.setThumbnail(dto.getThumbnail());
		item.setLink(dto.getLink());
		item.setPubDate(DateUtils.format(dto.getPubDate()));
		item.setContent(dto.getContent());
		item.setMediumThumbnail(dto.getMediumThumbnail());
		item.setLargeThumbnail(dto.getLargeThumbnail());
		return item;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getMediumThumbnail() {
		return mediumThumbnail;
	}

	public void setMediumThumbnail(String mediumThumbnail) {
		this.mediumThumbnail = mediumThumbnail;
	}

	public String getLargeThumbnail() {
		return largeThumbnail;
	}

	public void setLargeThumbnail(String largeThumbnail) {
		this.largeThumbnail = largeThumbnail;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}
