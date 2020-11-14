package cn.y3h2.blog.web.common.dto.user;

import lombok.Data;

import java.util.List;

/**
 * @ClassName RoleDTO
 * @Author kongming
 * @Date 2020/11/12 9:34 下午
 * @Description 角色dto
 */
@Data
public class RoleDTO {

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 权限列表
     */
    private List<PermissionsDTO> permissions;

}
