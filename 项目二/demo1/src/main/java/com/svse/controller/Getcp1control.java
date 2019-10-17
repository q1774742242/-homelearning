package com.svse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svse.entity.Cpmessage;
import com.svse.entity.Getcp1;
import com.svse.entity.Userinfo;
import com.svse.service.Cpmeservice;
import com.svse.service.Getcp1service;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("cp.do")
@Scope("prototype")
public class Getcp1control {

	@Autowired
	private Getcp1service cp;
	

	@Autowired
	private Cpmeservice cpinfo;
	@RequestMapping(params="method=index")
	public String index() {
		
		return "cp/all";
	}
	
	
	@RequestMapping(params="method=index1")
	public String index1(int fid,Model model) {
		model.addAttribute("fid",fid);
		
		return "cp/allf";
	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		
		List<Cpmessage> all=cpinfo.getAll(offset, limit);
		int count=cpinfo.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	@RequestMapping(params="method=allf")
	@ResponseBody
	public JSONObject getAlls(Getcp1 user) {
	
		List<Getcp1> all=cp.getAll(user);
		int count=cp.getcount(user);
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addcp(Getcp1 user,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		Userinfo all=(Userinfo)session.getAttribute("user");
		int uid=all.getUid();
		user.setUid(uid);
		cp.addGetcp1(user);
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Getcp1 getone(int gid) {
		Getcp1 user=cp.getOne(gid);
	
		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int gid,Model model) {
		
		model.addAttribute("gid",gid);
		return "cp/update";
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Getcp1 user) {
		cp.updateGetcp1(user);
		return 1;
		
	}
	
	
	//下拉框
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "cp/add";
	}
	
	
	
	//下拉框
	@RequestMapping(params="method=getfname")
	@ResponseBody
	public List<Cpmessage> getfname(){
		List<Cpmessage> all=cpinfo.all();
		
		return all;
		
	}
	
	//无下拉框增加
	@RequestMapping(params="method=myonef")
	public String myaddf(int fid,Model model) {
	
		Cpmessage user=cpinfo.getOne(fid);
		String fname=user.getFname();
		model.addAttribute("fname",fname);
		model.addAttribute("fid",fid);
		return "cp/addf";
	}
	
	
}
