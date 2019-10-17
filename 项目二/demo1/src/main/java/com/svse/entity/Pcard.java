package com.svse.entity;

import java.io.Serializable;

public class Pcard extends Pagecount implements Serializable {
		
	private int zid;
	private  String zname;
	public int getZid() {
		return zid;
	}
	public void setZid(int zid) {
		this.zid = zid;
	}
	public String getZname() {
		return zname;
	}
	public void setZname(String zname) {
		this.zname = zname;
	}

	
}
