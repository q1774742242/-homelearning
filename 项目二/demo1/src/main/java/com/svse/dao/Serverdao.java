package com.svse.dao;

import java.util.List;

import com.svse.entity.Servicetype;

public interface Serverdao {
	List<Servicetype> getAll(int begin,int page);
	void addservice(Servicetype user);
	void updateService(Servicetype user);
	Servicetype getOne(int sid);
	int getcount();
	//验证重名
	int verifyname(Servicetype user);
	//验证修改
	Servicetype getsname(Servicetype user);
	
}
