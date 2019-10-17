package com.svse.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.svse.entity.Car;
import com.svse.entity.Carxl;
import com.svse.entity.Grade;
import com.svse.entity.Member;
import com.svse.entity.Pcard;
import com.svse.service.Cardservice;
import com.svse.service.Carservice;
import com.svse.service.Carxlservice;
import com.svse.service.Gradeservice;
import com.svse.service.Memberservice;
import com.svse.util.Getnewname;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("member.do")
@Scope("prototype")
public class Membercontrol {

	
	
	@Value("${web.upload-path}")
	private String upload;
	
	@Autowired
	private Memberservice member;
	
	@Autowired
	private Carxlservice carxl;
	
	@Autowired
	private Cardservice card;
	
	@Autowired
	private Gradeservice grade;
	
	
	@Autowired
	private Carservice car;
	

	
	
	@RequestMapping(params="method=index")
	public String index() {
		
		return "member/all";
	}
	
	@RequestMapping(params="method=all")
	@ResponseBody
	public JSONObject getAll(int limit,int offset) {
		List<Member> all=member.getAll(offset, limit);
		
		for (Member m:all) {
			int x=m.getRsex();
			if (x==0) {
				m.setSex("男");
			} else {
				m.setSex("女");
			}
			
		}
		int count=member.getcount();
		JSONObject obj=new JSONObject();
		obj.put("rows",all);
		obj.put("total",count);
		return obj;
	}
	
	
	
