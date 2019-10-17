package com.svse.service;

import java.util.List;

import com.svse.entity.Cptype;
import com.svse.entity.Servicetype;

public interface Serverservice {
	List<Servicetype> getAll(int begin,int page);
	void addservice(Servicetype user);
	void updateService(Servicetype user);
	Servicetype getOne(int sid);
	int getcount();
	//验证修改
	int verifyname(Servicetype user);
	//验证重名
	Servicetype getsname(Servicetype user);
}
