package com.ssm.irs.resp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginResp {

  private int code = 0;

  private String msg = "请求成功";

  /**
   * 成功返回
   * @return
   */
  public static LoginResp getSucess(){
    return new LoginResp();
  }

  /**
   * 失败返回
   * @param msg
   * @return
   */
  public static LoginResp getSBai(String msg){
    LoginResp respLogin =  new LoginResp();
    respLogin.setCode(-1);
    respLogin.setMsg(msg);
    return  respLogin;
  }


}
