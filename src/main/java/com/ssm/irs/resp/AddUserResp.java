package com.ssm.irs.resp;

import com.ssm.irs.req.AddUserReq;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddUserResp {
  private int code;
  private String msg;
  private Boolean flag;
  private AddUserReq addUserReq;
}
