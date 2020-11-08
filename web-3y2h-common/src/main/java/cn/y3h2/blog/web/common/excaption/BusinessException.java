package cn.y3h2.blog.web.common.excaption;

import lombok.Data;

@Data
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
