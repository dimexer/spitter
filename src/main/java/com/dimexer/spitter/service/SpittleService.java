package com.dimexer.spitter.service;

import java.util.List;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.model.Spittle;

public interface SpittleService {
	public void insertSpittle(Spittle spitter);

	public List<Spittle> getSpittleFromLastLogin(Spitter spitter, int size);
	
	public List<Spittle> loadSpittlesForSpitter(Spitter spitter);
}
