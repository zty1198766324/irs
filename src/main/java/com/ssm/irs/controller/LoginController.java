package com.ssm.irs.controller;

import com.google.code.kaptcha.Producer;

import com.ssm.irs.resp.LoginResp;
import com.ssm.irs.resp.RespIndex;
import com.ssm.irs.service.IMuneService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/sys")
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


  @Autowired
  private Producer captchaProducer = null;

//  @Autowired
//  private AdminServiceImpl adminService;

  @Autowired
  IMuneService iMenuServiceImpl;

  @RequestMapping("/vcode")
  public void getVcode(HttpServletRequest req, HttpServletResponse resp){
    logger.info("开始生成验证码");
    String text = captchaProducer.createText();
    BufferedImage image = captchaProducer.createImage(text);
    req.getSession().setAttribute("kaptcha",text);
    try {
      ImageIO.write(image, "JPEG", resp.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @ResponseBody
  @RequestMapping(value = "/login")
  public LoginResp login(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("vcode") String vcode, HttpServletRequest request){
    logger.info("用户登录请求参数loginReq:{}",vcode);

    if (StringUtils.isEmpty(vcode)){
      logger.info("验证码为空");
      return LoginResp.getSBai("验证码为空");
    }

    if (StringUtils.isEmpty(username)){
      logger.info("yonghuming为空");
      return LoginResp.getSBai("yonghuming为空");
    }


    if (StringUtils.isEmpty(password)){
      logger.info("mima为空");
      return LoginResp.getSBai("mima为空");
    }

    String sessionCode = (String)request.getSession().getAttribute("kaptcha");

    if(!vcode.equals(sessionCode)){
      return LoginResp.getSBai("输入验证码不正确");
    }

    /*=================shiro登录============================*/
    LoginResp loginResp = new LoginResp();

    Subject subject = SecurityUtils.getSubject();

    //MD5加密的密码
    String md5Password = new Md5Hash(password).toString();

    logger.info("加密后的密码"+md5Password);

    UsernamePasswordToken upt = new UsernamePasswordToken(username,md5Password);

    //判断是否登录
    if(!subject.isAuthenticated()){
      try {
        subject.login(upt);
      }catch (AuthenticationException a){
        logger.error("AuthenticationException",a);
        return LoginResp.getSBai("登陆失败");
      }
    }
    return LoginResp.getSucess();
//    Boolean falg= adminService.login(loginReq);
//
//    if(falg){
//      loginResp.setCode(1);
//      return LoginResp.getSucess();
//    }else {
//      return LoginResp.getSBai("登录失败");
//    }
  }

  @RequestMapping("/index")
  public String index(HttpServletRequest req){

    return "redirect:/index.jsp";
  }

  @RequestMapping("/main")
  public String loadMain(){
    System.out.println("===============================================");
    return "main";
  }

  /**
   * 登出
   * @return
   */
  @RequestMapping(value="/loginOut")
  public LoginResp loginOut(){

    Subject subject = SecurityUtils.getSubject();
    subject.logout();

    return LoginResp.getSBai("成功退出");
  }

  /**
   * 加载左边得下拉菜单
   */
  @RequestMapping("/getMenus")
  @ResponseBody
  public RespIndex loadLeft(){
    logger.info("进入到getMenus方法中");
    RespIndex respIndex=iMenuServiceImpl.queryMenus();
    return respIndex;
  }


}
