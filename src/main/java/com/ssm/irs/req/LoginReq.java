package com.ssm.irs.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class LoginReq {
  private String username;
  private String password;
  private String vcode;
}
