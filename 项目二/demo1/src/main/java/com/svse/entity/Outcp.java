package com.svse.entity;

import java.io.Serializable;

public class Outcp extends Pagecount implements Serializable {
		
	private int tid;
	private int rid;
	private int fid;
	private int xcount;
	private String ttime;
	private int uid;
	private int tflag;
	private String flag;
	private String rcard;
	private String rname;
	private String cname;
	private String fname;//产品名字
	private double foutprice;//售价
	private int gcount;//库存
	private double lastprice;//折扣价格
	//出库
	private String faddress;
	
	private double finprice;
	private  String fimg;
	
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public double getFinprice() {
		return finprice;
	}
	public void setFinprice(double finprice) {
		this.finprice = finprice;
	}
	public String getFimg() {
		return fimg;
	}
	public void setFimg(String fimg) {
		this.fimg = fimg;
	}
	//会员折扣
	private int did;
	private double dzk;
	//cid类型
	private int cid;
	private String uname;
	//总计
	private double count;

	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getFoutprice() {
		return foutprice;
	}
	public void setFoutprice(double foutprice) {
		this.foutprice = foutprice;
	}
	public double getLastprice() {
		return lastprice;
	}
	public void setLastprice(double lastprice) {
		this.lastprice = lastprice;
	}
	public double getDzk() {
		return dzk;
	}
	public void setDzk(double dzk) {
		this.dzk = dzk;
	}

	
	public String getRcard() {
		return rcard;
	}
	public void setRcard(String rcard) {
		this.rcard = rcard;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getGcount() {
		return gcount;
	}
	public void setGcount(int gcount) {
		this.gcount = gcount;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}

	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getXcount() {
		return xcount;
	}
	public void setXcount(int xcount) {
		this.xcount = xcount;
	}
	public String getTtime() {
		return ttime;
	}
	public void setTtime(String ttime) {
		this.ttime = ttime;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getTflag() {
		return tflag;
	}
	public void setTflag(int tflag) {
		this.tflag = tflag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
