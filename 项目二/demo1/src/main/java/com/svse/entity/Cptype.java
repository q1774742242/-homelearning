package com.svse.entity;

import java.io.Serializable;

public class Cptype extends Pagecount implements Serializable {
		
	private int cid;
	private  String cname;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
