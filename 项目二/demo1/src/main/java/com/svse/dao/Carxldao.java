package com.svse.dao;

import java.util.List;

import com.svse.entity.Carxl;

public interface Carxldao {
	List<Carxl> getAll(int begin,int page);
	Carxl getOne(int xid);
	int getcount();
	void updateCarxl(Carxl user);
	void addCarxl(Carxl user);
	//修改重名
	Carxl getaname(Carxl user);
	//增加重名
	int verifyname(Carxl user);
	//下拉框
	List<Carxl> all();
	//联动下拉框
	List<Carxl> getallxl(int aid);
}
