package com.ssm.irs.mapper;

import com.ssm.irs.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    //获取用户信息
    Admin selectByUserName(@Param("username") String userName);

    //获取当前用户得权限信息
    List<String> selectByPerms(@Param("username") String userName);

}
