package com.dimexer.spitter.service.impl;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.service.SpitterService;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class SpitterServiceImpl extends NamedParameterJdbcDaoSupport implements
		SpitterService {

	public List<Spitter> loadFollowers(Spitter spitter) {
		return searchFollowings("spitter_uname", spitter.getUsername(),
				"follower_uname");
	}

	public List<Spitter> loadFollowed(Spitter spitter) {
		return searchFollowings("follower_uname", spitter.getUsername(),
				"spitter_uname");
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
		if (target == null || follower == null)
			return;
		String query = "insert into followers values(:followedId, :followerId)";
		Map<String, String> params = new HashMap<String, String>();
		params.put("followedId", String.valueOf(target.getId()));
		params.put("followerId", String.valueOf(follower.getId()));
		getNamedParameterJdbcTemplate().update(query, params);
	}

	private List<Spitter> searchFollowings(String searchField,
			String searchValue, String targetField) {
		MongoClient mongo = null;
		try {
			mongo = new MongoClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
		List<Spitter> followers = new LinkedList<Spitter>();
		DB db = mongo.getDB("spitter");
		DBCollection coll = db.getCollection("followings");
		DBCursor followersUnames = null;
		try {
			//get only target field values
			DBObject q = new BasicDBObject(searchField, searchValue);
			followersUnames = coll.find(q);
			while (followersUnames.hasNext()) {
				String uname = followersUnames.next().get(targetField)
						.toString();
				followers.add(findSpitterByUsername(uname));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			followersUnames.close();
		}
		mongo.close();
		return followers;
	}
}
