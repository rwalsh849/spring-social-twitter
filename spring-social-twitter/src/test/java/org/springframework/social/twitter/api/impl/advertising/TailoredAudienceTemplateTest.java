package org.springframework.social.twitter.api.impl.advertising;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.social.twitter.api.advertising.TailoredAudience;
import org.springframework.social.twitter.api.advertising.TailoredAudienceListType;
import org.springframework.social.twitter.api.advertising.TailoredAudienceType;
import org.springframework.social.twitter.api.impl.AbstractTwitterApiTest;
import org.springframework.social.twitter.api.impl.DataListHolder;

/**
 * @author Hudson Mendes
 */
public class TailoredAudienceTemplateTest extends AbstractTwitterApiTest {

    @Test
    public void getTailoredAudience() {
        String mockedAccountId = "hkk5";
        String mockedTailoredAudience = "qq4u";
        mockServer
                .expect(requestTo(
                        "https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/tailored_audiences" +
                                "/" + mockedTailoredAudience +
                                "?with_deleted=true"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-tailored-audiences-single"), APPLICATION_JSON));

        TailoredAudience tailoredAudience = twitter.tailoredAudienceOperations().getTailoredAudience(mockedAccountId, mockedTailoredAudience);
        assertSingleTailoredAudienceContents(tailoredAudience);
    }

    @Test
    public void getTailoredAudiences() {
        String mockedAccountId = "hkk5";
        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/tailored_audiences?with_deleted=false"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-tailored-audiences"), APPLICATION_JSON));

        DataListHolder<TailoredAudience> tailoredAudiences = twitter.tailoredAudienceOperations()
                .getTailoredAudiences(
                        mockedAccountId,
                        new TailoredAudienceQueryBuilder().includeDeleted(false));

        assertTailoredAudienceContents(tailoredAudiences.getList());
    }

    @Test
    public void createTailoredAudience() {
        String mockedAccountId = "hkk5";
        String doesntMatterString = "doesn-matter";

        String chainedPostContent =
                "account_id=" + mockedAccountId + "&" +
                        "name=" + doesntMatterString + "&" +
                        "list_type=" + TailoredAudienceListType.TWITTER_ID;

        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/tailored_audiences"))
                .andExpect(method(POST))
                .andExpect(content().string(chainedPostContent))
                .andRespond(withSuccess(jsonResource("ad-tailored-audiences-single"), APPLICATION_JSON));

        TailoredAudience tailoredAudience = twitter.tailoredAudienceOperations().createTailoredAudience(
                mockedAccountId,
                new TailoredAudienceFormBuilder()
                        .withAccount(mockedAccountId)
                        .named(doesntMatterString)
                        .ofListType(TailoredAudienceListType.TWITTER_ID));

        assertSingleTailoredAudienceContents(tailoredAudience);
    }

    @Test
    public void deleteTailoredAudience() {
        String mockedAccountId = "0ga0yn";
        String mockedTailoredAudienceId = "qq4u";
        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/tailored_audiences/" + mockedTailoredAudienceId))
                .andExpect(method(DELETE))
                .andRespond(withSuccess());

        twitter.tailoredAudienceOperations().deleteTailoredAudience(mockedAccountId, mockedTailoredAudienceId);
    }

    private void assertSingleTailoredAudienceContents(TailoredAudience audience) {
        assertEquals("qq4u", audience.getId());
        assertEquals("twitter%20ids", audience.getName());

        assertEquals("OTHER", audience.getPartnerSource());
        assertEquals(TailoredAudienceListType.TWITTER_ID, audience.getListType());

        for (String reason : new String[] {"PROCESSING", "TOO_SMALL"})
            assertThat(Arrays.asList(audience.getReasonsNotTargetable()), CoreMatchers.hasItem(reason));

        for (String targetableType : new String[] {"CRM", "EXCLUDED_CRM"})
            assertThat(Arrays.asList(audience.getTargetableTypes()), CoreMatchers.hasItem(targetableType));

        assertEquals(null, audience.getAudienceSize());
        assertEquals(TailoredAudienceType.CRM, audience.getAudienceType());

        assertEquals(false, audience.isDeleted());
        assertEquals(false, audience.isTargetable());

        assertEquals("2015-06-08T20:13:40", audience.getCreatedAt().toString());
        assertEquals("2015-06-08T20:13:40", audience.getUpdatedAt().toString());
    }

    private void assertTailoredAudienceContents(List<TailoredAudience> audiences) {
        assertEquals(1, audiences.size());

        assertEquals("qq4u", audiences.get(0).getId());
        assertEquals("twitter%20ids", audiences.get(0).getName());

        assertEquals("OTHER", audiences.get(0).getPartnerSource());
        assertEquals(TailoredAudienceListType.TWITTER_ID, audiences.get(0).getListType());

        for (String reason : new String[] {"PROCESSING", "TOO_SMALL"})
            assertThat(Arrays.asList(audiences.get(0).getReasonsNotTargetable()), CoreMatchers.hasItem(reason));

        for (String targetableType : new String[] {"CRM", "EXCLUDED_CRM"})
            assertThat(Arrays.asList(audiences.get(0).getTargetableTypes()), CoreMatchers.hasItem(targetableType));

        assertEquals(null, audiences.get(0).getAudienceSize());
        assertEquals(TailoredAudienceType.CRM, audiences.get(0).getAudienceType());

        assertEquals(false, audiences.get(0).isDeleted());
        assertEquals(false, audiences.get(0).isTargetable());

        assertEquals("2015-06-08T20:13:40", audiences.get(0).getCreatedAt().toString());
        assertEquals("2015-06-08T20:13:40", audiences.get(0).getUpdatedAt().toString());
    }
}
