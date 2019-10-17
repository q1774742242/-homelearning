package com.svse.service;

import java.util.List;

import com.svse.entity.Cptype;

public interface Ctypeservice {
	List<Cptype> getAll(int begin, int page);
	void addctype(Cptype user);
	void delectype(int cid);
	void updatectype(Cptype user);
	Cptype getone(int cid);
	int getcount();

	//验证重名
	int getcount1(Cptype user);
	//id查找名字
	Cptype getcname(int cid);	
	List<Cptype> all();
}
