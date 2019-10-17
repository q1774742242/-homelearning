package com.svse.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svse.dao.Memberdao;
import com.svse.entity.Member;
import com.svse.service.Memberservice;


@Service
public class Merberimple implements Memberservice {

	
	@Autowired
	private Memberdao dao;
	@Override
	public List<Member> getAll(int begin, int page) {
		
		return dao.getAll(begin, page);
	}

	@Override
	public Member getOne(int rid) {
		
		return dao.getOne(rid);
	}

	@Override
	public int getcount() {
		
		return dao.getcount();
	}

	@Override
	public void updateMember(Member user) {
		
		dao.updateMember(user);
	}

	@Override
	public void addMember(Member user) {
		
		dao.addMember(user);
	}



	@Override
	public int all() {
		
		return dao.all();
	}

	@Override
	public Member getrname(Member user) {
		
		return dao.getrname(user);
	}

	@Override
	public Member getrcard(Member user) {
		
		return dao.getrcard(user);
	}

	@Override
	public int verifyname(Member user) {
		
		return dao.verifyname(user);
	}

	@Override
	public int verifycard(Member user) {
		
		return dao.verifycard(user);
	}

	@Override
	public Member getselect(Member user) {
		
		return dao.getselect(user);
	}

	@Override
	public int getcard(Member user) {
		
		return dao.getcard(user);
	}

	@Override
	public void uppmoney(Member user) {
		dao.uppmoney(user);
		
	}

}
