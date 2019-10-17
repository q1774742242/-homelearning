package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Youdao;
import com.svse.entity.Youhui;
import com.svse.service.Youservice;


@Service
public class Youimple implements Youservice {

	@Autowired
	private Youdao dao;
	@Override
	public List<Youhui> getAll(int begin, int page) {
		
		return dao.getAll(begin, page);
	}

	@Override
	public Youhui getOne(int yid) {
		
		return dao.getOne(yid);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}



	@Override
	public void addcar(Youhui user) {
		
		dao.addcar(user);
	}

	@Override
	public Youhui getytitle(Youhui user) {
		
		return dao.getytitle(user);
	}

	@Override
	public int verifyname(Youhui user) {
		
		return dao.verifyname(user);
	}

	@Override
	public void updateyou(Youhui user) {
		dao.updateyou(user);
		
	}

	@Override
	public List<Youhui> all() {
		
		return dao.all();
	}

}
