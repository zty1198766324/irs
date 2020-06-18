package com.ssm.irs.resp;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class UserRespData {
  private Long uid;

  private String eMail;

  private String nickname;

  private String sex;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthday;

  private String address;

  private String phone;

  private String status;//状态
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createTime;
}
