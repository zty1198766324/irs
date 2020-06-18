package com.ssm.irs.service;

import com.ssm.irs.pojo.User;
import com.ssm.irs.req.AddUserReq;
import com.ssm.irs.req.UpUserReq;
import com.ssm.irs.resp.UserRespData;

import java.util.List;

public interface IUserService {
  public List<UserRespData> queryAll();

  public Boolean insert(AddUserReq addReq);

  public User selUserByUid(Long id);

  public Boolean update(UpUserReq updeteReq);

  public Boolean delete(Long id);

  public List<UserRespData> queryLimit(Integer page,Integer limit);

  public int count();

}
