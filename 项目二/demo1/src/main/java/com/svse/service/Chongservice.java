package com.svse.service;

import java.util.List;

import com.svse.entity.Car;
import com.svse.entity.Chong;

public interface Chongservice {
	List<Chong> getAll(int begin,int page);
	Chong getOne(int oid);
	int getcount();
	void updateChong(Chong user);
	void addChong(Chong user);
	List<Chong> all();
	
}
