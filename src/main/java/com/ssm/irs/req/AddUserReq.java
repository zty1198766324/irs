package com.ssm.irs.req;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@ToString
public class AddUserReq {

  private String eMail;

  private String nickname;

  private String password;

  private String sex;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthday;

  //private String status;

  private String address;

  private String phone;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createTime;

}
