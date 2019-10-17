package com.svse.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svse.service.Mailservice;

@Controller
@RequestMapping("mail.do")
@Scope("prototype")
public class Mailcontrol {
		
	
	@Autowired
	private Mailservice mailservice;
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

    
    
}
