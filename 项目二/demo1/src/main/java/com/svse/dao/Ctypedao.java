package com.svse.dao;

import java.util.List;

import com.svse.entity.Cptype;

public interface Ctypedao {
	List<Cptype> getAll(int begin, int page);
	void addctype(Cptype user);
	void delectype(int cid);
	void updatectype(Cptype user);
	Cptype getone(int cid);
	//查询个数
	int getcount();
	//验证重名
	int getcount1(Cptype user);
	//id查找名字
	Cptype getcname(int cid);
	List<Cptype> all();
}
