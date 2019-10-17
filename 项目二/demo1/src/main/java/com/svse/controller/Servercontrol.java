package com.svse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svse.entity.Cptype;
import com.svse.entity.Grade;
import com.svse.entity.Servicetype;
import com.svse.service.Serverservice;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("server.do")
@Scope("prototype")
public class Servercontrol {

	@Autowired
	private Serverservice  server;
	@RequestMapping(params="method=index")
	public String index() {
		
		return "server/all";
	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		
		List<Servicetype> all=server.getAll(offset, limit);
		int count=server.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addserver(Servicetype user) {
		server.addservice(user);
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Servicetype getone(int sid) {
		Servicetype user=server.getOne(sid);
	
		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int sid,Model model) {
		
		model.addAttribute("sid",sid);
		return "server/update";
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Servicetype user) {
		server.updateService(user);
		return 1;
		
	}
	
	
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "server/add";
	}
	
	
	//验证重名
	@RequestMapping(params="method=verifysname")
	@ResponseBody
	public int verifycname(Servicetype user) {
		int count=0;
		int sid=user.getSid();
		String sname=user.getSname();
		//查重名
		
		Servicetype x=server.getsname(user);
		//原名
		String y=x.getSname();
		
		if (y.equalsIgnoreCase(sname)) {
			//与原名相同，不修改
			count=0;
		} else {
			int count1=server.verifyname(user);
			if (count1>0) {
				count=1;
			}else{
				count=0;
			}
		}
		
		return count;
	}
	
	
	@RequestMapping(params="method=getsname")
	@ResponseBody
	public int getcname(Servicetype user) {
		
		int count=0;
		int count1=server.verifyname(user);
		if (count1>0) {
			count=1;
		
		} else {
			count=0;
		}
		return count;

	}
	
	
	
	
}
