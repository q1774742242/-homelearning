package com.kst.clm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kst.clm.vo.Grouputil;


import java.util.Date;

@TableName("clm_detail")
public class Detail  extends Grouputil {
    private static final long serialVersionUID = 1L;


    /*申请编号*/
    @TableField("did")
    private  int did;

    //发票类型
    @TableField("clm_type")
    private String clm_type;
    //发票状态
    @TableField("clm_status")
    private int clm_status;
    //登陆方式
    @TableField("clm_inputtype")
    private String clm_inputtype;
    //报销单编号
    @TableField("claim_no")
    private int claim_no;
    //登录日期
    @TableField("clm_inputdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date clm_inputdate;
    //发票代码
    @TableField("clm_invoiceNum")
    private int clm_invoiceNum;
    //发票号码
    @TableField("clm_invoiceCode")
    private int clm_invoiceCode;
    //报销日期
    @TableField("clm_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date clm_date;
    //总金额
    @TableField("clm_price")
    private double clm_price;
    //补充说明
    @TableField("clm_memo")
    private String clm_memo;
    //申请记录删除
    @TableField("del_flag")
    private int del_flag;

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getClm_type() {
        return clm_type;
    }

    public void setClm_type(String clm_type) {
        this.clm_type = clm_type;
    }

    public int getClm_status() {
        return clm_status;
    }

    public void setClm_status(int clm_status) {
        this.clm_status = clm_status;
    }

    public String getClm_inputtype() {
        return clm_inputtype;
    }

    public void setClm_inputtype(String clm_inputtype) {
        this.clm_inputtype = clm_inputtype;
    }

    public int getClaim_no() {
        return claim_no;
    }

    public void setClaim_no(int claim_no) {
        this.claim_no = claim_no;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getClm_inputdate() {
        return clm_inputdate;
    }

    public void setClm_inputdate(Date clm_inputdate) {
        this.clm_inputdate = clm_inputdate;
    }

    public int getClm_invoiceNum() {
        return clm_invoiceNum;
    }

    public void setClm_invoiceNum(int clm_invoiceNum) {
        this.clm_invoiceNum = clm_invoiceNum;
    }

    public int getClm_invoiceCode() {
        return clm_invoiceCode;
    }

    public void setClm_invoiceCode(int clm_invoiceCode) {
        this.clm_invoiceCode = clm_invoiceCode;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getClm_date() {
        return clm_date;
    }

    public void setClm_date(Date clm_date) {
        this.clm_date = clm_date;
    }

    public double getClm_price() {
        return clm_price;
    }

    public void setClm_price(double clm_price) {
        this.clm_price = clm_price;
    }

    public String getClm_memo() {
        return clm_memo;
    }

    public void setClm_memo(String clm_memo) {
        this.clm_memo = clm_memo;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }
}
