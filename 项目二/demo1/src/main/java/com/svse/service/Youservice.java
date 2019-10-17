package com.svse.service;

import java.util.List;

import com.svse.entity.Youhui;

public interface Youservice {
	List<Youhui> getAll(int begin,int page);
	Youhui getOne(int yid);
	int getcount();
	void updateyou(Youhui user);
	void addcar(Youhui user);
	Youhui getytitle(Youhui user);
	int verifyname(Youhui user);
	List<Youhui> all();
}
