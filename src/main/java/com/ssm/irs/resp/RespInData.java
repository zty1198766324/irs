package com.ssm.irs.resp;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class RespInData {

  private Long menuId;

  private String title;

  private String icon;

  private String href;

  private String perms;

  private String spread;

  private Long parentId;

  private Long sorting;

  private List<RespInData> list;
}
