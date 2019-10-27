package com.kst.clm.controller;




import com.fasterxml.jackson.annotation.JsonFormat;
import com.kst.clm.entity.Claim;
import com.kst.clm.service.ClaimService;
import com.kst.clm.vo.Grouputil;
import com.kst.common.base.vo.DataTable;


import com.kst.sys.api.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("clm/claim")
@Scope("prototype")
public class ClaimController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClaimController.class);

    @JsonFormat(pattern = "yyyy-mm-dd",timezone = "GMT+8")
    private   Date d=new Date();
    java.sql.Date date=new java.sql.Date(d.getTime());


    @Autowired
    private ClaimService claimservice;
    @GetMapping("list")
    @RequiresPermissions("clm:claim:list")
    public String list() {

        return "claim/list";
    }


    //查询列表
    @PostMapping("list")
    @ResponseBody
    public DataTable<Claim> selectClaimAll(@RequestBody DataTable dataTable){

        DataTable<Claim> list = this.claimservice.selectClaimAll(dataTable);
        return list;

    }
    //查询单个数据
    @PostMapping("one")
    @ResponseBody
    public Claim selectClaim(int cid){
        Claim claims=claimservice.selectClaim(cid);

        return claims;
    }

    //跳转到增加页面
    @GetMapping("TurnAdd")
    public String TurntoAdd(){

        return "claim/addClaim";
    }
    //跳转到修改页面
    @GetMapping("TurnUpp/{cid}")
    public String TurntoUpp(Model model, @PathVariable("cid")int cid){
        Claim claims=claimservice.selectClaim(cid);
        model.addAttribute("claim",claims);
        return "claim/uppClaim";
    }
    //提交删除
    @PostMapping("dele")
    @ResponseBody
    @RequiresPermissions("clm:claim:delete")
    public int  editFlag(int cid){
        Claim claim=claimservice.selectClaim(cid);
        if (claim.getDelFlag()==false){
            LOGGER.info("编号"+cid+",此记录已经被删除");
        }else{
            claimservice.uppClaim(cid);
            LOGGER.info("已经将编号"+cid+",此记录删除成功");
        }

        return 1;
    }
    //删除多组数据
    @PostMapping("deles")
    @ResponseBody
    @RequiresPermissions("clm:claim:delete")
    public int  editFlags(String [] cids){
        int cid=0;
        for (int i = 0; i <cids.length; i++) {
            cid=Integer.parseInt(cids[i]);
            Claim claim=claimservice.selectClaim(cid);
            if (claim.getDelFlag()==false){
                LOGGER.info("编号"+cid+",此记录已经被删除");
            }else{
                claimservice.uppClaim(cid);
                LOGGER.info("已经将编号"+cid+",此记录删除成功");

            }

        }

        return 1;
    }
    //修改
    @PostMapping("uppclaim")
    @ResponseBody
    public int updateClaim(Claim claims){
        //提交修改时间

        claims.setUpdateDate(date);
        claimservice.updateClaim(claims);
        return 1;
    }
    //增加
    @PostMapping("addclaim")
    @ResponseBody
    public int addClaim(Claim claims){
        //增加提交时间

        claims.setCreateDate(date);

        claimservice.updateClaim(claims);
        return 1;
    }

    //显示申请人
    @GetMapping("Applyuser/{group_id}")
    @ResponseBody
    public List<User> getUser(@PathVariable("group_id")int group_id){
        List<User> user=claimservice.selectGroupuser(group_id);
        return user;
    }



    //显示小组
    @GetMapping("Applygroup/{user_id}")
    @ResponseBody
    public Grouputil  getgroup(@PathVariable("user_id")int user_id){
        Grouputil group=claimservice.selectGroup(user_id);
        return group;
    }

}
