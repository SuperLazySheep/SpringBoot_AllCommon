package com.sq.springboot.model.bean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description:
 * @author: kiKi
 */
@lombok.Data
@ApiModel("带分页的数据")
public class Data<T> {
  @ApiModelProperty(value = "数据")
  private T data;
  @ApiModelProperty(value = "分页")
  private Pager pager;

  public Data(T data, Pager pager) {
    this.data = data;
    this.pager = pager;
  }
}
