/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.kst.common.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;


/**
 * Entity支持类
 *
 * @param <T>
 */

public abstract class BaseTreeEntity<T extends Model> extends Model<T>  {



    /**
     * 实体编号（唯一标识）
     */
    @TableId(type= IdType.INPUT)
    protected Long id;

    public BaseTreeEntity() {

    }

    public BaseTreeEntity(Long id) {
        this();
        this.id = id;
    }
    @JsonSerialize(using=ToStringSerializer.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseTreeEntity<?> that = (BaseTreeEntity<?>) obj;
        return null != this.getId() && this.getId().equals(that.getId());
    }




}
