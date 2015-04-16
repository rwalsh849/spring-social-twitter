package org.springframework.social.twitter.api.impl.advertising;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import org.springframework.social.twitter.api.advertising.StatisticsMetric;

public class StatisticsSnapshotMetricMapBuilder {
	public Map<StatisticsMetric, Type> build() {
		Map<StatisticsMetric, Type> map = new HashMap<StatisticsMetric, Type>();
		mapCounters(map);
		mapRates(map);
		mapCurrencies(map);
		mapComplexObjects(map);
		return map;
	}
	
	private void mapCounters(Map<StatisticsMetric, Type> map) {
		map.put(StatisticsMetric.promotion_card_responses, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_search_card_engagements, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_card_engagements, Integer.class);
		map.put(StatisticsMetric.conversion_site_visits, Integer.class);
		map.put(StatisticsMetric.conversion_purchases, Integer.class);
		map.put(StatisticsMetric.conversion_downloads, Integer.class);
		map.put(StatisticsMetric.conversion_sign_ups, Integer.class);
		map.put(StatisticsMetric.conversion_custom, Integer.class);
		map.put(StatisticsMetric.billed_engagements, Integer.class);
		map.put(StatisticsMetric.billed_follows, Integer.class);
		map.put(StatisticsMetric.promoted_account_follows, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_search_clicks, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_search_engagements, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_search_follows, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_search_replies, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_search_retweets, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_clicks, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_engagements, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_follows, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_replies, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_retweets, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_search_url_clicks, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_url_clicks, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_search_favorites, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_favorites, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_installs, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_logins, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_re_engages, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_sign_ups, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_purchases, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_app_install_attempts, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_app_open_attempts, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_updates, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_tutorial_completes, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_reservations, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_add_to_cart, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_add_to_wishlist, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_checkout_initiated, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_searches, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_level_achieved, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_achievement_unlocked, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_content_views, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_shares, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_invites, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_added_payment_infos, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_spent_credits, Integer.class);
		map.put(StatisticsMetric.mobile_conversion_rated, Integer.class);
		map.put(StatisticsMetric.promoted_video_total_views, Integer.class);
		map.put(StatisticsMetric.promoted_video_replays, Integer.class);
		map.put(StatisticsMetric.promoted_video_cta_clicks, Integer.class);
		map.put(StatisticsMetric.promoted_video_views_25, Integer.class);
		map.put(StatisticsMetric.promoted_video_views_50, Integer.class);
		map.put(StatisticsMetric.promoted_video_views_75, Integer.class);
		map.put(StatisticsMetric.promoted_video_views_100, Integer.class);
		map.put(StatisticsMetric.promoted_account_impressions, Integer.class);
		map.put(StatisticsMetric.promoted_account_profile_visits, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_search_impressions, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_impressions, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_search_impressions, Integer.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_impressions, Integer.class);
	}
	
	private void mapRates(Map<StatisticsMetric, Type> map) {
		map.put(StatisticsMetric.promoted_account_follow_rate, Double.class);
		map.put(StatisticsMetric.promoted_tweet_search_engagement_rate, Double.class);
		map.put(StatisticsMetric.promoted_tweet_timeline_engagement_rate, Double.class);
	}
	
	private void mapCurrencies(Map<StatisticsMetric, Type> map) {
		map.put(StatisticsMetric.billed_charge_local_micro, BigDecimal.class);
		map.put(StatisticsMetric.estimated_charge_local_micro, BigDecimal.class);
	}
	
	private void mapComplexObjects(Map<StatisticsMetric, Type> map) {
		map.put(StatisticsMetric.mobile_conversion_installs_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_logins_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_re_engages_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_sign_ups_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_purchases_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.promoted_tweet_app_install_attempts_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.promoted_tweet_app_open_attempts_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_updates_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_tutorial_completes_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_reservations_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_add_to_cart_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_add_to_wishlist_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_checkout_initiated_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_searches_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_level_achieved_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_achievement_unlocked_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_content_views_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_shares_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_invites_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_added_payment_infos_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_spent_credits_breakdown, AbstractMap.SimpleEntry.class);
		map.put(StatisticsMetric.mobile_conversion_rated_breakdown, AbstractMap.SimpleEntry.class);


	}
}
