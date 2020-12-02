package com.lin.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserMoodPraiseRel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer userid;

	private Integer moodid;

	public UserMoodPraiseRel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getMoodid() {
		return moodid;
	}

	public void setMoodid(Integer moodid) {
		this.moodid = moodid;
	}

	@Override
	public String toString() {
		return "UserMoodPraiseRel [id=" + id + ", userid=" + userid + ", moodid=" + moodid + "]";
	}

}
