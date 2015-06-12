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
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.social.twitter.api.impl.AbstractTwitterApiTest;

/**
 * @author Chris Latko
 */
public class TonTemplateTest extends AbstractTwitterApiTest {
	@SuppressWarnings("unused")
	private final static Log logger = LogFactory.getLog(TonTemplateTest.class);
	private final static String BUCKET_NAME = "ta_partner";

    @Test
    public void uploadChunk() throws IOException {
        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts?with_deleted=true&sort=updated_at"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-accounts"), APPLICATION_JSON));

        Resource resource = dataResource("hashed_twitter.txt");
        InputStream is = resource.getInputStream();
        String contentType = URLConnection.guessContentTypeFromName(resource.getFilename());
        byte[] data = bufferObj(is);
        ZonedDateTime expiry = ZonedDateTime.now().plusDays(7);
        URI uri = twitter.tonOperations().uploadSingleChunk(BUCKET_NAME, data, contentType, expiry);
        assertEquals(uri.toString(),"mofo");
    }

    private byte[] bufferObj(final InputStream is) throws IOException {
    	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    	int count;
    	byte[] data = new byte[16384];
    	while((count = is.read(data, 0, data.length)) != -1) {
    		buffer.write(data, 0, count);
    	}
    	buffer.flush();
        return buffer.toByteArray();
    }

}
