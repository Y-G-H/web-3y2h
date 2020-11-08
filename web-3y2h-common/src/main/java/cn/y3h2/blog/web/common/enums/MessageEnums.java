package cn.y3h2.blog.web.common.enums;

import lombok.Getter;

import java.util.Objects;


public enum MessageEnums {

  RPC_ERROR("-1", "服务不可用！"),
  SUCCESS("0", "处理成功！"),
  FAIL("99", "操作失败！"),
  NO_RESULT("100", "无查询结果！"),
  PARAM_ERROR("9990", "参数校验错误！"),
  RPC_RETURN_ERROR("9991", "服务数据异常！"),
  MQ_ERROR("9996", "操作MQ异常！"),
  REDIS_ERROR("9997","操作redis异常！"),
  SQL_ERROR("9998", "SQL执行异常！"),
  SERVICE_RETURN_ERROR("9992","服务数据异常！"),
  SYS_ERROR("9999", "系统异常！"),
  SCHEDULE_JOB_ERROR("9992", "创建定时任务异常"),
  NO_PRIVILEGE("101","无权限"),
  UN_LOGGED("102", "用户未登录！"),
  EXCEL_READ_ERROR("9994", "excel数据校验异常"),
  CONFLICT("10100","操作冲突,请重试！"),
  VALIDATE_FAIL("10200","操作校验失败 ");

  @Getter
  private final String code;
  @Getter
  private final String msg;

  MessageEnums(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  @Override
  public String toString() {
    return "MessageEnums{" +
        "code='" + code + '\'' +
        ", msg='" + msg + '\'' +
        '}';
  }

  /**
   * 根据数值返回状态枚举
   *
   * @param code 数值
   * @return 状态枚举
   */
  public static MessageEnums from(String code) {
    for (MessageEnums e : MessageEnums.values()) {
      if (Objects.equals(e.code, code)) {
        return e;
      }
    }
    return null;
  }

}
