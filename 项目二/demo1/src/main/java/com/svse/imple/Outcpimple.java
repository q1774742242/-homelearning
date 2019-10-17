package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Outcpdao;
import com.svse.entity.Getcp1;
import com.svse.entity.Outcp;
import com.svse.service.Outcpservice;



@Service
public class Outcpimple implements Outcpservice {

	
	@Autowired
	private Outcpdao dao;
	@Override
	public List<Outcp> getAll(int begin, int page) {
	
		return dao.getAll(begin, page);
	}

	@Override
	public Outcp getOne(int tid) {
	
		return dao.getOne(tid);
	}

	@Override
	public int getcount() {
	
		return dao.getcount();
	}

	@Override
	public void updateOutcp(Outcp user) {
	
		dao.updateOutcp(user);
	}

	@Override
	public void addOutcp(Outcp user) {
	
		dao.addOutcp(user);
	}

	@Override
	public List<Outcp> getorder(Outcp user) {
	
		return dao.getorder(user);
	}

	@Override
	public int getordercount(Outcp user) {
	
		return dao.getordercount(user);
	}

	@Override
	public void dele(int tid) {
		dao.dele(tid);
		
	}

	@Override
	public int getsum(int fid) {
	
		return dao.getsum(fid);
	}

	@Override
	public void upporder(int tid) {
		dao.upporder(tid);
	}

	@Override
	public List<Outcp> getAllout(Outcp user) {
		
		return dao.getAllout(user);
	}

	@Override
	public int getcountout(Outcp user) {
	
		return dao.getcountout(user);
	}



}
