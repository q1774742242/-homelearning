package com.svse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svse.entity.Car;


import com.svse.service.Carservice;


import net.sf.json.JSONObject;

@Controller
@RequestMapping("car.do")
@Scope("prototype")
public class Carcontrol {

	@Autowired
	private Carservice car;
	@RequestMapping(params="method=index")
	public String index() {
		
		return "car/all";
	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		
		List<Car> all=car.getAll(offset, limit);
		int count=car.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addcar(Car user) {
		car.addcar(user);
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Car getone(int aid) {
		Car user=car.getOne(aid);
	
		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int aid,Model model) {
		
		model.addAttribute("aid",aid);
		return "car/update";
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Car user) {
		car.updatecar(user);
		return 1;
		
	}
	
	
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "car/add";
	}
	
	
	//验证重名
	@RequestMapping(params="method=verifyaname")
	@ResponseBody
	public int verifycname(Car user) {
		int count=0;
		int aid=user.getAid();
		String aname=user.getAname();
		//查重名
		
		Car x=car.getaname(user);
		//原名
		String y=x.getAname();
		
		if (y.equalsIgnoreCase(aname)) {
			//与原名相同，不修改
			count=0;
		} else {
			int count1=car.verifyname(user);
			if (count1>0) {
				count=1;
			}else{
				count=0;
			}
		}
		
		return count;
	}
	
	
	@RequestMapping(params="method=getaname")
	@ResponseBody
	public int getcname(Car user) {
		
		int count=0;
		int count1=car.verifyname(user);
		if (count1>0) {
			count=1;
		
		} else {
			count=0;
		}
		return count;

	}
	
	
	
	
}
