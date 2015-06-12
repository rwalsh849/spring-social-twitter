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
package org.springframework.social.twitter.api.ton;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.springframework.social.ApiException;
import org.springframework.social.MissingAuthorizationException;

/**
 * Interface defining the twitter object nest operations.
 * 
 * @author Chris Latko
 */
public interface TonOperations {

    /**
     * Photo uploads and small tailored audiences will typically be under 64MB and can use non-resumable upload.
     * 
     * @param bucketName must be "ta_partner" for ads api
     * @param the binary data
     * @param mime type of data, must conform to http://www.iana.org/assignments/media-types/media-types.xhtml
     * @param TON expiry date, up to 7 days, but ruby script has 10 days.
     * @return 
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    URI uploadSingleChunk(final String bucketName, final byte[] data, final String contentType, final ZonedDateTime expiry);

    /**
     * Retrieves a list of all {@link FundingInstrument} linked to a particular {@link AdvertisingAccount}.
     * 
     * @param bucketName must be "ta_partner" for ads api
     * @param identifier used to resume an upload
     * @param the binary data
     * @param mime type of data, must conform to http://www.iana.org/assignments/media-types/media-types.xhtml
     * @param TON expiry date, up to 7 days, but ruby script has 10 days.
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    void uploadResumable(final String bucketName, final String resumeId, final byte[] data, final String contentType, final LocalDateTime expiry);

}
