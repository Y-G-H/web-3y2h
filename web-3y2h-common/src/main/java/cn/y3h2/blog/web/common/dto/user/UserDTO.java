package cn.y3h2.blog.web.common.dto.user;

import lombok.Data;

/**
* @ClassName UserDTO
* @Author kongming
* @Date 2020/11/12 9:34 下午
* @Description 用户dto
*/
@Data
public class UserDTO {

    private Long id;
    private String username;
    private String realname;
    private String password;
    private Integer state;

    private RoleDTO role;

}
