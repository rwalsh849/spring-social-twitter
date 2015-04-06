package org.springframework.social.twitter.api.impl;

public enum TwitterApiUriResourceForStandard {
	BLOCKS("blocks/list.json"),
	BLOCKS_IDS("blocks/ids.json"),
	BLOCKS_CREATE("blocks/create.json"),
	BLOCKS_DESTROY("blocks/destroy.json");
	
	private final String name;
	
	TwitterApiUriResourceForStandard(String path) {
		this.name = path;
	}
	
	public String getPath() {
		return this.name;
	}
}
