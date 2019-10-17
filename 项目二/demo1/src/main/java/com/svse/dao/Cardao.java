package com.svse.dao;

import java.util.List;

import com.svse.entity.Car;

public interface Cardao {
	List<Car> getAll(int begin,int page);
	Car getOne(int aid);
	int getcount();
	void updatecar(Car user);
	void addcar(Car user);
	//验证重名：修改
	Car getaname(Car user);
	//验证重名：增加
	int verifyname(Car user);
	List<Car> all();
}
