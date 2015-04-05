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
package org.springframework.social.twitter.api.impl;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;
import org.springframework.social.twitter.api.AdAccount;
import org.springframework.social.twitter.api.ContentApprovalStatus;

/**
 * @author Hudson mendes
 */
public class AdvertisingTemplateTest extends AbstractTwitterApiTest {

	@Test
	public void getAccounts() {
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("ad-accounts"), APPLICATION_JSON));

		List<AdAccount> accounts = twitter.advertisingOperations().getAccounts();
		assertAdAccountContents(accounts);
	}
	
	private void assertAdAccountContents(List<AdAccount> accounts) {
		assertEquals(2, accounts.size());
		
		assertEquals("l0l0l0", accounts.get(0).getId());
		assertEquals("h1234jasd", accounts.get(0).getName());
		assertEquals("699169a7693e571000000fef0ef0ef09", accounts.get(0).getSalt());
		assertEquals(ContentApprovalStatus.ACCEPTED, accounts.get(0).getApprovalStatus());
		assertEquals(TimeZone.getTimeZone("America/Los_Angeles"), accounts.get(0).getTimeZone());
		assertEquals(LocalDateTime.of(2013,Month.MAY,22,07,00,00), accounts.get(0).getTimeZoneSwitchAt());
		assertEquals(LocalDateTime.of(2013,Month.MARCH,05,21,57,11), accounts.get(0).getCreatedAt());
		assertEquals(LocalDateTime.of(2015,Month.FEBRUARY,21,03,15,30), accounts.get(0).getUpdatedAt());
		assertEquals(false, accounts.get(0).isDeleted());
		
		assertEquals("l1l1l1", accounts.get(1).getId());
		assertEquals("test02483", accounts.get(1).getName());
		assertEquals("abababababababababababababababab", accounts.get(1).getSalt());
		assertEquals(ContentApprovalStatus.ACCEPTED, accounts.get(1).getApprovalStatus());
		assertEquals(TimeZone.getTimeZone("America/Los_Angeles"), accounts.get(1).getTimeZone());
		assertEquals(LocalDateTime.of(2013,Month.JANUARY,01,01,01,01), accounts.get(1).getTimeZoneSwitchAt());
		assertEquals(LocalDateTime.of(2011,Month.JANUARY,01,01,01,01), accounts.get(1).getCreatedAt());
		assertEquals(LocalDateTime.of(2012,Month.JANUARY,01,01,01,01), accounts.get(1).getUpdatedAt());
		assertEquals(false, accounts.get(0).isDeleted());
	}
	
}
