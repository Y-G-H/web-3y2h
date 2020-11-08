package cn.y3h2.blog.web.common.excaption;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
/**
* @ClassName ConflictException
* @Author kongming
* @Date 2020/11/8 5:22 下午
* @Description 冲突异常
*/
public class ConflictException extends RuntimeException {

  @Getter
  @Setter
  private String code;

  @Getter
  @Setter
  private String msg;


  public ConflictException(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public ConflictException(String message, String code, String msg) {
    super(message);
    this.code = code;
    this.msg = msg;
  }

  public ConflictException(Throwable cause, String code, String msg) {
    super(cause);
    this.code = code;
    this.msg = msg;
  }

}
