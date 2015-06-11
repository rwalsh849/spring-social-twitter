package org.springframework.social.twitter.api.impl.advertising;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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

    private void assertTailoredAudienceContents(List<TailoredAudience> criterias) {
        assertEquals(1, criterias.size());

        assertEquals("qq4u", criterias.get(0).getId());
        assertEquals("twitter%20ids", criterias.get(0).getName());

        assertEquals("OTHER", criterias.get(0).getPartnerSource());
        assertEquals(TailoredAudienceListType.TWITTER_ID, criterias.get(0).getListType());

        for (String reason : new String[] {"PROCESSING", "TOO_SMALL"})
            assertThat(Arrays.asList(criterias.get(0).getReasonsNotTargetable()), CoreMatchers.hasItem(reason));

        for (String targetableType : new String[] {"CRM", "EXCLUDED_CRM"})
            assertThat(Arrays.asList(criterias.get(0).getTargetableTypes()), CoreMatchers.hasItem(targetableType));

        assertEquals(null, criterias.get(0).getAudienceSize());
        assertEquals(TailoredAudienceType.CRM, criterias.get(0).getAudienceType());

        assertEquals(false, criterias.get(0).isDeleted());
        assertEquals(false, criterias.get(0).isTargetable());

        assertEquals("2015-06-08T20:13:40", criterias.get(0).getCreatedAt().toString());
        assertEquals("2015-06-08T20:13:40", criterias.get(0).getUpdatedAt().toString());
    }
}
