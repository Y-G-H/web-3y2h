package cn.y3h2.blog.web.common;

import cn.y3h2.blog.web.common.enums.MessageEnums;
import lombok.Data;

/**
* @ClassName WebResponse
* @Author kongming
* @Date 2020/11/8 5:18 下午
* @Description 统一接口返回结构
*/
@Data
public class WebResponse<T> {
    /**
     * 错误编码
     */
    private String code;
    /**
     * 错误描述
     */
    private String message;
    /**
     * 请求是否成功
     */
    private Boolean success;
    /**
     * 返回数据
     */
    private T data;

    public static WebResponse fail(String code, String message) {
        WebResponse responseVo = new WebResponse();
        responseVo.setSuccess(false);
        responseVo.setCode(code);
        responseVo.setMessage(message);
        responseVo.setData(null);
        return responseVo;
    }

    public static <T> WebResponse ok(T data) {
        WebResponse responseVo = new WebResponse();
        responseVo.setSuccess(true);
        responseVo.setCode(MessageEnums.SUCCESS.getCode());
        responseVo.setMessage(MessageEnums.SUCCESS.getMsg());
        responseVo.setData(data);
        return responseVo;
    }
}
