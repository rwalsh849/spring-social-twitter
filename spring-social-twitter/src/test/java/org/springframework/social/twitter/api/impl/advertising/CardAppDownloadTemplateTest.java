/*
 * Copyright 2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.twitter.api.impl.advertising;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.Test;
import org.springframework.social.twitter.api.advertising.Card;
import org.springframework.social.twitter.api.advertising.CardAppCallToAction;
import org.springframework.social.twitter.api.advertising.CardType;
import org.springframework.social.twitter.api.impl.AbstractTwitterApiTest;
import org.springframework.social.twitter.api.impl.DataListHolder;

/**
 * @author Richard Walsh
 */
public class CardAppDownloadTemplateTest extends AbstractTwitterApiTest {
    
    @Test
    public void getCard() {
        
        String mockedAccountId = "0ga0yn";
        String mockedCardId = "92ph";
        
        mockServer
                .expect(requestTo(
                        "https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/cards/app_download" +
                                "/" + mockedCardId +
                                "?with_deleted=true"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-cards-appdownloads-single"), APPLICATION_JSON));

        Card card = twitter.creativeOperations().getAppDownloadCard(mockedAccountId, mockedCardId);
        
        assertEquals("pfs", card.getId());
        assertEquals("Sample App Card", card.getName());
        assertEquals("abc1", card.getAccountId());
        assertEquals(LocalDateTime.of(2014, Month.JUNE, 10, 04, 21, 19), card.getCreatedAt());
        assertEquals(LocalDateTime.of(2014, Month.JUNE, 10, 04, 21, 19), card.getUpdatedAt());
        assertEquals(false, card.isDeleted());
        assertEquals(CardType.APP_DOWNLOAD, card.getCardType());
        assertEquals("com.twitter.android", card.getGoogleplayAppId());
        assertEquals("333903271", card.getIpadAppId());
        assertEquals("333903271", card.getIphoneAppId());
        assertEquals("https://cards.twitter.com/cards/abc1/pfs", card.getPreviewUrl());
        assertEquals("US", card.getAppCountryCode());
    }
    
    @Test
    public void getCards() throws UnsupportedEncodingException {
        String mockedAccountId = "abc1";
        String mockedCardId1 = "pfs";
        
        mockServer
                .expect(requestTo(
                        "https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/cards/app_download" +
                                "?card_ids=" + URLEncoder.encode(mockedCardId1, UTF8) +
                                "&with_deleted=false"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-cards-appdownloads"), APPLICATION_JSON));

        DataListHolder<Card> cards = twitter.creativeOperations().getCards(
                mockedAccountId,
                new CardQueryBuilder()
                        .withCards(mockedCardId1)
                        .includeDeleted(false));
        
        List<Card> cardsList = cards.getList();
        
        assertEquals(1, cardsList.size());

        Card card = cardsList.get(0);
        
        assertEquals("pfs", card.getId());
        assertEquals("Sample App Card", card.getName());
        assertEquals("abc1", card.getAccountId());
        assertEquals(LocalDateTime.of(2014, Month.JUNE, 10, 04, 21, 19), card.getCreatedAt());
        assertEquals(LocalDateTime.of(2014, Month.JUNE, 10, 04, 21, 19), card.getUpdatedAt());
        assertEquals(false, card.isDeleted());
        assertEquals(CardType.APP_DOWNLOAD, card.getCardType());
        assertEquals("com.twitter.android", card.getGoogleplayAppId());
        assertEquals("333903271", card.getIpadAppId());
        assertEquals("333903271", card.getIphoneAppId());
        assertEquals("https://cards.twitter.com/cards/abc1/pfs", card.getPreviewUrl());
        assertEquals("US", card.getAppCountryCode());
        
    }
    
    @Test
    public void createCard() throws UnsupportedEncodingException {
        String doesNotMatterString = "does-not-matter";
        String mockedAccountId = "1ga1yn";
        
        String chainedPostContent =
                "name="+doesNotMatterString+
                "&" + "app_country_code="+doesNotMatterString+
                "&" + "iphone_app_id="+doesNotMatterString+
                "&" + "ipad_app_id="+doesNotMatterString+
                "&" + "googleplay_app_id="+doesNotMatterString+
                "&" + "app_cta="+"PLAY"+
                "&" + "iphone_deep_link="+doesNotMatterString+
                "&" + "ipad_deep_link="+doesNotMatterString+
                "&" + "googleplay_deep_link="+doesNotMatterString+
                "&" + "custom_icon_media_id="+doesNotMatterString+
                "&" + "custom_app_description="+doesNotMatterString;

        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/cards/app_download"))
                .andExpect(method(POST))
                .andExpect(content().string(chainedPostContent))
                .andRespond(withSuccess(jsonResource("ad-cards-appdownloads-create"), APPLICATION_JSON));

        Card card = twitter.creativeOperations().createCard(
                mockedAccountId,
                new CardFormBuilder()
                        .withName(doesNotMatterString)
                        .withAppCountryCode(doesNotMatterString)
                        .withIPhoneAppId(doesNotMatterString)
                        .withGooglePlayAppId(doesNotMatterString)
                        .withIPadAppId(doesNotMatterString)
                        .withCallToAction(CardAppCallToAction.PLAY)
                        .withIPhoneDeepLink(doesNotMatterString)
                        .withIPadDeepLink(doesNotMatterString)
                        .withGooglePlayDeepLink(doesNotMatterString)
                        .withCustomIconMediaId(doesNotMatterString)
                        .withCustomAppDescription(doesNotMatterString)
                        );

        assertEquals("pfs", card.getId());
        assertEquals("Sample App Card", card.getName());
        assertEquals("abc1", card.getAccountId());
        assertEquals(LocalDateTime.of(2014, Month.JUNE, 10, 04, 21, 19), card.getCreatedAt());
        assertEquals(LocalDateTime.of(2014, Month.JUNE, 10, 04, 21, 19), card.getUpdatedAt());
        assertEquals(false, card.isDeleted());
        assertEquals(CardType.APP_DOWNLOAD, card.getCardType());
        assertEquals("https://cards.twitter.com/cards/abc1/pfs", card.getPreviewUrl());
    }
    
    @Test
    public void updateCard() throws UnsupportedEncodingException {
        String doesNotMatterString = "does-not-matter";
        String mockedAccountId = "1ga1yn";
        String mockedCardId = "pfs";
        
        String chainedPostContent =
                "name="+doesNotMatterString+
                "&" + "app_country_code="+doesNotMatterString+
                "&" + "iphone_app_id="+doesNotMatterString+
                "&" + "ipad_app_id="+doesNotMatterString+
                "&" + "googleplay_app_id="+doesNotMatterString+
                "&" + "app_cta="+"PLAY"+
                "&" + "iphone_deep_link="+doesNotMatterString+
                "&" + "ipad_deep_link="+doesNotMatterString+
                "&" + "googleplay_deep_link="+doesNotMatterString+
                "&" + "custom_icon_media_id="+doesNotMatterString+
                "&" + "custom_app_description="+doesNotMatterString;

        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/cards/app_download" +
                        "/" + mockedCardId ))
                .andExpect(method(PUT))
                .andExpect(content().string(chainedPostContent))
                .andRespond(withSuccess(jsonResource("ad-cards-appdownloads-update"), APPLICATION_JSON));

        //Card card = 
                twitter.creativeOperations().updateCard(
                mockedAccountId,mockedCardId,
                new CardFormBuilder()
                        .withName(doesNotMatterString)
                        .withAppCountryCode(doesNotMatterString)
                        .withIPhoneAppId(doesNotMatterString)
                        .withGooglePlayAppId(doesNotMatterString)
                        .withIPadAppId(doesNotMatterString)
                        .withCallToAction(CardAppCallToAction.PLAY)
                        .withIPhoneDeepLink(doesNotMatterString)
                        .withIPadDeepLink(doesNotMatterString)
                        .withGooglePlayDeepLink(doesNotMatterString)
                        .withCustomIconMediaId(doesNotMatterString)
                        .withCustomAppDescription(doesNotMatterString)
                        );

                //        assertEquals("pfs", card.getId());
                //        assertEquals("Sample App Card", card.getName());
                //        assertEquals("abc1", card.getAccountId());
                //        assertEquals(LocalDateTime.of(2014, Month.JUNE, 10, 04, 21, 19), card.getCreatedAt());
                //        assertEquals(LocalDateTime.of(2014, Month.JUNE, 10, 04, 21, 19), card.getUpdatedAt());
                //        assertEquals(false, card.isDeleted());
                //        assertEquals(CardType.APP_DOWNLOAD, card.getCardType());
                //        assertEquals("https://cards.twitter.com/cards/abc1/pfs", card.getPreviewUrl());
    }
    
    @Test
    public void deleteCard() throws UnsupportedEncodingException {
        String doesNotMatterString = "does-not-matter";
        String mockedAccountId = "1ga1yn";
        String mockedCardId = "pfs";
        
        String chainedPostContent =
                "name="+doesNotMatterString+
                "&" + "app_country_code="+doesNotMatterString+
                "&" + "iphone_app_id="+doesNotMatterString+
                "&" + "ipad_app_id="+doesNotMatterString+
                "&" + "googleplay_app_id="+doesNotMatterString+
                "&" + "app_cta="+"PLAY"+
                "&" + "iphone_deep_link="+doesNotMatterString+
                "&" + "ipad_deep_link="+doesNotMatterString+
                "&" + "googleplay_deep_link="+doesNotMatterString+
                "&" + "custom_icon_media_id="+doesNotMatterString+
                "&" + "custom_app_description="+doesNotMatterString;

        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/cards/app_download" +
                        "/" + mockedCardId ))
                .andExpect(method(DELETE))
               // .andExpect(content().string(chainedPostContent))
                .andRespond(withSuccess(jsonResource("ad-cards-appdownloads-delete"), APPLICATION_JSON));

        //Card card = 
                twitter.creativeOperations().deleteCard(mockedAccountId, mockedCardId);

                //        assertEquals("pfs", card.getId());
                //        assertEquals("Sample App Card", card.getName());
                //        assertEquals("abc1", card.getAccountId());
                //        assertEquals(LocalDateTime.of(2014, Month.JUNE, 10, 04, 21, 19), card.getCreatedAt());
                //        assertEquals(LocalDateTime.of(2014, Month.JUNE, 10, 04, 21, 19), card.getUpdatedAt());
                //        assertEquals(false, card.isDeleted());
                //        assertEquals(CardType.APP_DOWNLOAD, card.getCardType());
                //        assertEquals("https://cards.twitter.com/cards/abc1/pfs", card.getPreviewUrl());
    }

}
