package cn.y3h2.blog.web.common.dto.user.req;

import lombok.Data;

/**
 * @ClassName LoginParam
 * @Author kongming
 * @Date 2020/11/8 5:17 下午
 * @Description 用户登录入参
 */
@Data
public class LoginParam {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
