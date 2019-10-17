package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Gradedao;
import com.svse.entity.Grade;
import com.svse.service.Gradeservice;


@Service
public  class Gradeimple implements Gradeservice {

	@Autowired
	private Gradedao dao;


	@Override
	public void addgra(Grade user) {
		
		dao.addgra(user);
	}

	@Override
	public void delegra(int did) {
		
		dao.delegra(did);
	}

	@Override
	public void updategra(Grade user) {
		
		dao.updategra(user);
	}

	@Override
	public Grade getgrade(int did) {
		
		return dao.getgrade(did);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}

	@Override
	public List<Grade> getAll(int begin, int page) {
	
		return dao.getAll(begin, page);
	}

	@Override
	public int getdname(Grade user) {
		
		return dao.getdname(user);
	}

	@Override
	public int getdjf(Grade user) {
		
		return dao.getdname(user);
	}

	@Override
	public Grade getuser(int did) {

		return dao.getuser(did);
	}

	@Override
	public void uppdname(Grade user) {
		
		dao.uppdname(user);
	}

	@Override
	public List<Grade> all() {
		
		return dao.all();
	}



	

}
