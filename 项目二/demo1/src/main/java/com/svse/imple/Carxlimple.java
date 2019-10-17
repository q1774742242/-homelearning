package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Carxldao;
import com.svse.entity.Carxl;
import com.svse.service.Carxlservice;


@Service
public class Carxlimple implements Carxlservice {

	
	@Autowired
	private Carxldao dao;
	@Override
	public List<Carxl> getAll(int begin, int page) {
		
		return dao.getAll(begin, page);
	}

	@Override
	public Carxl getOne(int xid) {
		
		return dao.getOne(xid);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}

	@Override
	public void updateCarxl(Carxl user) {
		
		dao.updateCarxl(user);
	}

	@Override
	public void addCarxl(Carxl user) {
		
		dao.addCarxl(user);
	}

	@Override
	public Carxl getaname(Carxl user) {
		
		return dao.getaname(user);
	}

	@Override
	public int verifyname(Carxl user) {
		
		return dao.verifyname(user);
	}

	@Override
	public List<Carxl> all() {
		
		return dao.all();
	}

	@Override
	public List<Carxl> getallxl(int aid) {

		return dao.getallxl(aid);
	}

}
