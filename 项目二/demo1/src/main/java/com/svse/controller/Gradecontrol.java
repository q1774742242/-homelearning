package com.svse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svse.entity.Grade;
import com.svse.service.Gradeservice;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("grade.do")
@Scope("prototype")
public class Gradecontrol {

	@Autowired
	private Gradeservice grade;
	
	@RequestMapping(params="method=index")
	public String index() {
		
		return "grade/all";
	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		
		List<Grade> all=grade.getAll(offset, limit);
		int count=grade.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int addgra(Grade user) {
		
		
		grade.addgra(user);	
		return 1;
		}
	
	//得到did；
	@RequestMapping(params="method=one")
	public String getone(int did,Model model) {
		
		model.addAttribute("did",did);
		return "grade/update";
	}
	
	//修改页面
	@RequestMapping(params="method=getone")
	@ResponseBody
	public Grade getone1(int did) {
		Grade user=grade.getgrade(did);
		
		return  user;
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int updategra(Grade user) {
		
		grade.updategra(user);
		
		return 1;
	}
	
	@RequestMapping(params="method=dele")
	@ResponseBody
	public int delegra(int did) {
	
		grade.delegra(did);
		return 1;
	}
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
		
		return "grade/add";
	}
	
	@RequestMapping(params="method=vcode")
	@ResponseBody
	public int vcode(Grade user) {
		
		int count1=grade.getdjf(user);
	
		return count1;
	}
	
	@RequestMapping(params="method=vname")
	@ResponseBody
	public int vname(Grade user) {
	
		int count=grade.getdname(user);

		return count;
	}
	
	//修改里的验证重名
	@RequestMapping(params="method=vcode1")
	@ResponseBody
	public int vcode1(Grade user) {
		int count1=0;
		int djf=user.getDjf();//传过来的
		int did=user.getDid();
		Grade ar=grade.getuser(did);
		int x=ar.getDjf();
		if (djf==x) {
			count1=0;//名字与原名相同
		} else  {
			count1=grade.getdjf(user);
		}
		
	
		return count1;
	}
	
	@RequestMapping(params="method=vname1")
	@ResponseBody
	public int vname1(Grade user) {
		int count=0;
		String dname=user.getDname();
		int did=user.getDid();
		Grade ar=grade.getuser(did);
		String x=ar.getDname();
		
		if (x.equalsIgnoreCase(dname)) {
			
			count=0;
		} else {
		int	count1=grade.getdname(user);//不与原名相同
			if (count1>0) {
				count=1;
			} else {
				count=0;
			}
		}

		return count;
	}
	
	//修改等级名称
	@RequestMapping(params="method=updatedname")
	public void updatedname(Grade user) {
		grade.uppdname(user);

	}
	
	
}
