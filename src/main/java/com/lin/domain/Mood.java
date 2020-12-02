package com.lin.domain;

import java.io.Serializable;
import java.sql.Timestamp;


public class Mood implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String content;

	private Integer userid;

	private Timestamp publishtime;

	private Integer praisenum;

	public Mood() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Timestamp getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Timestamp publishtime) {
		this.publishtime = publishtime;
	}

	public Integer getPraisenum() {
		return praisenum;
	}

	public void setPraisenum(Integer praisenum) {
		this.praisenum = praisenum;
	}

	@Override
	public String toString() {
		return "Mood [id=" + id + ", content=" + content + ", userid=" + userid + ", publishtime=" + publishtime
				+ ", praisenum=" + praisenum + "]";
	}

}
