package com.ssm.irs.mapper;

import com.ssm.irs.pojo.RolesMenusKey;

public interface RolesMenusMapper {
    int deleteByPrimaryKey(RolesMenusKey key);

    int insert(RolesMenusKey record);

    int insertSelective(RolesMenusKey record);
}