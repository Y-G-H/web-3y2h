package cn.y3h2.blog.web.common.dto.user.req;

import lombok.Data;

/**
 * @ClassName RegisterParam
 * @Author kongming
 * @Date 2020/11/14 6:23 下午
 * @Description 注册用户入参
 */
@Data
public class RegisterParam {

    /**
     * 注册用户名
     */
    private String username;

    /**
     * 注册密码
     */
    private String password;

}
