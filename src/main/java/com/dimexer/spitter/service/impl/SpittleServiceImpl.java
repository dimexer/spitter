package com.dimexer.spitter.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.model.Spittle;
import com.dimexer.spitter.service.SpittleService;

public class SpittleServiceImpl implements SpittleService {
	private JdbcTemplate jdbcTemplate;

	private TransactionTemplate txTemplate;

	/*public final void insertSpittle(Spittle spittle) {
		this.jdbcTemplate.update(
				"insert into spittle values (null, ?, ?, now())",
				spittle.getText(), spittle.getSpitterId());

	}*/

	/*public Spittle getSpittleById(Integer id) {
		Object[] a = new Object[1];
		a[0] = id;
		Spittle res = null;
		try {
			res = jdbcTemplate.queryForObject(
					"select * from spittle where id=?", a,

					new ParameterizedRowMapper<Spittle>() {

						public Spittle mapRow(ResultSet rs, int arg1)
								throws SQLException {

							Spittle found = new Spittle();
							found.setId(rs.getInt(1));
							found.setText(rs.getString(2));
							found.setSpitterId(rs.getInt(3));
							found.setTime(rs.getTimestamp(4));
							return found;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			System.err.println("No spittle with id " + id + " found!");
			res = null;
		}

		return res;
	} */

	public List<Spittle> getRecentSpittles(int size) {
		String query = null;
		Object[] params = null;
		Spitter spitter = (Spitter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(spitter == null){
			query="select * from spittle l join spitter r on l.spitter_id = r.id order by time desc limit";
			params = new Object[1];
			params[0]=size;
		}
		else{
			query="select * from spittle l join spitter r on l.spitter_id = r.id where l.time > ? order by time desc limit ?";
			params = new Object[2];
			params[0]=spitter.getLastLogin();
			params[1]=size;
		}
		
		List<Map<String, Object>> queryRes = jdbcTemplate
				.queryForList(query, params);
		
		List<Spittle> res = new ArrayList<Spittle>();
		
		for (Map<String, Object> row : queryRes) {
			Spittle a = new Spittle();
			a.setId((Integer) row.get("id"));
			a.setText((String) row.get("text"));
			a.setTime((Date) row.get("time"));
			//a.setSpitterId((Integer) row.get("spitter_id"));
			Spitter sp = new Spitter((String)row.get("username"), (String)row.get("password"), (String)row.get("full_name"), (String)row.get("email"));
			a.setSpitter(sp);
			
			res.add(a);
		}
		
		return res;
	}

	/*
	 * public void saveinTransaction(final SpitterDAO dao, final Spitter
	 * spitter) { txTemplate.execute(new TransactionCallback<Object>() {
	 * 
	 * @Override public Object doInTransaction(TransactionStatus arg0) { try {
	 * dao.insertSpitter(spitter); } catch (RuntimeException e) {
	 * e.printStackTrace(); } return new Object(); }
	 * 
	 * }); }
	 */

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public TransactionTemplate getTxTemplate() {
		return txTemplate;
	}

	public void setTxTemplate(TransactionTemplate txTemplate) {
		this.txTemplate = txTemplate;
	}

}
