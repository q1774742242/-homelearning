package com.kst.clm.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("clm/detail")
@Scope("prototype")
public class DetailController {

    @GetMapping("list")
    @RequiresPermissions("clm:detail:list")
    private String getall(){
        System.out.println("进来了");
        return "detail/list";
    }



}
