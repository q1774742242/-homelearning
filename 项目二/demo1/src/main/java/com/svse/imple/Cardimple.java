package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Carddao;
import com.svse.entity.Pcard;
import com.svse.service.Cardservice;


@Service
public class Cardimple implements Cardservice {

	
	@Autowired
	private Carddao dao;
	@Override
	public List<Pcard> getAll(int begin, int page) {
		
		return dao.getAll(begin, page);
	}

	@Override
	public Pcard getOne(int zid) {
		
		return dao.getOne(zid);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}

	@Override
	public void updatecard(Pcard user) {
		
		dao.updatecard(user);
	}

	@Override
	public void addcard(Pcard user) {
		
		dao.addcard(user);
	}

	@Override
	public Pcard getzname(Pcard user) {
		
		return dao.getzname(user);
	}

	@Override
	public int verifyname(Pcard user) {
		
		return dao.verifyname(user);
	}

	@Override
	public List<Pcard> all() {
		
		return dao.all();
	}

}
