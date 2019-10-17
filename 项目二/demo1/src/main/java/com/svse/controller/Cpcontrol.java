package com.svse.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.svse.entity.Cpmessage;
import com.svse.entity.Cptype;
import com.svse.service.Cpmeservice;
import com.svse.service.Ctypeservice;
import com.svse.util.Getnewname;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("cpinfo.do")
@Scope("prototype")
public class Cpcontrol {

	
	@Value("${web.upload-path}")
	private String upload;
	
	@Autowired
	private Cpmeservice cpinfo;
	
	
	@Autowired
	private Ctypeservice type;
	@RequestMapping(params="method=index")
	public String index() {
		
		return "cpinfo/all";
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
	
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addcpinfo(Cpmessage user,MultipartFile  ximg) {

		String newname="";
		if (!ximg.isEmpty()) {
			try {
				//创建文件夹
				System.out.println(upload);
				File files=new File(upload);
				if (!files.exists()||!files.isDirectory()) {
					files.mkdir();
				} else {
					System.out.println("存在!");
				}
				//得到数据
				byte[] all=ximg.getBytes();
				
				//得到源文件地址
				String path=ximg.getOriginalFilename();
				
				//改名
			 Getnewname getnewname=new Getnewname();
				 newname=getnewname.getNewName(path);
				Path paths=Paths.get(upload+newname);
				Files.write(paths, all);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	
		
		
		user.setFimg(newname);
		cpinfo.addCpmessage(user);
		return 1;
			
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Cpmessage getone(int fid) {
		Cpmessage user=cpinfo.getOne(fid);
	
		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int fid,Model model) {
		
		model.addAttribute("fid",fid);
		return "cpinfo/update";
	}
	
	
	@RequestMapping(params="method=upp")
	@ResponseBody
	public int uppcptype(Cpmessage user,MultipartFile ximg) {
		String newname="";
		int fid=user.getFid();
		String imgname=(cpinfo.getOne(fid)).getFimg();
		String oldname=ximg.getOriginalFilename();
		
		if (oldname.length()==0) {
			newname=imgname;//不上传新的，保持原有的
		} else {
			//删除旧文件
			System.out.println(upload);
			File files=new File(upload);
			if (!files.exists()||!files.isDirectory()) {
				files.mkdir();
			} else {
				System.out.println("存在!");
			}
			String xpath=upload+imgname;
			File file=new File(xpath,imgname);
			file.delete();
			//存新文件
			//得到存放路径
			
			try {
				//得到数据
				byte[] all=ximg.getBytes();
				
				//得到源文件地址
				String path=ximg.getOriginalFilename();
				//改名
				Getnewname getnewname=new Getnewname();
				 newname=getnewname.getNewName(path);
				Path paths=Paths.get(upload+newname);
				Files.write(paths, all);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		user.setFimg(newname);
		cpinfo.updateCpmessage(user);
		return 1;
		
	}
	
	
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "cpinfo/add";
	}
	
	
	//验证重名
	@RequestMapping(params="method=verifyfname")
	@ResponseBody
	public int verifycname(Cpmessage user) {
		int count=0;
		int fid=user.getFid();
		String fname=user.getFname();
		//查重名
		
		Cpmessage x=cpinfo.getname(user);
		//原名
		String y=x.getFname();
		
		if (y.equalsIgnoreCase(fname)) {
			//与原名相同，不修改
			count=0;
		} else {
			int count1=cpinfo.verifyname(user);
			if (count1>0) {
				count=1;
			}else{
				count=0;
			}
		}
		
		return count;
	}
	
	
	@RequestMapping(params="method=getfname")
	@ResponseBody
	public int getcname(Cpmessage user) {
		
		int count=0;
		int count1=cpinfo.verifyname(user);
		if (count1>0) {
			count=1;
		
		} else {
			count=0;
		}
		return count;

	}
	
	
	//下拉框产品类型
	@RequestMapping(params="method=allc")
	@ResponseBody
	public List<Cptype> getcname() {
		List<Cptype> all=type.all();
		return all;
	
	}
	
	
	//单位下拉框
	@RequestMapping(params="method=alltype")
	@ResponseBody
	public List<Cpmessage> getmodel() {
		List<Cpmessage> all=new ArrayList<Cpmessage>();
		
		for (int i = 0; i < 3; i++) {
			Cpmessage user=new Cpmessage();
			if (i==0) {
				user.setModel(0);
				user.setFmodel("个");
			} else if (i==1) {
				user.setModel(1);
				user.setFmodel("瓶");
			}else {
				user.setModel(2);
				user.setFmodel("盒");
			}
			
			all.add(user);
		}
		return all;

	}
	
	
	//修改下拉框
	
	@RequestMapping(params="method=upptype")
	@ResponseBody
	public List<Cpmessage> getmodel1(int fid) {
		
		Cpmessage user=cpinfo.getOne(fid);
		int model=user.getModel();
		List<Cpmessage> all=new ArrayList<Cpmessage>();
		
		for (int i = 0; i < 3; i++) {
			
			if (model==0) {
				user.setModel(0);
				user.setFmodel("个");
			} else if (model==1) {
				user.setModel(1);
				user.setFmodel("瓶");
			}else {
				user.setModel(2);
				user.setFmodel("盒");
			}
			
			all.add(user);
		}
		return all;

	}
	
	//修改的cname
	@RequestMapping(params="method=uppc")
	@ResponseBody
	public List<Cptype> uppcname(int fid) {
		
		Cpmessage user=cpinfo.getOne(fid);
		int cid=user.getCid();
		List<Cptype> all=new ArrayList<Cptype>();
		
		List<Cptype> arr=type.all();
		for (Cptype c:arr) {
			int x=c.getCid();
			if (x==cid) {
				
				all.add(0,c);
			} else {
				all.add(c);
			}
		}
		
		
		return all;
	
	}
	
}
