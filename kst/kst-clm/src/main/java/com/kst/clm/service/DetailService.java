package com.kst.clm.service;

import com.kst.clm.entity.Detail;
import com.kst.common.base.service.IBaseService;

public interface DetailService extends IBaseService<Detail> {

    //查询总数据
    DataTable<Detail> selectDetailAll(DataTable dt);
    //total
    int selectTotal(DataTable dt);
    //单个数据明细
    Detail selectDeatil(int did);
    //修改数据明细
    void updateDetail(Detail detail);
    //删除报销单
    void uppDetail(int did);
    //报销单生成
    void addDetail(Detail detail);

}
