package com.ssm.irs.resp;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class RespIndex {

  private int code;

  private String msg;

  private List<RespInData> data;
}
