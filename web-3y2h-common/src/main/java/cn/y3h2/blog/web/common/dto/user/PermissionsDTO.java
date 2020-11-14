package cn.y3h2.blog.web.common.dto.user;

import lombok.Data;

/**
* @ClassName PermissionsDTO
* @Author kongming
* @Date 2020/11/12 10:02 下午
* @Description 权限dto
*/
@Data
public class PermissionsDTO {

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限编码
     */
    private String permissionCode;

}
