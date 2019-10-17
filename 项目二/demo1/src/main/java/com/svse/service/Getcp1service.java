package com.svse.service;

import java.util.List;

import com.svse.entity.Getcp1;

public interface Getcp1service {
	List<Getcp1> getAll(Getcp1 user);
	Getcp1 getOne(int gid);
	int getcount(Getcp1 user);
	void updateGetcp1(Getcp1 user);
	void addGetcp1(Getcp1 user);
	List<Getcp1> all();
	Getcp1 getmoney(int fid);
}
