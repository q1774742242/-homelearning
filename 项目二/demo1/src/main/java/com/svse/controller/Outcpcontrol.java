package com.svse.controller;





import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.svse.entity.Cpmessage;
import com.svse.entity.Getcp1;
import com.svse.entity.Grade;
import com.svse.entity.Member;
import com.svse.entity.Outcp;
import com.svse.entity.Userinfo;
import com.svse.service.Cpmeservice;
import com.svse.service.Getcp1service;
import com.svse.service.Gradeservice;
import com.svse.service.Memberservice;
import com.svse.service.Outcpservice;


import net.sf.json.JSONObject;

@Controller
@RequestMapping("out.do")
@Scope("prototype")
public class Outcpcontrol {

	@Autowired
	private Outcpservice out;
	
	@Autowired
	private Cpmeservice cpinfo;
	
	
	@Autowired
	private Getcp1service cp;
	
	
	@Autowired
	private Gradeservice grade;
	
	@Autowired
	private Memberservice member;
	@RequestMapping(params="method=index")
	public String index() {
		
		return "out/all";
	}
	
	@RequestMapping(params="method=index2")
	public String index2(Model model,int fid) {
		
		model.addAttribute("fid",fid);
		return "out/allout";
	}
	
	@RequestMapping(params="method=index1")
	public String index1() {
		
		return "out/order";
	}
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		
		List<Outcp> all=out.getAll(offset, limit);
		for (Outcp c:all) {
			double dzk=c.getDzk();
			int xcount=c.getXcount();//数量
			double price=c.getFoutprice();//价格
			double lastmoney=dzk*price;//折后价格
			double count=lastmoney*xcount;
			c.setLastprice(lastmoney);
			c.setCount(count);
		}
		
