package com.frlgrd.rssstream.core.model;

import org.joda.time.DateTime;

import java.io.Serializable;

public class FeedItem implements Serializable {

	private String title;
	private String description;
	private String thumbnail;
	private String link;
	private DateTime pubDate;
	private String content;
	private String mediumThumbnail;
	private String largeThumbnail;
	private Long guid;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public DateTime getPubDate() {
		return pubDate;
	}

	public void setPubDate(DateTime pubDate) {
		this.pubDate = pubDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Long getGuid() {
		return guid;
	}

	public void setGuid(Long guid) {
		this.guid = guid;
	}
}
