package com.ssm.irs.service.impl;

import com.ssm.irs.mapper.MenusMapper;
import com.ssm.irs.pojo.Menus;
import com.ssm.irs.resp.RespInData;
import com.ssm.irs.resp.RespIndex;
import com.ssm.irs.service.IMuneService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class MenuServiceImpl implements IMuneService {

  @Autowired
  MenusMapper menusMapper;

  @Override
  public RespIndex queryMenus() {

    RespIndex resp = new RespIndex();  // 成功失败

    List<Menus> menuList= menusMapper.queryMenus();
    //判断非空
    if(StringUtils.isEmpty(menuList)){
      return resp;
    }

    //获取到目录信息
    List<RespInData> data = new ArrayList<RespInData>();
    for(Menus menus:menuList){
      RespInData respData = new RespInData(); //一级

      BeanUtils.copyProperties(menus,respData);

      Long menuId= menus.getMenuId();

      List<RespInData> list =  new ArrayList<RespInData>();
      List<Menus> menuChild = menusMapper.queryChildMenus(menuId);
      for(Menus m:menuChild){
        RespInData respInData = new RespInData();//二级
        BeanUtils.copyProperties(m,respInData);
        list.add(respInData);
      }
      respData.setList(list);
      data.add(respData);
    }
    resp.setData(data);
    resp.setCode(0);
    resp.setMsg("请求数据成功");
    return resp;
  }
}
