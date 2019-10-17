package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Userdao;
import com.svse.entity.Userinfo;
import com.svse.service.Userservice;


@Service
public class Userimple implements Userservice {

	
	@Autowired
	private Userdao dao;
	@Override
	public List<Userinfo> getAll(int begin, int page) {
		
		return dao.getAll(begin, page);
	}

	@Override
	public Userinfo getOne(int uid) {
		
		return dao.getOne(uid);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}

	@Override
	public void updateUserinfo(Userinfo user) {
		
		dao.updateUserinfo(user);
	}

	@Override
	public void addUserinfo(Userinfo user) {
		
		dao.addUserinfo(user);
	}

	@Override
	public Userinfo getuname(Userinfo user) {
		
		return dao.getuname(user);
	}

	@Override
	public int verifyname(Userinfo user) {
		
		return dao.verifyname(user);
	}

	@Override
	public int getlogin(Userinfo user) {
		
		return dao.getlogin(user);
	}

	@Override
	public Userinfo getuser(Userinfo user) {
	
		return dao.getuser(user);
	}

	@Override
	public int getempty(String uname) {
	
		return dao.getempty(uname);
	}

	@Override
	public void getregister(Userinfo user) {
		
		dao.getregister(user);
		
	}




}
