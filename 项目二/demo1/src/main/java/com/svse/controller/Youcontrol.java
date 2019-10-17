package com.svse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svse.entity.Youhui;
import com.svse.service.Youservice;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("you.do")
@Scope("prototype")
public class Youcontrol {

	@Autowired
	private Youservice you;
	@RequestMapping(params="method=index")
	public String index() {
		
		return "you/all";
	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		
		List<Youhui> all=you.getAll(offset, limit);
		int count=you.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addyou(Youhui user) {
		you.addcar(user);
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Youhui getone(int yid) {
		Youhui user=you.getOne(yid);
	
		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int yid,Model model) {
		
		model.addAttribute("yid",yid);
		return "you/update";
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Youhui user) {
		you.updateyou(user);
		return 1;
		
	}
	
	
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "you/add";
	}
	
	
	//验证重名
	@RequestMapping(params="method=verifytitle")
	@ResponseBody
	public int verifycname(Youhui user) {
		int count=0;
	
		String ytitle=user.getYtitle();
		//查重名
		
		Youhui x=you.getytitle(user);
		//原名
		String y=x.getYtitle();
		
		if (y.equalsIgnoreCase(ytitle)) {
			//与原名相同，不修改
			count=0;
			
		} else {
			int count1=you.verifyname(user);
			if (count1>0) {
				count=1;
			}else{
				count=0;
			}
		}
		
		return count;
	}
	
	
	@RequestMapping(params="method=getytitle")
	@ResponseBody
	public int getcname(Youhui user) {
		
		int count=0;
		int count1=you.verifyname(user);
		if (count1>0) {
			count=1;
		
		} else {
			count=0;
		}
		return count;

	}
	
	
	
	
}
