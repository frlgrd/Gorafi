package com.frlgrd.rssstream.core.cache;

import java.io.Serializable;
import java.util.Date;

public class CacheWrapper<T> implements Serializable {

	private Date cachedDate;
	private T data;

	public CacheWrapper() {
	}

	public CacheWrapper(T data) {
		this.cachedDate = new Date();
		this.data = data;
	}

	public CacheWrapper(Date cachedDate, T data) {
		this.cachedDate = cachedDate;
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CacheWrapper<?> that = (CacheWrapper<?>) o;

		if (cachedDate != null ? !cachedDate.equals(that.cachedDate) : that.cachedDate != null)
			return false;
		return !(data != null ? !data.equals(that.data) : that.data != null);

	}

	@Override
	public int hashCode() {
		int result = cachedDate != null ? cachedDate.hashCode() : 0;
		result = 31 * result + (data != null ? data.hashCode() : 0);
		return result;
	}

	public Date getCachedDate() {
		return cachedDate;
	}

	public void setCachedDate(Date cachedDate) {
		this.cachedDate = cachedDate;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
