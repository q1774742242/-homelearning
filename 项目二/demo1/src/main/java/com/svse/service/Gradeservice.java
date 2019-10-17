package com.svse.service;

import java.util.List;

import com.svse.entity.Grade;



public interface Gradeservice {
	List<Grade> getAll(int begin,int page);
	void addgra(Grade user);
	void delegra(int did);
	void updategra(Grade user);
	Grade getgrade(int did);
	int getcount();
	int getdname(Grade user);
	int getdjf(Grade user);
	Grade getuser(int did);
	void uppdname(Grade user);
	List<Grade> all();
}