		int count=out.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	//查找订单记录
	@RequestMapping(params="method=allorder")
	@ResponseBody
	public JSONObject getAllorder(Outcp user) {
		
		List<Outcp> all=out.getorder(user);
		
		for (Outcp c:all) {
			double dzk=c.getDzk();
			int xcount=c.getXcount();//数量
			double price=c.getFoutprice();//价格
			double lastmoney=dzk*price;//折后价格
			double count=lastmoney*xcount;
			c.setLastprice(lastmoney);
			c.setCount(count);
		}
		int count=out.getordercount(user);
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addout(Outcp user,HttpServletRequest request) {
		HttpSession session=request.getSession();
		Userinfo users=(Userinfo)session.getAttribute("user");
		user.setUid(users.getUid());
		
		int xcount=user.getXcount();
	
		
		int fcount=(Integer)session.getAttribute("fcount");//得到原始库存
		//更新库存
		fcount=fcount-xcount;
		if (fcount<=0) {
			session.setAttribute("fcount", 0);
		} else {
			session.setAttribute("fcount", fcount);
		}
		
		
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
		String ttime = ff.format(d);
		user.setTtime(ttime);
		out.addOutcp(user);
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Outcp getone(int tid,HttpServletRequest request) {
		Outcp user=out.getOne(tid);
		double dzk=user.getDzk();
		double price=user.getFoutprice();//价格
		double lastmoney=dzk*price;//折后价格
		
		//得到原始库存
		HttpSession session=request.getSession();
		int gcount=(Integer)session.getAttribute("fcount");
		user.setGcount(gcount);
		
		//得到已经购买的
		int xcount=user.getXcount();
	
		
		
		user.setLastprice(lastmoney);
		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int tid,Model model) {
	
		model.addAttribute("tid",tid);
		return "out/update";
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Outcp user,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		Userinfo users=(Userinfo)session.getAttribute("user");
		user.setUid(users.getUid());
		//修改的购买数量,
		int xcount=user.getXcount();
		
		int fcount=(Integer)session.getAttribute("fcount");//得到原始库存
		//原购买数量
		int tid=user.getTid();
		Outcp all=out.getOne(tid);
		int xcounts=all.getXcount();
		//判断修改的购买数量是否大于原来购买的数量
		if (xcount>xcounts) {
			fcount=fcount-(xcount-xcounts);
		} else {
			fcount=fcount+(xcount-xcounts);
		}
	
		//更新库存
		
		if (fcount<=0) {
			session.setAttribute("fcount", 0);
		} else {
			session.setAttribute("fcount", fcount);
		}
	
		
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
		String ttime = ff.format(d);
		user.setTtime(ttime);

		out.updateOutcp(user);
		return 1;
		
	}
	
	
	//增加
	@RequestMapping(params="method=myadd")
	public String myadd(int rid,Model model) {
	
		
		model.addAttribute("rid",rid);
		return "out/add";
	}
	
	
	
	//生成订单


	
	@RequestMapping(params="method=dele")
	@ResponseBody
	public int dele(int tid) {
		
		out.dele(tid);
		return 1;
		
	}


	@RequestMapping(params="method=allf")
	@ResponseBody
	public List<Cpmessage> getfname(int cid) {
		
		List<Cpmessage> all=cpinfo.getfname(cid);
		
		
		return all;
	}
	
	@RequestMapping(params="method=getrid")
	@ResponseBody
	public Member getzk(int rid) {
		Member user=member.getOne(rid);
	
		return user;
	}
	
	
	@RequestMapping(params="method=getfid")
	@ResponseBody
	public Getcp1 getmoney(int fid,HttpServletRequest request) {
		int gcount=0;
		HttpSession session=request.getSession();
		//库存已经购买的
		int xcount=out.getsum(fid);

		//得到原始的库存,fid；
		Getcp1 user=cp.getmoney(fid);
		int firstcount=user.getCount();
		
		
	
		if (xcount==0) {
			gcount=firstcount;
			
		} else {
			
			 gcount=firstcount-xcount;
		}
		
		session.setAttribute("fcount",gcount);
		user.setCount(gcount);
		return user;
	}
/***************************结账部分**********************************/
	@RequestMapping(params="method=mybuy")
	public String mybuy(int []tt,HttpServletRequest request,Model model,int rid) {
		HttpSession session=request.getSession();
		
		session.setAttribute("tt",tt);
		model.addAttribute("rid", rid);
		return "out/buy";
	}
	
	
	@RequestMapping(params="method=order")
	@ResponseBody
	public JSONObject order(Outcp user,HttpServletRequest request) {
	
		List<Outcp> all=out.getorder(user);
		//获取指定会员的订单
		//获取rid集合
		HttpSession session=request.getSession();
		
		int [] tt=(int [])session.getAttribute("tt");
		
		
		List<Outcp> arr=new ArrayList<Outcp>();
		for (Outcp c:all) {
			
			for (int i = 0; i < tt.length; i++) {
				int tid=tt[i];
					
				if (tid==c.getTid()) {
					arr.add(c);
				} 
			}
		}
	
		
			String dname="";
			double totalmoney=0;//总价
			double allmoney=0;//折后价格
			double dzk=0;		
		for (Outcp c:arr) {
			
			int did=c.getDid();//会员情况
			Grade  xx=grade.getgrade(did);		
			dname=xx.getDname();
			
			//折扣
			 dzk=c.getDzk();
			int xcount=c.getXcount();//数量
			double price=c.getFoutprice();//价格
			
			totalmoney=totalmoney+price*xcount;//总价
			
			double lastmoney=dzk*price;//折后价格
			double count=lastmoney*xcount;
			
			allmoney=allmoney+count;
			
			c.setLastprice(lastmoney);
			c.setCount(count);
		}
		int count=out.getordercount(user);
		JSONObject obj=new JSONObject();
		obj.put("rows",arr);
		obj.put("total",count);
		obj.put("dgrade",dname);//等级
		obj.put("summoney",totalmoney);//等级
		obj.put("disMoney",allmoney);//折后价
		obj.put("discount",dzk);//折扣
		
		return obj;
	
	}
	
	
	
	@RequestMapping(params="method=buy")
	@ResponseBody
	public  int getorder(HttpServletRequest request,double xmoney) {
		HttpSession session=request.getSession();
		int rid=0;
		int [] tt=(int [])session.getAttribute("tt");
		
		for (int i = 0; i < tt.length; i++) {
			int tid=tt[i];
			Outcp user=out.getOne(tid);
			rid=user.getRid();
			//得到rid;
		}
		
		//修改顾客的余额
		//得到顾客的rmoney;
		Member all=member.getOne(rid);
		double rmoney=all.getRmoney();
		double ymoney=rmoney-xmoney;
		if (ymoney<0) {
			return 0;
		} else {
			//修改订单状态
			for (int i = 0; i < tt.length; i++) {
				int tid=tt[i];
				Outcp user=out.getOne(tid);
				
				out.upporder(tid);//修改状态
			}
			
			all.setRmoney(ymoney);
			all.setRid(rid);
			member.uppmoney(all);
			
			
			//增加出库订单记录
			return 1;
		}
		
		
		
	}
	
	
	
	//出库记录
	@RequestMapping(params="method=allout")
	@ResponseBody
	public JSONObject getAlls(Outcp user) {
	
		List<Outcp> all=out.getAllout(user);
		int count=out.getcountout(user);
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	
	
	
	
}