	@RequestMapping(params="method=add")
	@ResponseBody
	public int  addmember(Member user,MultipartFile fimg) {
		
		
		String newname="";
		if (!fimg.isEmpty()) {
			try {
				//得到数据
				byte[] all=fimg.getBytes();
				
				//得到源文件地址
				String path=fimg.getOriginalFilename();
				//改名
			 Getnewname getnewname=new Getnewname();
				 newname=getnewname.getNewName(path);
				Path paths=Paths.get(upload+newname);
				Files.write(paths, all);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//设置提交时间
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
		String rtime = ff.format(d);
		
		user.setRstatus(1);//会员的状态
		user.setRtime(rtime);
		user.setRimg(newname);
		member.addMember(user);
		return 1;
	}
	

	
	@RequestMapping(params="method=one")
	@ResponseBody
	public Member getone(int rid) {
		Member user=member.getOne(rid);

		return user;
	}
	
	//传值
	@RequestMapping(params="method=myone")
	public String getmyone(int rid,Model model) {
		
		model.addAttribute("rid",rid);
		return "member/update";
	}
	
	@RequestMapping(params="method=update")
	@ResponseBody
	public int uppcptype(Member user,MultipartFile ximg) {
		
		String newname="";
		int rid=user.getRid();
		String imgname=(member.getOne(rid)).getRimg();
		String oldname=ximg.getOriginalFilename();
		
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
		
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyyMMddHHssmm");
		String rtime = ff.format(d);
		
		user.setRstatus(1);//会员的状态
		user.setRtime(rtime);
		user.setRimg(newname);
		member.updateMember(user);
		return 1;
		
	}
	
	
	
	@RequestMapping(params="method=myadd")
	public String myadd() {
	
		return "member/add";
	}
	
	
	//验证重名
	@RequestMapping(params="method=verifyrname")
	@ResponseBody
	public int verifycname(Member user) {
		int count=0;
		int rid=user.getRid();
		String rname=user.getRname();
		//查重名
		
		Member x=member.getrname(user);
		//原名
		String y=x.getRname();
		
		if (y.equalsIgnoreCase(rname)) {
			//与原名相同，不修改
			count=0;
		} else {
			int count1=member.verifyname(user);
			if (count1>0) {
				count=1;
			}else{
				count=0;
			}
		}
		
		return count;
	}
	
	
	@RequestMapping(params="method=getrname")
	@ResponseBody
	public int getrname(Member user) {
		
		int count=0;
		int count1=member.verifyname(user);;
		if (count1>0) {
			count=1;
		
		} else {
			count=0;
		}
		return count;

	}
	
	
	//验证会员kahao
	@RequestMapping(params="method=getrcard")
	@ResponseBody
	public int getrcard(Member user) {
		
		int count=0;
		int count1=member.verifycard(user);
		if (count1>0) {
			count=1;
		
		} else {
			count=0;
		}
		return count;

	}
	
	@RequestMapping(params="method=verifyrcard")
	@ResponseBody
	public int verifyrcard(Member user) {
		int count=0;
		int rid=user.getRid();
		String rcard=user.getRcard();
		//查重名
		
		Member x=member.getrcard(user);
		//原名
		String y=x.getRcard();
		
		if (y.equalsIgnoreCase(rcard)) {
			//与原名相同，不修改
			count=0;
		} else {
			int count1=member.verifycard(user);
			if (count1>0) {
				count=1;
			}else{
				count=0;
			}
		}
		
		return count;

	}
	
/******************下拉框*****************************/
	
	//增加下拉框
	//会员等级
	@RequestMapping(params="method=alld")
	@ResponseBody
	public List<Grade> getdname() {
		
		List<Grade> all=grade.all();
		return all;
				
	}
	//汽车品牌下拉框
	@RequestMapping(params="method=allc")
	@ResponseBody
	public List<Car> getallc() {
		
		List<Car> all=car.all();
		return all;
				
	}
	
	
	
	//汽车系列下拉框
	@RequestMapping(params="method=allx")
	@ResponseBody
	public List<Carxl> getxname(int aid) {
		
		List<Carxl> all=carxl.getallxl(aid);
		return all;
				
	}
	
	//凭证下拉框
	@RequestMapping(params="method=allcard")
	@ResponseBody
	public List<Pcard> getcard() {
		
		List<Pcard> all=card.all();
		return all;
				
	}
	
	
	/**************************************************/
	//修改凭证下拉框
		@RequestMapping(params="method=uppcard")
		@ResponseBody
		public List<Pcard> uppc(int rid) {
			Member user=member.getOne(rid);
			int zid=user.getZid();
			List<Pcard> ar=new ArrayList<Pcard>();
			List<Pcard> arr=card.all();
			for (Pcard p:arr) {
				int x=p.getZid();
				if (x==zid) {
					ar.add(0,p);
				} else {
					ar.add(p);
				}
			}
		
			return ar;
					
		}
	
	//修改等级下拉框
		@RequestMapping(params="method=uppd")
		@ResponseBody
		public List<Grade> uppd(int rid) {
			Member user=member.getOne(rid);
			int did=user.getDid();
			List<Grade> ar=new ArrayList<Grade>();
			List<Grade> arr=grade.all();
			for (Grade p:arr) {
				int x=p.getDid();
				if (x==did) {
					ar.add(0,p);
				} else {
					ar.add(p);
				}
			}
		
			return ar;
					
		}
		
		
		//修改汽车品牌,系列下拉框下拉框
		@RequestMapping(params="method=uppc")
		@ResponseBody
		public List<Car> uppcar(int rid) {
			Member user=member.getOne(rid);
			int xid=user.getXid();
			Carxl cars=carxl.getOne(xid);//得到汽车系列
			int aid=cars.getAid();//得到汽车品牌
			
			
			List<Car> ar=new ArrayList<Car>();
			List<Car> arr=car.all();
			for (Car c:arr) {
				int x=c.getAid();
				if (x==aid) {
					ar.add(0,c);
				} else {
					ar.add(c);
				}
			}
		
			return ar;
					
		}
		
		//修改汽车系列下拉框下拉框
				@RequestMapping(params="method=uppxl")
				@ResponseBody
				public List<Carxl> uppxl(int rid) {
					Member user=member.getOne(rid);
					int xid=user.getXid();//得到汽车系列编号
					

					List<Carxl> ar=new ArrayList<Carxl>();
					List<Carxl> arr=carxl.all();
					for (Carxl c:arr) {
						int x=c.getXid();
						if (x==xid) {
							ar.add(0,c);
						} else {
							ar.add(c);
						}
					}
				
					return ar;
							
				}
				
		//页面详细		
				@RequestMapping(params="method=look")
				public String getlook(int rid,Model model) {
					
					model.addAttribute("rid",rid);
					return "member/look";
				}	

				
				
				
		
		
}
