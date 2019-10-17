package com.svse.entity;

import java.io.Serializable;

public class Youhui extends Pagecount implements Serializable {
		
	private int yid;
	private String ytitle;
	private String ybegintime;
	private String yendtime;
	private float ymoney;
	private float ylessmoney;
	public int getYid() {
		return yid;
	}
	public void setYid(int yid) {
		this.yid = yid;
	}
	public String getYtitle() {
		return ytitle;
	}
	public void setYtitle(String ytitle) {
		this.ytitle = ytitle;
	}
	public String getYbegintime() {
		return ybegintime;
	}
	public void setYbegintime(String ybegintime) {
		this.ybegintime = ybegintime;
	}
	public String getYendtime() {
		return yendtime;
	}
	public void setYendtime(String yendtime) {
		this.yendtime = yendtime;
	}
	public float getYmoney() {
		return ymoney;
	}
	public void setYmoney(float ymoney) {
		this.ymoney = ymoney;
	}
	public float getYlessmoney() {
		return ylessmoney;
	}
	public void setYlessmoney(float ylessmoney) {
		this.ylessmoney = ylessmoney;
	}
	
	
	
	
}
