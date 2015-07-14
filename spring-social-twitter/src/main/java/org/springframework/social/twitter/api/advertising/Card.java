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
package org.springframework.social.twitter.api.advertising;

import java.time.LocalDateTime;

import org.springframework.social.twitter.api.TwitterObject;

/**
 * Represents a Twitter Card linked a particular {@link AdvertisingAccount}. 
 * @author Richard Walsh
 */
public class Card extends TwitterObject {
	private final String id;
	private final String accountId;
	private final String name;
	private final Boolean deleted;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
    private final CardType cardType;
    private final String googleplayAppId;
    private final String ipadAppId;
    private final String iphoneAppId;
    private final String previewUrl;
    private final String appCountryCode;
	
	public Card(
			String id, String name, String accountId, LocalDateTime createdAt,
			LocalDateTime updatedAt, Boolean deleted, CardType cardType,
			String googleplayAppId, String ipadAppId, String iphoneAppId, String previewUrl,
			String appCountryCode) {
		
		this.id = id;
		this.name = name;
		this.accountId = accountId;
		
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		
		this.deleted = deleted;
		
		this.cardType = cardType;
		
		this.googleplayAppId = googleplayAppId;
		this.ipadAppId = ipadAppId;
		this.iphoneAppId = iphoneAppId;
		
		this.previewUrl = previewUrl;
		this.appCountryCode = appCountryCode;
		
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAccountId() {
		return accountId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public Boolean isDeleted() {
		return deleted;
	}

    public Boolean getDeleted() {
        return deleted;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getGoogleplayAppId() {
        return googleplayAppId;
    }

    public String getIpadAppId() {
        return ipadAppId;
    }

    public String getIphoneAppId() {
        return iphoneAppId;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getAppCountryCode() {
        return appCountryCode;
    }
}
