package com.svse.dao;

import java.util.List;


import com.svse.entity.Cpmessage;

public interface Cpmessagedao {
	List<Cpmessage> getAll(int begin,int page);
	Cpmessage getOne(int fid);
	int getcount();
	void updateCpmessage(Cpmessage user);
	void addCpmessage(Cpmessage user);
	//验证重名：修改
	Cpmessage getname(Cpmessage user);
	//验证重名：增加
	int verifyname(Cpmessage user);
	List<Cpmessage> all();
	List<Cpmessage> getfname(int cid);
}
