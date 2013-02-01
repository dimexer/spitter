package com.dimexer.spitter.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.service.SpitterService;

public class SpitterServiceImpl extends NamedParameterJdbcDaoSupport implements
		SpitterService {

	public int addSpitter(Spitter spitter) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username", spitter.getUsername());
		paramMap.put("password", spitter.getPassword());
		paramMap.put("fullName", spitter.getFullName());
		paramMap.put("email", spitter.getEmail());
		getNamedParameterJdbcTemplate()
				.update("insert into spitter values(null, :username, :password, :fullName, :email)",
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

						Spitter a = new Spitter(arg0.getString(2),
								arg0.getString(3), arg0.getString(4), arg0
										.getString(5));
						a.setLastLogin(arg0.getDate(6));
						return a;
					}
				});
	}
	
	public void updateLastLoginTime(Spitter spitter){
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", spitter.getUsername());
		int res = getNamedParameterJdbcTemplate().update("update spitter s set s.last_login=now() where s.username = :username", params);
		if(res == 0)
			System.out.println("WANING!!! No Spitter with the requested id found!");
	}
}
