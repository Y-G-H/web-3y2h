package cn.y3h2.blog.web.common.excaption;


import cn.y3h2.blog.web.common.enums.MessageEnums;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionFactory {

    private final static String EMPTY_STR = "";
    private final static String BLANK_STR = " ";

    public static BusinessException getBusinessException(String code, String msg) {
        return new BusinessException(msg, code, msg);
    }

    public static BusinessException getBusinessException(MessageEnums enums, String desc) {
        return new BusinessException(desc, enums.getCode(), enums.getMsg());
    }

    public static BusinessException getBusinessException(MessageEnums enums, Throwable cause) {
        return new BusinessException(cause, enums.getCode(), enums.getMsg());
    }
    public static BusinessException getBusinessException(MessageEnums enums) {
        return new BusinessException(enums.getMsg(), enums.getCode(), enums.getMsg());

    }
    public static BusinessException getBusinessException(String code, String msg, String desc) {
        return new BusinessException(desc, code, EMPTY_STR + msg + BLANK_STR + desc);

    }
    public static BusinessException getBusinessException(String code, String msg, Throwable cause) {
        return new BusinessException(cause, code, msg);

    }
    public static ConflictException getConflictException(String code, String msg) {
        return new ConflictException(msg, code, msg);

    }

    public static ConflictException getConflictException(MessageEnums enums, String desc) {
        return new ConflictException(desc, enums.getCode(), enums.getMsg());
    }

    public static ConflictException getConflictException(MessageEnums enums, Throwable cause) {
        return new ConflictException(cause, enums.getCode(), enums.getMsg());
    }

    public static ConflictException getConflictException(MessageEnums enums) {
        return new ConflictException(enums.getMsg(), enums.getCode(), enums.getMsg());
    }

    public static ConflictException getConflictException(String code, String msg, String desc) {
        return new ConflictException(desc, code, EMPTY_STR + msg + BLANK_STR + desc);
    }

    public static ConflictException getConflictException(String code, String msg, Throwable cause) {
        return new ConflictException(cause, code, msg);
    }

    public static OSException getOsException(String code, String msg) {
        return new OSException(msg, code, msg);
    }

    public static OSException getOsException(MessageEnums enums, String desc) {
        return new OSException(desc, enums.getCode(), enums.getMsg());
    }

    public static OSException getOsException(MessageEnums enums, Throwable cause) {
        return new OSException(cause, enums.getCode(), enums.getMsg());
    }

    public static OSException getOsException(MessageEnums enums, Exception e) {
        return new OSException(e.getCause(), enums.getCode(), enums.getMsg());
    }

    public static OSException getOsException(MessageEnums enums) {
        return new OSException(enums.getMsg(), enums.getCode(), enums.getMsg());
    }

    public static OSException getOsException(String code, String msg, String desc) {
        return new OSException(desc, code, EMPTY_STR + msg + BLANK_STR + desc);
    }

    public static OSException getOsException(String code, String msg, Throwable cause) {
        return new OSException(cause, code, msg);
    }

    public static RpcException getRpcException(String code, String msg) {
        return new RpcException(msg, code, msg);
    }

    public static RpcException getRpcException(MessageEnums enums, String desc) {
        return new RpcException(desc, enums.getCode(), enums.getMsg());
    }

    public static RpcException getRpcException(MessageEnums enums, Throwable cause) {
        return new RpcException(cause, enums.getCode(), enums.getMsg());
    }

    public static RpcException getRpcException(MessageEnums enums) {
        return new RpcException(enums.getMsg(), enums.getCode(), enums.getMsg());
    }

    public static RpcException getRpcException(String code, String msg, String desc) {
        return new RpcException(desc, code, EMPTY_STR + msg + BLANK_STR + desc);
    }

    public static RpcException getRpcException(String code, String msg, Throwable cause) {
        return new RpcException(cause, code, msg);
    }

    public static SqlException getSqlException(String code, String msg) {
        return new SqlException(msg, code, msg);
    }

    public static SqlException getSqlException(MessageEnums enums, String desc) {
        return new SqlException(desc, enums.getCode(), enums.getMsg());
    }

    public static SqlException getSqlException(MessageEnums enums, Throwable cause) {
        return new SqlException(cause, enums.getCode(), enums.getMsg());
    }

    public static SqlException getSqlException(MessageEnums enums) {
        return new SqlException(enums.getMsg(), enums.getCode(), enums.getMsg());
    }


    public static SqlException getSqlException(String code, String msg, String desc) {
        return new SqlException(desc, code, EMPTY_STR + msg + BLANK_STR + desc);
    }

    public static SqlException getSqlException(String code, String msg, Throwable cause) {
        return new SqlException(cause, code, msg);
    }
    /**
     * 把异常转化为字符串输出
     * @param e
     * @return
     */
    public static String getErrorInfoFromException(Exception e) {
        if (e == null){
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
