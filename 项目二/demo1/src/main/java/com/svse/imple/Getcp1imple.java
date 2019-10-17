package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Getcp1dao;
import com.svse.entity.Getcp1;
import com.svse.service.Getcp1service;


@Service
public class Getcp1imple implements Getcp1service {

	
	@Autowired
	private Getcp1dao dao;



	@Override
	public Getcp1 getOne(int gid) {
		
		return dao.getOne(gid);
	}



	@Override
	public void updateGetcp1(Getcp1 user) {
		
		dao.updateGetcp1(user);
	}

	@Override
	public void addGetcp1(Getcp1 user) {
		
		dao.addGetcp1(user);
	}

	@Override
	public List<Getcp1> all() {
		
		return dao.all();
	}

	@Override
	public List<Getcp1> getAll(Getcp1 user) {
		
		return dao.getAll(user);
	}



	@Override
	public int getcount(Getcp1 user) {
		
		return dao.getcount(user);
	}



	@Override
	public Getcp1 getmoney(int fid) {
		
		return dao.getmoney(fid);
	}




	
	

}
