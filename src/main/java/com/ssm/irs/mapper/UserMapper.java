package com.ssm.irs.mapper;

import com.ssm.irs.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> queryAll();

    int update(User user);

  List<User> queryAllLimit(Integer page,Integer limit);

  int count();
}
