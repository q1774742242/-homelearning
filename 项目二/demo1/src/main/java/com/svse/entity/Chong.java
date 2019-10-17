package com.svse.entity;

import java.io.Serializable;

public class Chong extends Pagecount implements Serializable {
		
	private int oid;
	private int rid;
	private int omoney;
	private int yid;
	private int osmoney;
	private int olastmoney;
	private int uid;
	private String oremark;
	private String otime;
	//youhui
	private String ytitle;
	private int ymoney;
	private int ylessmoney;
	//member
	private int rcard;
	
	private String uname;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getOmoney() {
		return omoney;
	}
	public void setOmoney(int omoney) {
		this.omoney = omoney;
	}
	public int getYid() {
		return yid;
	}
	public void setYid(int yid) {
		this.yid = yid;
	}
	public int getOsmoney() {
		return osmoney;
	}
	public void setOsmoney(int osmoney) {
		this.osmoney = osmoney;
	}
	public int getOlastmoney() {
		return olastmoney;
	}
	public void setOlastmoney(int olastmoney) {
		this.olastmoney = olastmoney;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getOremark() {
		return oremark;
	}
	public void setOremark(String oremark) {
		this.oremark = oremark;
	}
	public String getOtime() {
		return otime;
	}
	public void setOtime(String otime) {
		this.otime = otime;
	}
	public String getYtitle() {
		return ytitle;
	}
	public void setYtitle(String ytitle) {
		this.ytitle = ytitle;
	}
	public int getYmoney() {
		return ymoney;
	}
	public void setYmoney(int ymoney) {
		this.ymoney = ymoney;
	}
	public int getYlessmoney() {
		return ylessmoney;
	}
	public void setYlessmoney(int ylessmoney) {
		this.ylessmoney = ylessmoney;
	}
	public int getRcard() {
		return rcard;
	}
	public void setRcard(int rcard) {
		this.rcard = rcard;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
	
}
