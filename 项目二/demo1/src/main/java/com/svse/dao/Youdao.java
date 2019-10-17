package com.svse.dao;

import java.util.List;

import com.svse.entity.Youhui;

public interface Youdao {
	List<Youhui> getAll(int begin,int page);
	Youhui getOne(int yid);
	int getcount();
	void updateyou(Youhui user);
	void addcar(Youhui user);
	Youhui getytitle(Youhui user);
	int verifyname(Youhui user);
	List<Youhui> all();
}
