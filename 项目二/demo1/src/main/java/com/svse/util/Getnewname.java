package com.svse.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Getnewname {

	
	public String getNewName(String oldname) {

		String lastname = oldname.substring(oldname.lastIndexOf("."));
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyyMMddHHssmm");
		String time = ff.format(d);
		Random rad = new Random();
		int num = rad.nextInt(9999999);
		String newname = time + num + lastname;
		return newname;
	}
}
