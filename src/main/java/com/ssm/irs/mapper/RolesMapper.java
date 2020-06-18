package com.ssm.irs.mapper;

import com.ssm.irs.pojo.Roles;

public interface RolesMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}