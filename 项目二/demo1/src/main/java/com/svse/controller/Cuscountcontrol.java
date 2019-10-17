package com.svse.controller;

import java.net.URLEncoder;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





@Controller
@RequestMapping("cus.do")
@Scope("prototype")
/*
 * 实时统计客户访问流量
 * 和客户在线人数
 * 
 */
public class Cuscountcontrol {


	@RequestMapping(params="method=index")
	public String index() {
		
		return "cus/all";
	}
	
	
	
	@RequestMapping(params="method=count")
    @ResponseBody
	 public int number(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
	        try{  //把sessionId记录在浏览器
	            Cookie c = new Cookie("JSESSIONID", URLEncoder.encode(httpServletRequest.getSession().getId(), "utf-8"));
	            c.setPath("/");
	            //先设置cookie有效期为2天，不用担心，session不会保存2天
	            c.setMaxAge( 48*60 * 60);
	            httpServletResponse.addCookie(c);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	  
	        HttpSession session = httpServletRequest.getSession();
	        int count=(Integer)session.getAttribute("count");
	        System.out.println(count);
	        return count;
	    }
	
	
	
	
	
}
