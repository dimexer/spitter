package com.dimexer.spitter.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="followings")
public class FollowingRelation {
	
	@Field("spitter_uname")
	private String spitterUsername;
	
	@Field("follower_uname")
	private String followerUsername;

	@Field("follow_since")
	private Date followSince;
	
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

	public Date getFollowSince() {
		return followSince;
	}

	public void setFollowSince(Date followSince) {
		this.followSince = followSince;
	}
}
