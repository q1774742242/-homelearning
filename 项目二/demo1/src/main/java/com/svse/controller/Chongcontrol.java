package com.svse.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svse.entity.Chong;
import com.svse.entity.Member;
import com.svse.entity.Userinfo;
import com.svse.entity.Youhui;
import com.svse.service.Chongservice;
import com.svse.service.Memberservice;
import com.svse.service.Userservice;
import com.svse.service.Youservice;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("chong.do")
@Scope("prototype")
public class Chongcontrol {

	@Autowired
	private Chongservice chong;
	
	
	@Autowired
	private Youservice you;
	
	@Autowired
	private Memberservice member;
	
	@Autowired
	private Userservice user;
	
	
	
	
	@RequestMapping(params="method=index")
	public String index() {
		
		return "chong/all";
	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		
		List<Chong> all=chong.getAll(offset, limit);
		int count=chong.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addchong(Chong users,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		Userinfo all=(Userinfo)session.getAttribute("user");
		users.setUid(all.getUid());
		System.out.println(all.getUid());
		
		//添加时间
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
		String otime = ff.format(d);
		users.setOtime(otime);
		chong.addChong(users);
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Chong getone(int aid) {
		Chong user=chong.getOne(aid);
	
		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int aid,Model model) {
		
		model.addAttribute("aid",aid);
		return "chong/update";
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Chong user) {
		chong.updateChong(user);;
		return 1;
		
	}
	
	
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "out/add";
	}
	
	
	@RequestMapping(params="method=getselect")
	@ResponseBody
	public Member getmyone(Member users) {
		
		Member all=member.getselect(users);
	
		return all;
	}
	
	
	
	//
	@RequestMapping(params="method=getcard")
	@ResponseBody
	public int getcard(Member users) {
		
		int count=member.verifycard(users);
		if (count>0) {
			count=1;
		} else {
			count=0;
		}
		return  count;
	}
	
	
	
	@RequestMapping(params="method=ally")
	@ResponseBody
	public List<Youhui> getyou() {
		List<Youhui> all=you.all();
		return all;
	}
	
	
	@RequestMapping(params="method=getmoney")
	@ResponseBody
	public Youhui getmoney(int yid) {
		Youhui all=you.getOne(yid);
		return all;
	}
	
}
