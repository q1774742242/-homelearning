package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Chongdao;
import com.svse.entity.Chong;
import com.svse.service.Chongservice;



@Service
public class Chongimple implements Chongservice {

	
	@Autowired
	private Chongdao dao;
	
	@Override
	public List<Chong> getAll(int begin, int page) {
		
		return dao.getAll(begin, page);
	}

	@Override
	public Chong getOne(int oid) {
		
		return dao.getOne(oid);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}

	@Override
	public void updateChong(Chong user) {
		
		dao.updateChong(user);
	}

	@Override
	public void addChong(Chong user) {
		
		dao.addChong(user);
	}

	@Override
	public List<Chong> all() {
		
		return dao.all();
	}

}
