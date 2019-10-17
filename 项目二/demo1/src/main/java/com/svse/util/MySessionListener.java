package com.svse.util;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

	
	
	public static  AtomicInteger userCount=new AtomicInteger(0);
	//session登录创建新的session
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
		userCount.getAndIncrement();
	}

	
	//session登出后销毁session
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
		userCount.getAndDecrement();
	}

}
