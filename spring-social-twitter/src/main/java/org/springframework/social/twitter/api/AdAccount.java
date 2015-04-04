/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.twitter.api;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.social.twitter.api.AccountSettings.TimeZone;

/**
 * Represents an Advertising Account
 * 
 * Source: https://dev.twitter.com/ads/campaigns/getting-started 
 * 		Advertising accounts are registered on ads.twitter.com and
 * 		identified in the API by account_id. Advertising accounts
 * 		link directly to funding sources and leverage content from
 * 		one or more Twitter user accounts as ‘promotable users’.
 * 		Each advertising account can grant permission to one or more
 * 		Twitter user accounts. The advertising account, or “current account,”
 * 		is represented in nearly every URL executed as an in-line:account_id
 * 		parameter.
 * 
 * @author Hudson Mendes
 */
public class AdAccount extends TwitterObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String id;
	private final String name;
	private final TimeZone timezone;
	private final LocalDateTime timeZoneSwitchAt;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final String salt;
	private final ContentApprovalStatus approvalStatus;
	private final Boolean deleted;
	
	public AdAccount(String id, String name, TimeZone timezone, LocalDateTime timeZoneSwitchAt, LocalDateTime createdAt, LocalDateTime updatedAt, String salt, ContentApprovalStatus approvalStatus, Boolean deleted) {
		this.id = id;
		this.name = name;
		this.timezone = timezone;
		this.timeZoneSwitchAt = timeZoneSwitchAt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.salt = salt;
		this.approvalStatus = approvalStatus;
		this.deleted = deleted;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public TimeZone getTimezone() {
		return timezone;
	}

	public LocalDateTime getTimeZoneSwitchAt() {
		return timeZoneSwitchAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public String getSalt() {
		return salt;
	}

	public ContentApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public Boolean isDeleted() {
		return deleted;
	}
}
