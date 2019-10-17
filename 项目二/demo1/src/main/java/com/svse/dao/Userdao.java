package com.svse.dao;

import java.util.List;

import com.svse.entity.Userinfo;

public interface Userdao {
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
	//根据用户名查询用户数据 
	Userinfo getuser(Userinfo user);
	//验证此用户是否存在
	int getempty(String uname);
	//注册账号和密码
	void getregister(Userinfo user);
}
