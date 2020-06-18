package com.ssm.irs.resp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateUserResp {
  private int code;
  private String msg;
  private UpUserResp upUserResp;

}
