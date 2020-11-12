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

    private String name;

    private List<PermissionsDTO> permissions;

}
