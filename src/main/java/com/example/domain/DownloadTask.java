package com.example.domain;

import java.util.Date;

import org.joda.time.DateTime;

public class DownloadTask extends Base {

	private Long id;
	private Long userId;
	private Long fileId;
	private String clientIp;
	private Date expiredAt;
	private Long timeCostMillis;
	private Date lastDldedAt;
	private String uuid;
	
	public void reset() {
		super.reset();
		DateTime dateTime = new DateTime(getCreatedAt());
		DateTime expiredAt = dateTime.plusDays(7);
		setExpiredAt(expiredAt.toDate());
		setTimeCostMillis(0L);
		setLastDldedAt(getCreatedAt());
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		if (clientIp == null) {
			clientIp = EMPTY_STRING;
		}
		this.clientIp = clientIp;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTimeCostMillis() {
		return timeCostMillis;
	}

	public void setTimeCostMillis(Long timeCostMillis) {
		this.timeCostMillis = timeCostMillis;
	}

	public Date getLastDldedAt() {
		return lastDldedAt;
	}

	public void setLastDldedAt(Date lastDldedAt) {
		this.lastDldedAt = lastDldedAt;
	}
	
	public void resetLastDldededAt() {
		setLastDldedAt(new Date());
	}
	
	public void resetTimeCostMillis() {
		setTimeCostMillis(lastDldedAt.getTime() - getCreatedAt().getTime());
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
