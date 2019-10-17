package com.svse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.svse.entity.Pcard;

import com.svse.service.Cardservice;


import net.sf.json.JSONObject;

@Controller
@RequestMapping("card.do")
@Scope("prototype")
public class Cardcontrol {

	@Autowired
	private Cardservice  card;
	@RequestMapping(params="method=index")
	public String index() {
		
		return "card/all";
	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		
		List<Pcard> all=card.getAll(offset, limit);
		int count=card.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addcard(Pcard user) {
		card.addcard(user);;
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Pcard getone(int zid) {
		Pcard user=card.getOne(zid);
	
		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int zid,Model model) {
		
		model.addAttribute("zid",zid);
		return "card/update";
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Pcard user) {
		card.updatecard(user);
		return 1;
		
	}
	
	
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "card/add";
	}
	
	
	//验证重名
	@RequestMapping(params="method=verifyzname")
	@ResponseBody
	public int verifycname(Pcard user) {
		int count=0;
		int zid=user.getZid();
		String zname=user.getZname();
		//查重名
		
		Pcard x=card.getzname(user);
		//原名
		String y=x.getZname();
		
		if (y.equalsIgnoreCase(zname)) {
			//与原名相同，不修改
			count=0;
		} else {
			int count1=card.verifyname(user);
			if (count1>0) {
				count=1;
			}else{
				count=0;
			}
		}
		
		return count;
	}
	
	
	@RequestMapping(params="method=getzname")
	@ResponseBody
	public int getcname(Pcard user) {
		
		int count=0;
		int count1=card.verifyname(user);
		if (count1>0) {
			count=1;
		
		} else {
			count=0;
		}
		return count;

	}
	
	
	
	
}
