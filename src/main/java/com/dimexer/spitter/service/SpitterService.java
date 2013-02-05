package com.dimexer.spitter.service;

import com.dimexer.spitter.model.Spitter;

public interface SpitterService {
	public int addSpitter(Spitter spitter);

	public Spitter findSpitterByUsername(String username);
	
	public void updateLastLoginTime(Spitter spitter);
	
	public void addFollower(Spitter target, Spitter follower);
}
