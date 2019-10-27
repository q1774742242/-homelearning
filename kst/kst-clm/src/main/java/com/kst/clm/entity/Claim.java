package com.kst.clm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kst.clm.vo.Grouputil;


import java.util.Date;

@TableName("clm_claim")
public class Claim  extends Grouputil {

    private static final long serialVersionUID = 1L;
    @TableField("cid")
    //申请编号
    private int cid;
    //申请状态
    @TableField("clm_status")
    private String clm_status;
    //申请人编号
    @TableField("clm_userid")
    private int clm_userid;
    //申请日期
    @TableField("clm_applydate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date clm_applydate;
    //申请部门
    @TableField("clm_departid")
    private int clm_departid;
    //备注
    @TableField("remarks")
    private  String remarks;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getClm_status() {
        return clm_status;
    }

    public void setClm_status(String clm_status) {
        this.clm_status = clm_status;
    }

    public int getClm_userid() {
        return clm_userid;
    }

    public void setClm_userid(int clm_userid) {
        this.clm_userid = clm_userid;
    }

    public Date getClm_applydate() {
        return clm_applydate;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setClm_applydate(Date clm_applydate) {
        this.clm_applydate = clm_applydate;
    }

    public int getClm_departid() {
        return clm_departid;
    }

    public void setClm_departid(int clm_departid) {
        this.clm_departid = clm_departid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
