package com.ssm.irs.service.impl;

import com.ssm.irs.mapper.AdminMapper;
import com.ssm.irs.pojo.Admin;
import com.ssm.irs.req.LoginReq;
import com.ssm.irs.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AdminServiceImpl implements IAdminService {

  @Autowired
  private AdminMapper adminMapper;


  @Override
  public Boolean login(LoginReq loginReq) {
    Admin admin = adminMapper.selectByUserName(loginReq.getUsername());
    if(!StringUtils.isEmpty(loginReq.getUsername()) && admin.getUsername().equals(loginReq.getUsername())&&
      !StringUtils.isEmpty(loginReq.getPassword()) && admin.getPassword().equals(loginReq.getPassword())){
      return true;
    }else {
      return false;
    }
  }
}
