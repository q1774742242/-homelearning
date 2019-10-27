package com.kst.clm.service;

import com.kst.clm.entity.Claim;

import com.kst.clm.vo.Grouputil;
import com.kst.common.base.service.IBaseService;
import com.kst.common.base.vo.DataTable;
import com.kst.sys.api.entity.User;

import java.util.List;

public interface ClaimService extends IBaseService<Claim> {
    //查询总数据
    DataTable<Claim> selectClaimAll(DataTable dt);
    //total
    int selectTotal(DataTable dt);
    //单个数据明细
    Claim selectClaim(int cid);
    //修改数据明细
    void updateClaim(Claim claim);
    //取消报销的申请
    void uppClaim(int cid);
    //查询小组中的成员
    List<User> selectGroupuser(int group_id);
    //根据名字查询所在小组部门
    Grouputil selectGroup(int user_id);

    void addClaim(Claim claims);
}
