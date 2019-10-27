package com.kst.clm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kst.clm.entity.Claim;
import com.kst.clm.vo.Grouputil;
import com.kst.common.base.vo.DataTable;
import com.kst.sys.api.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClaimMapper extends BaseMapper<Claim> {
    //查询总数据
    List<Claim> selectClaimAll(DataTable dt);
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
