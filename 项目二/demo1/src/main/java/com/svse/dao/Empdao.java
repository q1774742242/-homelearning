package com.svse.dao;

import java.util.List;

import com.svse.entity.Emp;

public interface Empdao {
	List<Emp> getall();
	void addEmp(Emp user);
	void updateEmp(Emp user);
	void deleEmp(int eid);
	Emp getOne(int eid);
}
