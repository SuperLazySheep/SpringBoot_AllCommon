package com.sq.springboot.model.bean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kiki
 * @param <T>
 */
@Data
@ApiModel("ResultData")
public class ResultData<T> implements Serializable {
    @ApiModelProperty(value = "接口返回状态", example = "200")
    protected Integer code;
    @ApiModelProperty(value = "接口返回数据")
    protected T data;
    @ApiModelProperty(value = "接口返回信息", example = "ok")
    protected String msg;

    public ResultData(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public ResultData(T data, Msg msg) {
        this(data, msg.getCode(), msg.getMsg());
    }

    public ResultData(Msg msg, String s) {
        this.data = null;
        this.code = msg.getCode();
        this.msg = msg.getMsg() + ":" + s;
    }

    public ResultData() {
    }

    public ResultData(T data) {
        this(data, Msg.OK);
    }
}
