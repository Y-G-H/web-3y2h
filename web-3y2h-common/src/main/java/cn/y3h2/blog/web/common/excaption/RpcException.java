package cn.y3h2.blog.web.common.excaption;

import lombok.Data;

@Data
public class RpcException extends RuntimeException {
    private String code;
    private String msg;

    public RpcException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RpcException(String message, String code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public RpcException(Throwable cause, String code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

