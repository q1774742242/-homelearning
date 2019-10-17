package com.svse.util;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

public class Lisentersession {

	public static  int userCount=0;
	
	//getsession
	public int getsession(HttpSession session) {
		if (session!=null) {
			userCount++;
			return userCount;
		} else {
			return userCount;
		}
		
	}
	
	//distroysession
	public int distroysession(HttpSession session) {
		if (session!=null) {
			userCount--;
			return userCount;
		} else {
			
			return userCount;
		}

	}
	
	
}
