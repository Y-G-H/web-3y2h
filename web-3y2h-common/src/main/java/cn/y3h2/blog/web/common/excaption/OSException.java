package cn.y3h2.blog.web.common.excaption;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OSException extends RuntimeException {

  @Getter
  @Setter
  private String code;

  @Getter
  @Setter
  private String msg;


  public OSException(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public OSException(String message, String code, String msg) {
    super(message);
    this.code = code;
    this.msg = msg;
  }

  public OSException(Throwable cause, String code, String msg) {
    super(cause);
    this.code = code;
    this.msg = msg;
  }

}
