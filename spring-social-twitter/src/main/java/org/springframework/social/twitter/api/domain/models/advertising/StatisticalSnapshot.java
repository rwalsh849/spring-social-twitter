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
package org.springframework.social.twitter.api.domain.models.advertising;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.social.twitter.api.impl.standard.models.TwitterObject;

/**
 * Represents a snapshot of the statistics on advertising
 * for any of the perspectives requested to the twitter ads api.
 * @author Hudson Mendes
 */
public class StatisticalSnapshot extends TwitterObject {
	private final String id;
	
	private final List<BigDecimal> estimatedCharge;
	
	private final List<BigDecimal> billedCharge;
	private final List<Integer> billedEngagements;
	private final List<Integer> billedFollows;
	
	private final List<Double> promotedAccountFollowRate;
	private final List<Integer> promotedAccountFollows;
	private final List<Integer> promotedAccountImpressions;
	private final List<Integer> promotedAccountProfileVisits;
	
	private final List<Integer> promotedTweetSearchClicks;
	private final List<Double> promotedTweetSearchEngagementRate;
	private final List<Integer> promotedTweetSearchEngagements;
	private final List<Integer> promotedTweetSearchFollows;
	private final List<Integer> promotedTweetSearchImpressions;
	private final List<Integer> promotedTweetSearchReplies;
	private final List<Integer> promotedTweetSearchRetweets;
	
	private final List<Integer> promotedTweetTimelineClicks;
	private final List<Double> promotedTweetTimelineEngagementRate;
	private final List<Integer> promotedTweetTimelineEngagements;
	private final List<Integer> promotedTweetTimelineFollows;
	private final List<Integer> promotedTweetTimelineImpressions;
	private final List<Integer> promotedTweetTimelineReplies;
	private final List<Integer> promotedTweetTimelineRetweets;
	
	private final StatisticalGranularity granularity;
	
	private final LocalDateTime startTime;
	private final LocalDateTime endTime;
	
	public StatisticalSnapshot(
		String id,
		List<BigDecimal> estimatedCharge,
		List<BigDecimal> billedCharge,
		List<Integer> billedEngagements,
		List<Integer> billedFollows,
		List<Double> promotedAccountFollowRate,
		List<Integer> promotedAccountFollows,
		List<Integer> promotedAccountImpressions,
		List<Integer> promotedAccountProfileVisits,
		List<Integer> promotedTweetSearchClicks,
		List<Double> promotedTweetSearchEngagementRate,
		List<Integer> promotedTweetSearchEngagements,
		List<Integer> promotedTweetSearchFollows,
		List<Integer> promotedTweetSearchImpressions,
		List<Integer> promotedTweetSearchReplies,
		List<Integer> promotedTweetSearchRetweets,
		List<Integer> promotedTweetTimelineClicks,
		List<Double> promotedTweetTimelineEngagementRate,
		List<Integer> promotedTweetTimelineEngagements,
		List<Integer> promotedTweetTimelineFollows,
		List<Integer> promotedTweetTimelineImpressions,
		List<Integer> promotedTweetTimelineReplies,
		List<Integer> promotedTweetTimelineRetweets,
		StatisticalGranularity granularity,
		LocalDateTime startTime,
		LocalDateTime endTime) {
		
		this.id = id;
		
		this.estimatedCharge = estimatedCharge;
		
		this.billedCharge = billedCharge;
		this.billedEngagements = billedEngagements;
		this.billedFollows = billedFollows;
		
		this.promotedAccountFollowRate = promotedAccountFollowRate;
		this.promotedAccountFollows = promotedAccountFollows;
		this.promotedAccountImpressions = promotedAccountImpressions;
		this.promotedAccountProfileVisits = promotedAccountProfileVisits;
		
		this.promotedTweetSearchClicks = promotedTweetSearchClicks;
		this.promotedTweetSearchEngagementRate = promotedTweetSearchEngagementRate;
		this.promotedTweetSearchEngagements = promotedTweetSearchEngagements;
		this.promotedTweetSearchFollows = promotedTweetSearchFollows;
		this.promotedTweetSearchImpressions = promotedTweetSearchImpressions;
		this.promotedTweetSearchReplies = promotedTweetSearchReplies;
		this.promotedTweetSearchRetweets = promotedTweetSearchRetweets;
		
		this.promotedTweetTimelineClicks = promotedTweetTimelineClicks;
		this.promotedTweetTimelineEngagementRate = promotedTweetTimelineEngagementRate;
		this.promotedTweetTimelineEngagements = promotedTweetTimelineEngagements;
		this.promotedTweetTimelineFollows = promotedTweetTimelineFollows;
		this.promotedTweetTimelineImpressions = promotedTweetTimelineImpressions;
		this.promotedTweetTimelineReplies = promotedTweetTimelineReplies;
		this.promotedTweetTimelineRetweets = promotedTweetTimelineRetweets;
		
		this.granularity = granularity;
		
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public List<BigDecimal> getEstimatedCharge() {
		return estimatedCharge;
	}

	public List<BigDecimal> getBilledCharge() {
		return billedCharge;
	}

	public List<Integer> getBilledEngagements() {
		return billedEngagements;
	}

	public List<Integer> getBilledFollows() {
		return billedFollows;
	}

	public List<Double> getPromotedAccountFollowRate() {
		return promotedAccountFollowRate;
	}

	public List<Integer> getPromotedAccountFollows() {
		return promotedAccountFollows;
	}

	public List<Integer> getPromotedAccountImpressions() {
		return promotedAccountImpressions;
	}

	public List<Integer> getPromotedAccountProfileVisits() {
		return promotedAccountProfileVisits;
	}

	public List<Integer> getPromotedTweetSearchClicks() {
		return promotedTweetSearchClicks;
	}

	public List<Double> getPromotedTweetSearchEngagementRate() {
		return promotedTweetSearchEngagementRate;
	}

	public List<Integer> getPromotedTweetSearchEngagements() {
		return promotedTweetSearchEngagements;
	}

	public List<Integer> getPromotedTweetSearchFollows() {
		return promotedTweetSearchFollows;
	}

	public List<Integer> getPromotedTweetSearchImpressions() {
		return promotedTweetSearchImpressions;
	}

	public List<Integer> getPromotedTweetSearchReplies() {
		return promotedTweetSearchReplies;
	}

	public List<Integer> getPromotedTweetSearchRetweets() {
		return promotedTweetSearchRetweets;
	}

	public List<Integer> getPromotedTweetTimelineClicks() {
		return promotedTweetTimelineClicks;
	}

	public List<Double> getPromotedTweetTimelineEngagementRate() {
		return promotedTweetTimelineEngagementRate;
	}

	public List<Integer> getPromotedTweetTimelineEngagements() {
		return promotedTweetTimelineEngagements;
	}

	public List<Integer> getPromotedTweetTimelineFollows() {
		return promotedTweetTimelineFollows;
	}

	public List<Integer> getPromotedTweetTimelineImpressions() {
		return promotedTweetTimelineImpressions;
	}

	public List<Integer> getPromotedTweetTimelineReplies() {
		return promotedTweetTimelineReplies;
	}

	public List<Integer> getPromotedTweetTimelineRetweets() {
		return promotedTweetTimelineRetweets;
	}

	public StatisticalGranularity getGranularity() {
		return granularity;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}
}
