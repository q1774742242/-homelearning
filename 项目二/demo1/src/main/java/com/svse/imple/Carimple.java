package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Cardao;
import com.svse.entity.Car;
import com.svse.service.Carservice;


@Service
public class Carimple implements Carservice {

	
	@Autowired
	private Cardao dao;
	@Override
	public List<Car> getAll(int begin, int page) {
		
		return dao.getAll(begin, page);
	}

	@Override
	public Car getOne(int aid) {
		
		return dao.getOne(aid);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}

	@Override
	public void updatecar(Car user) {
		
		dao.updatecar(user);
	}

	@Override
	public void addcar(Car user) {
		
		dao.addcar(user);
	}

	@Override
	public Car getaname(Car user) {
		
		return dao.getaname(user);
	}

	@Override
	public int verifyname(Car user) {
		
		return dao.verifyname(user);
	}

	@Override
	public List<Car> all() {
		
		return dao.all();
	}

}
