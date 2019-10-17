package com.svse.dao;

import java.util.List;

import com.svse.entity.Member;

public interface Memberdao {
	List<Member> getAll(int begin,int page);
	Member getOne(int rid);
	int getcount();
	void updateMember(Member user);
	void addMember(Member user);
	Member getrname(Member user);
	Member getrcard(Member user);
	int verifyname(Member user);
	int verifycard(Member user);
	int all();
	Member getselect(Member user);
	int getcard(Member user);
	//更改用户余额
	void uppmoney(Member user);
}
