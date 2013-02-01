package com.dimexer.spitter.model;

import java.util.Date;

public class Spittle {
	private Integer id;
	private String text;
	private Spitter spitter;
	private Date time;

	public Integer getId() {
		return id;
	}

	public Spittle() {

	}

	public Spittle(String text, int spitterId) {
		this.text = text;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Spitter getSpitter() {
		return spitter;
	}

	public void setSpitter(Spitter spitter) {
		this.spitter = spitter;
	}
}
