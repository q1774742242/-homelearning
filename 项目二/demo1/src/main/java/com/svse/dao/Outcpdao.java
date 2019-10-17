package com.svse.dao;

import java.util.List;

import com.svse.entity.Cpmessage;
import com.svse.entity.Getcp1;
import com.svse.entity.Outcp;

public interface Outcpdao {
	List<Outcp> getAll(int begin,int page);
	Outcp getOne(int tid);
	int getcount();
	void updateOutcp(Outcp user);
	void addOutcp(Outcp user);
	List<Outcp> getorder(Outcp user);
	int getordercount(Outcp user);
	void dele(int tid);
	int getsum(int fid);
	void upporder(int tid);
	List<Outcp> getAllout(Outcp user);
	int getcountout(Outcp user);
}
