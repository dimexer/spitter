package com.dimexer.spitter.mongo;

import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class SpitterMongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "spitter";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient();
	}

}
