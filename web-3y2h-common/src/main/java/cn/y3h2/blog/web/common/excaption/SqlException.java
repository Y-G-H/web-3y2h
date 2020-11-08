package cn.y3h2.blog.web.common.excaption;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SqlException extends RuntimeException {


    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String msg;

    public SqlException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SqlException(String message, String code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public SqlException(Throwable cause, String code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }


}
