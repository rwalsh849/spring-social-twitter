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

/**
 * Possible metrics that can be retrieved in a {@link StatisticsSnapshot}.
 * @author Hudson Mendes
 *
 */
public enum StatisticsMetric {
	estimated_charge_local_micro,
	promotion_card_responses,
	promoted_tweet_search_card_engagements,
	promoted_tweet_timeline_card_engagements,
	conversion_site_visits,
	conversion_purchases,
	conversion_downloads,
	conversion_sign_ups,
	conversion_custom,
	billed_engagements,
	billed_follows,
	promoted_account_follows,
	promoted_tweet_search_clicks,
	promoted_tweet_search_engagements,
	promoted_tweet_search_follows,
	promoted_tweet_search_replies,
	promoted_tweet_search_retweets,
	promoted_tweet_timeline_clicks,
	promoted_tweet_timeline_engagements,
	promoted_tweet_timeline_follows,
	promoted_tweet_timeline_replies,
	promoted_tweet_timeline_retweets,
	promoted_tweet_search_url_clicks,
	promoted_tweet_timeline_url_clicks,
	promoted_tweet_search_favorites,
	promoted_tweet_timeline_favorites,
	mobile_conversion_installs,
	mobile_conversion_logins,
	mobile_conversion_re_engages,
	mobile_conversion_sign_ups,
	mobile_conversion_purchases,
	promoted_tweet_app_install_attempts,
	promoted_tweet_app_open_attempts,
	mobile_conversion_updates,
	mobile_conversion_tutorial_completes,
	mobile_conversion_reservations,
	mobile_conversion_add_to_cart,
	mobile_conversion_add_to_wishlist,
	mobile_conversion_checkout_initiated,
	mobile_conversion_searches,
	mobile_conversion_level_achieved,
	mobile_conversion_achievement_unlocked,
	mobile_conversion_content_views,
	mobile_conversion_shares,
	mobile_conversion_invites,
	mobile_conversion_added_payment_infos,
	mobile_conversion_spent_credits,
	mobile_conversion_rated,
	mobile_conversion_installs_breakdown,
	mobile_conversion_logins_breakdown,
	mobile_conversion_re_engages_breakdown,
	mobile_conversion_sign_ups_breakdown,
	mobile_conversion_purchases_breakdown,
	promoted_tweet_app_install_attempts_breakdown,
	promoted_tweet_app_open_attempts_breakdown,
	mobile_conversion_updates_breakdown,
	mobile_conversion_tutorial_completes_breakdown,
	mobile_conversion_reservations_breakdown,
	mobile_conversion_add_to_cart_breakdown,
	mobile_conversion_add_to_wishlist_breakdown,
	mobile_conversion_checkout_initiated_breakdown,
	mobile_conversion_searches_breakdown,
	mobile_conversion_level_achieved_breakdown,
	mobile_conversion_achievement_unlocked_breakdown,
	mobile_conversion_content_views_breakdown,
	mobile_conversion_shares_breakdown,
	mobile_conversion_invites_breakdown,
	mobile_conversion_added_payment_infos_breakdown,
	mobile_conversion_spent_credits_breakdown,
	mobile_conversion_rated_breakdown,
	promoted_video_total_views,
	promoted_video_replays,
	promoted_video_cta_clicks,
	promoted_video_views_25,
	promoted_video_views_50,
	promoted_video_views_75,
	promoted_video_views_100,
	promoted_account_impressions,
	promoted_account_profile_visits,
	promoted_account_follow_rate,
	promoted_tweet_search_engagement_rate,
	promoted_tweet_search_impressions,
	promoted_tweet_timeline_engagement_rate,
	promoted_tweet_timeline_impressions,
	billed_charge_local_micro,
}
