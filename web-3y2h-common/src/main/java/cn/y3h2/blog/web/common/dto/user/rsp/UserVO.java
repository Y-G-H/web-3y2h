package cn.y3h2.blog.web.common.dto.user.rsp;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserVO
 * @Author kongming
 * @Date 2020/11/14 7:15 下午
 * @Description 用户信息VO
 */
@Data
public class UserVO {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户账户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 解冻至时间
     */
    private Date freeze2Date;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 账户状态
     */
    private Integer state;

    /**
     * 角色编码
     */
    private String roleCode;

}
