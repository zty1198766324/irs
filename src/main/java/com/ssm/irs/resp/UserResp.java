package com.ssm.irs.resp;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class UserResp {
  private int code;
  private String msg;
  private int count;
  private List<UserRespData> data;

}
