package com.svse.service;

import java.util.List;

import com.svse.entity.Userinfo;

public interface Userservice {
	List<Userinfo> getAll(int begin,int page);
	Userinfo getOne(int uid);
	int getcount();
	void updateUserinfo(Userinfo user);
	void addUserinfo(Userinfo user);
	//修改验证重名
	Userinfo getuname(Userinfo user);
	
	//验证重名
	int verifyname(Userinfo user);
	//登录
	int getlogin(Userinfo user);
	//根据用户名查询用户的数据
	Userinfo getuser(Userinfo user);
	
	//验证用户存在
	int getempty(String uname);
	//注册信息
	void getregister(Userinfo user);
}
