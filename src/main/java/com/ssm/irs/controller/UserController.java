package com.ssm.irs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.irs.pojo.User;
import com.ssm.irs.req.AddUserReq;
import com.ssm.irs.req.UpUserReq;
import com.ssm.irs.resp.*;
import com.ssm.irs.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private IUserService iUserService;

  @RequestMapping("/addUser")
  public String userAdd(){
    return "user/addUser";
  }

  @RequestMapping("/userList")
  public String userList(){

    return "user/userList";
  }

  @ResponseBody
  @RequestMapping("/getUserList")
  public UserResp queryAll(@RequestParam("page") Integer page,
                           @RequestParam("limit") Integer limit
                          ){

    //page=page-1;
    //List<UserRespData> userList= iUserService.queryLimit(page,limit);

    PageHelper.startPage(page,limit);

    List<UserRespData> userList= iUserService.queryAll();

    PageInfo<UserRespData> pageInfo = new PageInfo<UserRespData>(userList);

    UserResp userResp = new UserResp();
   logger.info("----queryAll----list:{}",userList.get(0).getEMail());
    if(CollectionUtils.isEmpty(userList)){
      return null;
    }
    userResp.setCode(0);
    userResp.setMsg("拿到数据");
    userResp.setCount(iUserService.count());
    userResp.setData(pageInfo.getList());
    return userResp;
  }

  @ResponseBody
  @RequestMapping("/insUser")
  public AddUserResp AddUser(AddUserReq addUserReq){
    AddUserResp addUserResp = new AddUserResp();
    Boolean falg= iUserService.insert(addUserReq);
    if(falg){
      addUserResp.setCode(0);
      addUserResp.setMsg("添加成功");
      addUserResp.setFlag(true);
    }else {
      addUserResp.setCode(-1);
      addUserResp.setMsg("添加失败");
      addUserResp.setFlag(false);
    }
    return addUserResp;
  }


  @RequestMapping("editUser/{uid}")
  public String editUser(@PathVariable("uid")String uid, Model model){
    User user=iUserService.selUserByUid(Long.parseLong(uid));
    model.addAttribute("user", user);
    return "user/editUser";
  }


  @ResponseBody
  @RequestMapping("/updUser")
  public UpdateUserResp update(UpUserReq upUserReq){
    UpdateUserResp updateUserResp = new UpdateUserResp();
    Boolean falg=iUserService.update(upUserReq);
    if(!falg){
      logger.info("修改成功了么？"+falg);
      updateUserResp.setCode(0);
      updateUserResp.setMsg("修改成功");
      UpUserResp upUserResp = new UpUserResp();
      upUserResp.setAddress(upUserReq.getAddress());
      upUserResp.setBirthday(upUserReq.getBirthday());
      upUserResp.setEMail(upUserReq.getEMail());
      upUserResp.setNickname(upUserReq.getNickname());
      upUserResp.setPhone(upUserReq.getPhone());
      upUserResp.setSex(upUserReq.getSex());
      updateUserResp.setUpUserResp(upUserResp);
    }else {
      updateUserResp.setCode(-1);
      updateUserResp.setMsg("修改失败");
    }
    return updateUserResp;
  }

  @ResponseBody
  @RequestMapping(value = "/delUserByUid/{uid}")
  public DelResp update(@PathVariable("uid")String id){
    logger.info("拿到的id为：id:{}",id);
    DelResp delMsg = new DelResp();
    boolean flag= iUserService.delete(Long.parseLong(id));
    if(!flag){
      delMsg.setCode(0);
      delMsg.setMsg("删除成功");
    }else {
      delMsg.setMsg("删除失败");
    }
    logger.info("删除是否成功：flag:{}",flag);
    return delMsg;
  }

}
