package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Cpmessagedao;
import com.svse.entity.Cpmessage;
import com.svse.service.Cpmeservice;

@Service
public class Cpmessageimple implements Cpmeservice {

	
	@Autowired
	private Cpmessagedao dao;
	@Override
	public List<Cpmessage> getAll(int begin, int page) {
		
		return dao.getAll(begin, page);
	}

	@Override
	public Cpmessage getOne(int fid) {
		
		return dao.getOne(fid);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}

	@Override
	public void updateCpmessage(Cpmessage user) {
		
		dao.updateCpmessage(user);
	}

	@Override
	public void addCpmessage(Cpmessage user) {
		
		dao.addCpmessage(user);
	}

	@Override
	public Cpmessage getname(Cpmessage user) {
		
		return dao.getname(user);
	}

	@Override
	public int verifyname(Cpmessage user) {
		
		return dao.verifyname(user);
	}

	@Override
	public List<Cpmessage> all() {
		
		return dao.all();
	}

	@Override
	public List<Cpmessage> getfname(int cid) {
	
		return dao.getfname(cid);
	}

}
