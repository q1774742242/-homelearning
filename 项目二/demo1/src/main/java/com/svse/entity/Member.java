package com.svse.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Member extends Pagecount implements Serializable {
		
	private int rid;
	private String rname;
	private String rcard;
	private String rpsw;
	private String rimg;
	private String rtel;
	private String dname;
	private int did;
	private String rbirthday;
	private int rstatus;
	private String rcode;
	private String rcarnum;
	private String xname;
	private int xid;
	private String rcolor;
	private String rway;
	private String zname;
	private int zid;
	private String rnum;
	private String raddress;
	private String rremark;
	private String rtime;
	private double rmoney;
	private int rsex;
	private String sex;
	private int aid;
	private String aname;
	private double dzk;
	

	public double getDzk() {
		return dzk;
	}
	public void setDzk(double dzk) {
		this.dzk = dzk;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public int getRsex() {
		return rsex;
	}
	public void setRsex(int rsex) {
		this.rsex = rsex;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRcard() {
		return rcard;
	}
	public void setRcard(String rcard) {
		this.rcard = rcard;
	}
	public String getRpsw() {
		return rpsw;
	}
	public void setRpsw(String rpsw) {
		this.rpsw = rpsw;
	}
	public String getRimg() {
		return rimg;
	}
	public void setRimg(String rimg) {
		this.rimg = rimg;
	}
	public String getRtel() {
		return rtel;
	}
	public void setRtel(String rtel) {
		this.rtel = rtel;
	}

	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getRbirthday() {
		return rbirthday;
	}
	public void setRbirthday(String rbirthday) {
		this.rbirthday = rbirthday;
	}

	
	
	public int getRstatus() {
		return rstatus;
	}
	public void setRstatus(int rstatus) {
		this.rstatus = rstatus;
	}
	public String getRcode() {
		return rcode;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	public String getRcarnum() {
		return rcarnum;
	}
	public void setRcarnum(String rcarnum) {
		this.rcarnum = rcarnum;
	}
	public String getXname() {
		return xname;
	}
	public void setXname(String xname) {
		this.xname = xname;
	}
	public int getXid() {
		return xid;
	}
	public void setXid(int xid) {
		this.xid = xid;
	}
	public String getRcolor() {
		return rcolor;
	}
	public void setRcolor(String rcolor) {
		this.rcolor = rcolor;
	}
	public String getRway() {
		return rway;
	}
	public void setRway(String rway) {
		this.rway = rway;
	}
	public String getZname() {
		return zname;
	}
	public void setZname(String zname) {
		this.zname = zname;
	}
	public int getZid() {
		return zid;
	}
	public void setZid(int zid) {
		this.zid = zid;
	}
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getRaddress() {
		return raddress;
	}
	public void setRaddress(String raddress) {
		this.raddress = raddress;
	}
	public String getRremark() {
		return rremark;
	}
	public void setRremark(String rremark) {
		this.rremark = rremark;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	public double getRmoney() {
		return rmoney;
	}
	public void setRmoney(double rmoney) {
		this.rmoney = rmoney;
	}

	
	
}
