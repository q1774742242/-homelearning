package com.svse.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("emp.do")
@Scope("prototype")
public class Empcontrol {
	
	
	
	private static String upload="D:/workspace/demo1/src/main/resources/static/upload/";

	@RequestMapping(params="method=index")
	public String getindex() {

		return "main/mainframe";

	}

	
	/*
	@RequestMapping(params="method=add")
	public String add(Emp user) {
		String newname="";
		if (!user.getXimg().isEmpty()) {
			try {
				//得到数据
				byte[] all=user.getXimg().getBytes();
				
				//得到源文件地址
				String path=user.getXimg().getOriginalFilename();
				//改名
				 newname=getNewName(path);
				Path paths=Paths.get(upload+newname);
				Files.write(paths, all);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		user.setEimg(newname);
		emp.addEmp(user);
		return "redirect:/emp.do?method=all";
	}
	
	
	

	
	
	@RequestMapping(params="method=upp")
	private String update(Emp user) {
		
		String oldname="";
		String newname="";
		int eid=user.getEid();
		String imgname=(emp.getOne(eid)).getEimg();
	
		oldname=user.getXimg().getOriginalFilename();
		
		if (oldname.length()==0) {
			newname=imgname;//不上传新的，保持原有的
		} else {
			//删除旧文件
			String xpath=upload+imgname;
			File file=new File(xpath,imgname);
			file.delete();
			//存新文件
			//得到存放路径
			
			try {
				//得到数据
				byte[] all=user.getXimg().getBytes();
				
				//得到源文件地址
				String path=user.getXimg().getOriginalFilename();
				//改名
				 newname=getNewName(path);
				Path paths=Paths.get(upload+newname);
				Files.write(paths, all);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
			
	
		user.setEimg(newname);
		emp.updateEmp(user);
		return "redirect:/emp.do?method=all";
	}
	
	
	@RequestMapping(params="method=myadd")
	private String allp(Model model) {
		List<Dept> all=dept.getAll();
		
		model.addAttribute("all",all);
		return "emp/add";
	}
	
	@RequestMapping(params="method=myupp")
	private String upp(Model model,int eid) {
		
		Emp user=emp.getOne(eid);
		int pid=user.getPid();
		List<Dept> arr=new ArrayList<Dept>();
		List<Dept> all=dept.getAll();
		for (Dept x:all) {
			int y=x.getPid();
			if (y==pid) {
				
				arr.add(0,x);
			} else {
				arr.add(x);
			}
		}
		model.addAttribute("user",user);
		model.addAttribute("all",arr);
		return "emp/update";
	}
	
	
	//改名字
	private String getNewName(String oldname) {

		String lastname = oldname.substring(oldname.lastIndexOf("."));
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyyMMddHHssmm");
		String time = ff.format(d);
		Random rad = new Random();
		int num = rad.nextInt(9999999);
		String newname = time + num + lastname;
		return newname;
	}*/
	

}
