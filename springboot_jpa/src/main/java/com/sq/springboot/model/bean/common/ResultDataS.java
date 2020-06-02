package com.sq.springboot.model.bean.common;

import javax.validation.constraints.NotNull;

/**
 * @description: 带分页的返回值对象
 * @author: kiKi
 */
public class ResultDataS<T> extends ResultData<Data<T>> {

  public ResultDataS(@NotNull T data, Pager pager, Integer code, String msg) {
    this.data = new Data<T>(data, pager);
    this.code = code;
    this.msg = msg;
  }

  public ResultDataS(@NotNull T data, Pager pager, Msg msg) {
    this(data, pager, msg.getCode(), msg.getMsg());
  }

  public ResultDataS(@NotNull T data, Pager pager) {
    this(data, pager, Msg.OK.getCode(), Msg.OK.getMsg());
  }
}
