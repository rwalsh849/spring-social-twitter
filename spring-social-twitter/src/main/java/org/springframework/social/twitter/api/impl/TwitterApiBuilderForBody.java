package org.springframework.social.twitter.api.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

public class TwitterApiBuilderForBody<TData> {
    private final TData data;
    private boolean multipart = false;

    public TwitterApiBuilderForBody(TData data) {
        this.data = data;
    }

    public TwitterApiBuilderForBody<TData> multipart(boolean multipart) {
        this.multipart = multipart;
        return this;
    }

    public HttpEntity<?> build() {
        assertDataType();
        return new HttpEntity<>(
                this.data,
                this.makeHeaders());
    }

    private void assertDataType() {
        if (!MultiValueMap.class.isAssignableFrom(data.getClass())) {
            throw new IllegalArgumentException("'data' must be an instance of MultiValueMap.");
        }
    }

    private HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        if (this.multipart) {
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        }
        else {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }

        return headers;
    }
}
