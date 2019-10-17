package com.svse.service;

import java.util.List;

import com.svse.entity.Pcard;

public interface Cardservice {
	List<Pcard> getAll(int begin,int page);
	Pcard getOne(int zid);
	int getcount();
	void updatecard(Pcard user);
	void addcard(Pcard user);
	//修改验证重名
	Pcard getzname(Pcard user);
	//验证重名
	int verifyname(Pcard user);
	//下拉框
	List<Pcard> all();
}
