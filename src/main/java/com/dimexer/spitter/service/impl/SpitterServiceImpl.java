package com.dimexer.spitter.service.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.model.FollowingRelation;
import com.dimexer.spitter.service.SpitterService;

public class SpitterServiceImpl extends NamedParameterJdbcDaoSupport implements
		SpitterService {

	@Autowired
	private MongoOperations mongoTemplate;

	public List<Spitter> loadFollowers(Spitter spitter) {
		return this.searchFollowings("spitter_uname", spitter.getUsername());
	}

	public List<Spitter> loadFollowed(Spitter spitter) {
		return this.searchFollowings("follower_uname", spitter.getUsername());
	}

	public int addSpitter(Spitter spitter) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username", spitter.getUsername());
		paramMap.put("password", spitter.getPassword());
		paramMap.put("fullName", spitter.getFullName());
		paramMap.put("email", spitter.getEmail());
		getNamedParameterJdbcTemplate()
				.update("insert into spitter values(null, :username, :password, :fullName, :email, now())",
						paramMap);
		// TODO: return spitter id
		return 0;
	}

	public Spitter findSpitterByUsername(String username) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		return getNamedParameterJdbcTemplate().queryForObject(
				"select * from spitter where username=:username", params,
				new RowMapper<Spitter>() {

					public Spitter mapRow(ResultSet arg0, int arg1)
							throws SQLException {

						Spitter a = new Spitter(arg0.getInt(1), arg0
								.getString(2), arg0.getString(3), arg0
								.getString(4), arg0.getString(5), arg0
								.getDate(6));
						return a;
					}
				});
	}

	public void updateLastLoginTime(Spitter spitter) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", spitter.getUsername());
		int res = getNamedParameterJdbcTemplate()
				.update("update spitter s set s.last_login=now() where s.username = :username",
						params);
		if (res == 0)
			System.out
					.println("WANING!!! No Spitter with the requested id found!");
	}

	public void addFollower(Spitter target, Spitter follower) {
		FollowingRelation rel = new FollowingRelation();
		rel.setFollowerUsername(follower.getUsername());
		rel.setSpitterUsername(target.getUsername());
		rel.setFollowSince(new Date());
		mongoTemplate.insert(rel);
	}

	public MongoOperations getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	private List<Spitter> searchFollowings(String fieldName, String searchValue) {
		Query q = new Query(where(fieldName).is(searchValue));
		List<FollowingRelation> follRels = mongoTemplate.find(q,
				FollowingRelation.class);
		if (follRels.size() < 1)
			return Collections.emptyList();
		List<Spitter> followers = new LinkedList<Spitter>();
		for (FollowingRelation f : follRels)
			followers.add(this.findSpitterByUsername(f.getSpitterUsername()));
		return followers;
	}
}
