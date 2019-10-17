package com.svse.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Emp implements Serializable {

	
	private int eid;
	private	String ename;
	private	MultipartFile ximg;
	private int pid;
	private String eimg;
	
	private String pname;
	
	
	public String getEimg() {
		return eimg;
	}
	public void setEimg(String eimg) {
		this.eimg = eimg;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public MultipartFile getXimg() {
		return ximg;
	}
	public void setXimg(MultipartFile ximg) {
		this.ximg = ximg;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
}
