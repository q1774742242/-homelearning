package com.kst.clm.service.impl;


import com.kst.clm.entity.Claim;
import com.kst.clm.mapper.ClaimMapper;
import com.kst.clm.service.ClaimService;

import com.kst.clm.vo.Grouputil;
import com.kst.common.base.service.impl.BaseService;
import com.kst.common.base.vo.DataTable;
import com.kst.sys.api.entity.User;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClaimServiceImpl extends BaseService<ClaimMapper, Claim> implements ClaimService{

    @Override
    public DataTable<Claim> selectClaimAll(DataTable dt) {

        dt.setRows(this.baseMapper.selectClaimAll(dt));
        dt.setTotal(this.baseMapper.selectTotal(dt));

        return dt;
    }

    @Override
    public int selectTotal(DataTable dt) {
        return this.baseMapper.selectTotal(dt);
    }

    @Override
    public Claim selectClaim(int cid) {
        return this.baseMapper.selectClaim(cid);
    }

    @Override
    public void updateClaim(Claim claim) {
        this.baseMapper.updateClaim(claim);
    }

    @Override
    public void uppClaim(int cid) {
        this.baseMapper.uppClaim(cid);
    }

    @Override
    public List<User> selectGroupuser(int group_id) {
        return this.baseMapper.selectGroupuser(group_id);
    }

    @Override
    public Grouputil selectGroup(int user_id) {
        return this.baseMapper.selectGroup(user_id);
    }

    @Override
    public void addClaim(Claim claims) {
        this.baseMapper.addClaim(claims);
    }
}
