package com.kst.clm.service.impl;


import com.kst.clm.entity.Detail;

import com.kst.clm.mapper.DetailMapper;
import com.kst.clm.service.DetailService;
import com.kst.common.base.service.impl.BaseService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class DetailServiceImple extends BaseService<DetailMapper, Detail> implements DetailService {


    public DataTable<Detail> selectClaimAll(DataTable dt) {
       dt.setRows(this.baseMapper.selectClaimAll);
        dt.setTotal(this.baseMapper.selectTotal(dt));
        return dt;
    }

    public int selectTotal(DataTable dt) {
        return this.baseMapper.selectTotal(dt);;
    }

    public Detail selectClaim(int did) {
        return this.baseMapper.selectDeatil(int did);
    }

    public void updateDatil(Detail detail) {
        this.baseMapper.updateDetail(Detail detail);
    }

    public void uppDatil(int did) {
        this.baseMapper.uppDatil(int did);
    }

    public void addDetail(Detail detail) {
        this.baseMapper.addDetail(Detail detail);
    }
}


