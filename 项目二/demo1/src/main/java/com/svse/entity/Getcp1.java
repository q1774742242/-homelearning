package com.svse.entity;

import java.io.Serializable;

public class Getcp1 extends Pagecount implements Serializable {
		
	private int gid;
	private int fid;
	private int gcount;
	private String gtime;
	private int uid;
	private String cname;
	private String uname;
	private String fname;
	private int cid;//产品类型
	private  String fmodel;
	private String faddress;
	private int foutprice;
	private int finprice;
	private  String fimg;
	private  int count;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getGcount() {
		return gcount;
	}
	public void setGcount(int gcount) {
		this.gcount = gcount;
	}
	public String getGtime() {
		return gtime;
	}
	public void setGtime(String gtime) {
		this.gtime = gtime;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getFmodel() {
		return fmodel;
	}
	public void setFmodel(String fmodel) {
		this.fmodel = fmodel;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public int getFoutprice() {
		return foutprice;
	}
	public void setFoutprice(int foutprice) {
		this.foutprice = foutprice;
	}
	public int getFinprice() {
		return finprice;
	}
	public void setFinprice(int finprice) {
		this.finprice = finprice;
	}
	public String getFimg() {
		return fimg;
	}
	public void setFimg(String fimg) {
		this.fimg = fimg;
	}
	
	
	
}
