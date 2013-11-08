package com.dimexer.spitter.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="followings")
public class FollowingRelation {
	
	@Field("spitter_uname")
	private String spitterUsername;
	
	@Field("follower_uname")
	private String followerUsername;

	public String getSpitterUsername() {
		return spitterUsername;
	}

	public void setSpitterUsername(String spitterUsername) {
		this.spitterUsername = spitterUsername;
	}

	public String getFollowerUsername() {
		return followerUsername;
	}

	public void setFollowerUsername(String followerUsername) {
		this.followerUsername = followerUsername;
	}
}
