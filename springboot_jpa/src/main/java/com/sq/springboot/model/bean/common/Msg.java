package com.sq.springboot.model.bean.common;

/**
 * @description: 返回码信息
 * @author: kiKi
 */
public enum Msg {

  OK("成功", 0),
  ERROR_VALIDATION("字段校验不通过", 1),
  METHOD_NOT_ALLOWED("请求方式不支持", 2),
  PARAM("参数错误", 3),

  ERROR("后台报错", 4),

  ERROR_NAME_OR_PASSWORD("用户名或密码错误", 5),
  ERROR_CAPTCHA("验证码错误", 6),
  AUTH_ERROR("身份认证失败", 7),
  NO_PERMISSION("无权限", 8),
  INVALID_SESSION("session已失效", 9),
  INVALID_TOKEN("token已失效", 10),

  ERROR_THIRD_PARTY_FORWARD("第三方接口访问不了", 11),
  ERROR_THIRD_PARTY_AUTH("第三方接口授权失败", 12),
  ERROR_THIRD_PARTY_DATA("第三方接口获取数据错误", 13),

  ERROR_FILE_TYPE("文件类型错误", 14),


  OTHER("其他异常", 99);
  private Integer code;
  private String msg;


  Msg(String msg, Integer code) {
    this.code = code;
    this.msg = msg;
  }


  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
