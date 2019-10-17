package com.svse.entity;

import java.io.Serializable;

public class Grade extends Pagecount implements  Serializable {
		
	private int did;
	private String dname;
	private int djf;//等级积分
	double dmoneychange;//兑换比例
			
	public double getDmoneychange() {
		return dmoneychange;
	}
	public void setDmoneychange(double dmoneychange) {
		this.dmoneychange = dmoneychange;
	}
	double dzk;//折扣
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getDjf() {
		return djf;
	}
	public void setDjf(int djf) {
		this.djf = djf;
	}
	
	public double getDzk() {
		return dzk;
	}
	public void setDzk(double dzk) {
		this.dzk = dzk;
	}

	
	
	
}
