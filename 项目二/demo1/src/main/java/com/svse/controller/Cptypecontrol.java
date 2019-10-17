package com.svse.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svse.entity.Cptype;
import com.svse.service.Ctypeservice;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("ctype.do")
@Scope("prototype")
public class Cptypecontrol {
	
	@Autowired
	public Ctypeservice ctype;
	@RequestMapping(params="method=index")
	public String index() {
		
		return "cptype/all";
	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		System.out.println("wojinlai");
		List<Cptype> all=ctype.getAll(offset, limit);

		int count=ctype.getcount();
		
		
		
		JSONObject obj=null;
		try {
		
			obj=new JSONObject();
			obj.put("rows",all);
			obj.put("total",count);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;

	}
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addctype(Cptype user) {
		ctype.addctype(user);
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Cptype getone(int cid) {
		Cptype user=ctype.getone(cid);
	
		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int cid,Model model) {
		
		model.addAttribute("cid",cid);
		return "cptype/update";
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Cptype user) {
		ctype.updatectype(user);
		return 1;
		
	}
	
	
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "cptype/add";
	}
	
	//验证重名
	@RequestMapping(params="method=verifycname")
	@ResponseBody
	public int verifycname(Cptype user) {
		int count=0;
		int cid=user.getCid();
		String cname=user.getCname();
		//查重名
		
		Cptype x=ctype.getcname(cid);
		//原名
		String y=x.getCname();
		
		if (y.equalsIgnoreCase(cname)) {
			//与原名相同，不修改
			count=0;
		} else {
			int count1=ctype.getcount1(user);
			if (count1>0) {
				count=1;
			}else{
				count=0;
			}
		}
		
		return count;
	}
	
	
	@RequestMapping(params="method=getcname")
	@ResponseBody
	public int getcname(Cptype user) {
		
		int count=0;
		int count1=ctype.getcount1(user);
		if (count1>0) {
			count=1;
		
		} else {
			count=0;
		}
		return count;

	}
}
