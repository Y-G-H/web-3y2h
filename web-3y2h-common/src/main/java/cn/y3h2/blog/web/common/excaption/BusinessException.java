package cn.y3h2.blog.web.common.excaption;

import lombok.Data;

@Data
/**
* @ClassName BusinessException
* @Author kongming
* @Date 2020/11/8 5:22 下午
* @Description 业务异常
*/
public class BusinessException extends RuntimeException{

    private String errorCode;

    private String errorMsg;

    public BusinessException(String code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public BusinessException(String message, String code, String msg) {
        super(message);
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public BusinessException(Throwable cause, String code, String msg) {
        super(cause);
        this.errorCode = code;
        this.errorMsg = msg;
    }


}
