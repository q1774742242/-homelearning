package com.svse.service;

import java.util.List;

import com.svse.entity.Emp;

public interface Empservice {
	List<Emp> getall();
	void addEmp(Emp user);
	void updateEmp(Emp user);
	void deleEmp(int eid);
	Emp getOne(int eid);
}
