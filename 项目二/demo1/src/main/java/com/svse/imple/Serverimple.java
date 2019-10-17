package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Serverdao;
import com.svse.entity.Servicetype;
import com.svse.service.Serverservice;

@Service
public class Serverimple implements Serverservice {
	
	
	@Autowired
	private Serverdao dao;


	@Override
	public void addservice(Servicetype user) {
		
			dao.addservice(user);
	}

	@Override
	public void updateService(Servicetype user) {
		
			dao.updateService(user);
	}

	@Override
	public Servicetype getOne(int sid) {
		
		return dao.getOne(sid);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}

	@Override
	public int verifyname(Servicetype user) {
		
		return dao.verifyname(user);
	}

	@Override
	public Servicetype getsname(Servicetype user) {
		
		return dao.getsname(user);
	}

	@Override
	public List<Servicetype> getAll(int begin, int page) {
	
		return dao.getAll(begin, page);
	}

}
