package com.svse.entity;

import java.io.Serializable;

public class Cpmessage extends Pagecount implements Serializable {
		
	private int fid;
	private String fname;
	private int cid;
	private String cname;
	private String fmodel;
	private String faddress;
	private String foutprice;
	private String finprice;
	private String fimg;
	private int fcount;
	
	private int model;
	
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
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
	public String getFoutprice() {
		return foutprice;
	}
	public void setFoutprice(String foutprice) {
		this.foutprice = foutprice;
	}
	public String getFinprice() {
		return finprice;
	}
	public void setFinprice(String finprice) {
		this.finprice = finprice;
	}
	public String getFimg() {
		return fimg;
	}
	public void setFimg(String fimg) {
		this.fimg = fimg;
	}
	public int getFcount() {
		return fcount;
	}
	public void setFcount(int fcount) {
		this.fcount = fcount;
	}
	
	
	
}
