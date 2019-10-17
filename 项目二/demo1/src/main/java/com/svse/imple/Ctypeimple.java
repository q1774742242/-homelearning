package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Ctypedao;
import com.svse.entity.Cptype;
import com.svse.service.Ctypeservice;


@Service
public class Ctypeimple implements Ctypeservice {

	
	@Autowired
	private Ctypedao dao;


	@Override
	public void addctype(Cptype user) {
		
		dao.addctype(user);
	}

	@Override
	public void delectype(int cid) {
		
		dao.delectype(cid);
	}

	@Override
	public void updatectype(Cptype user) {
		
		dao.updatectype(user);
	}

	@Override
	public Cptype getone(int cid) {
		
		return dao.getone(cid);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}

	@Override
	public List<Cptype> getAll(int begin, int page) {
	
		return dao.getAll(begin, page);
	}

	@Override
	public int getcount1(Cptype user) {
		
		return dao.getcount1(user);
	}

	@Override
	public Cptype getcname(int cid) {
		
		return dao.getcname(cid);
	}

	@Override
	public List<Cptype> all() {
		
		return dao.all();
	}

}
