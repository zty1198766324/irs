package com.ssm.irs.service.impl;

import com.ssm.irs.mapper.UserMapper;
import com.ssm.irs.pojo.User;
import com.ssm.irs.req.AddUserReq;
import com.ssm.irs.req.UpUserReq;
import com.ssm.irs.resp.UserRespData;
import com.ssm.irs.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserMapper userMapper;

  @Override
  public List<UserRespData> queryAll() {
    List<User> users= userMapper.queryAll();
    if(CollectionUtils.isEmpty(users)){
      logger.info("没有获取到数据");
    }
    List<UserRespData> list=new ArrayList<UserRespData>();
    for(User user:users){
      UserRespData urd=new UserRespData();
      urd.setUid(user.getUid());
      urd.setAddress(user.getAddress());
      urd.setBirthday(user.getBirthday());
      urd.setEMail(user.geteMail());
      urd.setNickname(user.getNickname());
      urd.setPhone(user.getPhone());
      urd.setSex(user.getSex());
      urd.setStatus(user.getStatus());
      urd.setCreateTime(user.getCreateTime());
      list.add(urd);
    }
    System.out.println(list.get(0).getEMail());
    return list;
  }


  @Override
  public List<UserRespData> queryLimit(Integer page, Integer limit) {
    List<User> users= userMapper.queryAllLimit(page,limit);
    if(CollectionUtils.isEmpty(users)){
      logger.info("没有获取到数据");
    }
    List<UserRespData> list=new ArrayList<UserRespData>();
    for(User user:users){
      UserRespData urd=new UserRespData();
      urd.setUid(user.getUid());
      urd.setAddress(user.getAddress());
      urd.setBirthday(user.getBirthday());
      urd.setEMail(user.geteMail());
      urd.setNickname(user.getNickname());
      urd.setPhone(user.getPhone());
      urd.setSex(user.getSex());
      urd.setStatus(user.getStatus());
      urd.setCreateTime(user.getCreateTime());
      list.add(urd);
    }
    System.out.println(list.get(0).getEMail());
    return list;
  }

  @Override
  public int count() {
    return userMapper.count();
  }


  @Override
  public Boolean insert(AddUserReq addReq) {
    User user=new User();
    user.seteMail(addReq.getEMail());
    user.setNickname(addReq.getNickname());
    user.setAddress(addReq.getAddress());
    user.setPassword(addReq.getPassword());
    user.setPhone(addReq.getPhone());
    user.setSex(addReq.getSex());
    user.setStatus("1");
    user.setBirthday(addReq.getBirthday());
    user.setCreateTime(new Date());
    int rest=userMapper.insert(user);
    if(rest>0){
      return true;
    }else {
      return false;
    }
  }

  @Override
  public User selUserByUid(Long id) {
    User user=userMapper.selectByPrimaryKey(id);
    return user;
  }

  @Override
  public Boolean update(UpUserReq updeteReq) {
    User user = new User();
    user.seteMail(updeteReq.getEMail());
    user.setBirthday(updeteReq.getBirthday());
    user.setSex(updeteReq.getSex());
    user.setNickname(updeteReq.getNickname());
    user.setPhone(updeteReq.getPhone());
    user.setAddress(updeteReq.getAddress());
    user.setUid(updeteReq.getUid());
    int rest= userMapper.updateByPrimaryKeySelective(user);
    if(rest>0){
      return true;
    }else {
      return false;
    }
  }

  @Override
  public Boolean delete(Long id) {
    int rt= userMapper.deleteByPrimaryKey(id);
    if(rt>0){
      return true;
    }else {
      return false;
    }
  }

}
