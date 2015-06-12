package org.springframework.social.twitter.api.impl;

public enum TwitterApiUriResourceForTon {
	TWITTER_OBJECT_NEST("ton/bucket/:bucket_name");
	
	private final String path;
	
	TwitterApiUriResourceForTon(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
}
