package com.svse.controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svse.entity.Userinfo;
import com.svse.service.Mailservice;
import com.svse.service.Userservice;
import com.svse.util.Lisentersession;
import com.svse.util.MySessionListener;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("user.do")
@Scope("prototype")
public class Usercontrol {

	@Autowired
	private Userservice user;
	
	@Autowired
	private Mailservice mailservice;
	@RequestMapping(params="method=index")
	public String index(int begin) {
		if (begin==0) {
			
			return "main/main";
		} else {
			return "user/all";
		}
		
	}
	
	
	@RequestMapping(params="method=index1")
	public String getindex(int begin) {
		if (begin==0) {
			return "index";
		} else {
			return "main/error";
		}
		

	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		
		List<Userinfo> all=user.getAll(offset, limit);
		for (Userinfo c:all) {
			int x=c.getUsex();
			if (x==1) {
				c.setSex("男");
			} else {
				c.setSex("女");
			}
		}
		
		int count=user.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  adduser(Userinfo users) {
		user.addUserinfo(users);
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Userinfo getone(int uid) {
		Userinfo users=user.getOne(uid);
		System.out.println(uid);
		return users;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int uid,Model model) {
		
		model.addAttribute("uid",uid);
		return "user/update";
	}
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Userinfo users) {
		user.updateUserinfo(users);
		return 1;
		
	}
	
	
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "user/add";
	}
	
	
	//验证重名
	@RequestMapping(params="method=verifyuname")
	@ResponseBody
	public int verifycname(Userinfo users) {
		int count=0;
		int uid=users.getUid();
		String uname=users.getUname();
		//查重名
		
		Userinfo x=user.getuname(users);
		//原名
		String y=x.getUname();
		
		if (y.equalsIgnoreCase(uname)) {
			//与原名相同，不修改
			count=0;
		} else {
			int count1=user.verifyname(users);
			if (count1>0) {
				count=1;
			}else{
				count=0;
			}
		}
		
		return count;
	}
	
	
	@RequestMapping(params="method=getuname")
	@ResponseBody
	public int getcname(Userinfo users) {
		
		int count=0;
		int count1=user.verifyname(users);
		if (count1>0) {
			count=1;
		
		} else {
			count=0;
		}
		return count;

	}
	
	//登录
	@RequestMapping(params="method=login")
	@ResponseBody
	public int getlogin(Userinfo users) {
		//做验证账户存在
		String uname=users.getUname();
		int count1=user.getempty(uname);
	
		if (count1==0) {
			return 0;
		} else{
			
			return 1;
		}
		
	

	}
	
	@RequestMapping(params="method=getlogin")
	@ResponseBody
	public int getlogin1(Userinfo users,HttpServletRequest request) {
	
		int count=user.getlogin(users);
	
		if (count==0) {
			
			return 0;//账号密码错误
		} else {
			Userinfo arr=user.getuser(users);
			
			HttpSession session=request.getSession(true);
			session.setAttribute("user",arr);
			Lisentersession usercount=new Lisentersession();
			int count1=usercount.getsession(session);
			session.setAttribute("count",count1);
			
			return 1;
		}
		

	}
	
	//登录出去
	@RequestMapping(params="method=loginout")
	public String loginout(HttpServletRequest request) {
		
		//销毁session
		HttpSession session=request.getSession(true);
		Lisentersession usercount=new Lisentersession();
		int count=usercount.distroysession(session);
		session.setAttribute("count",count);
		session.invalidate();
		
		return "redirect:/user.do?method=index1&begin=0";
	}
	
	
	
	
	
	
	@RequestMapping(params="method=getCheckCode")
    @ResponseBody
    public String getCheckCode(String email){
		
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        try {
        	mailservice.sendSimpleMail(email, "注册验证码", message);
        }catch (Exception e){
            return "";
        }
        return checkCode;
    }
	
	
	@RequestMapping(params="method=register")
    @ResponseBody
    public int getregister(Userinfo users) {
		System.out.println(users.getEmail()+users.getUname()+users.getUpsw());
		user.getregister(users);
		System.out.println("注册成功");
		return 1;
	}
	
	
}
