package com.ssm.irs.resp;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@ToString
public class UpUserResp {
  private String eMail;
  private String nickname;
  private String sex;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthday;
  private String address;
  private String phone;
}
