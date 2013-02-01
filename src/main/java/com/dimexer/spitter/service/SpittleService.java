package com.dimexer.spitter.service;

import java.util.List;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.model.Spittle;

public interface SpittleService {
	//public void insertSpittle(Spittle spitter);

	//public Spittle getSpittleById(Integer id);

	// public void saveinTransaction(final SpitterDAO dao, final Spitter
	// spitter);
	public List<Spittle> getRecentSpittles(int size);
}
