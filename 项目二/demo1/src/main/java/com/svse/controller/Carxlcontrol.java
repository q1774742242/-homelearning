package com.svse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svse.entity.Car;
import com.svse.entity.Carxl;
import com.svse.service.Carservice;
import com.svse.service.Carxlservice;


import net.sf.json.JSONObject;

@Controller
@RequestMapping("carxl.do")
@Scope("prototype")
public class Carxlcontrol {

	@Autowired
	private Carxlservice carxl;
	
	@Autowired
	private Carservice car;
	@RequestMapping(params="method=index")
	public String index() {
		
		return "carxl/all";
	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		
		List<Carxl> all=carxl.getAll(offset, limit);
		int count=carxl.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addcarxl(Carxl user) {
		carxl.addCarxl(user);
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Carxl getone(int xid) {
		Carxl user=carxl.getOne(xid);
	
		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int xid,Model model) {
		
		model.addAttribute("xid",xid);
		return "carxl/update";
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Carxl user) {
		carxl.updateCarxl(user);
		return 1;
		
	}
	
	
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "carxl/add";
	}
	
	
	//验证重名
	@RequestMapping(params="method=verifyxname")
	@ResponseBody
	public int verifyxname(Carxl user) {
		int count=0;
		int xid=user.getXid();
		String xname=user.getXname();
		//查重名
		
		Carxl x=carxl.getaname(user);
		//原名
		String y=x.getXname();
		
		if (y.equalsIgnoreCase(xname)) {
			//与原名相同，不修改
			count=0;
		} else {
			int count1=carxl.verifyname(user);
			if (count1>0) {
				count=1;
			}else{
				count=0;
			}
		}
		
		return count;
	}
	
	
	@RequestMapping(params="method=getxname")
	@ResponseBody
	public int getxname(Carxl user) {
		
		int count=0;
		int count1=carxl.verifyname(user);
		if (count1>0) {
			count=1;
		
		} else {
			count=0;
		}
		return count;

	}
	
	
	//下拉框
	@RequestMapping(params="method=alla")
	@ResponseBody
	public List<Car> getallaname() {
		List<Car> all=car.all();
		
		return all;
	}
	
	//修改下拉框
	@RequestMapping(params="method=allx")
	@ResponseBody
	public List<Car> getallanames(int xid) {
		Carxl user=carxl.getOne(xid);
		int aid=user.getAid();
		List<Car> all=new ArrayList<Car>();
		List<Car> arr=car.all();
		for (Car c:arr) {
			int x=c.getAid();
			if (x==aid) {
				all.add(0,c);
			} else {
				all.add(c);
			}
			
		}
		
		return all;
		
	}
	
	
	
}
