package cn.y3h2.blog.web.biz.helper;

import cn.y3h2.blog.user.common.dto.PermissionInfoDTO;
import cn.y3h2.blog.user.common.dto.RoleInfoDTO;
import cn.y3h2.blog.user.common.dto.UserInfoDTO;
import cn.y3h2.blog.web.common.dto.user.PermissionsDTO;
import cn.y3h2.blog.web.common.dto.user.RoleDTO;
import cn.y3h2.blog.web.common.dto.user.UserDTO;
import cn.y3h2.blog.web.common.dto.user.rsp.UserVO;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @ClassName ConverterHelper
* @Author kongming
* @Date 2020/11/12 11:04 下午
* @Description 类型转换器
*/
public class ConverterHelper {

    public static UserDTO toUserDTO(UserInfoDTO userInfoDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userInfoDTO.getId());
        userDTO.setPassword(userInfoDTO.getPassword());
        userDTO.setRealname(userInfoDTO.getRealname());
        userDTO.setState(userInfoDTO.getState());
        userDTO.setUsername(userInfoDTO.getUsername());
        userDTO.setAvatar(userInfoDTO.getAvatar());
        userDTO.setFreeze2Date(userInfoDTO.getFreeze2Date());
        userDTO.setGender(userInfoDTO.getGender());
        userDTO.setMobile(userInfoDTO.getMobile());
        userDTO.setRoleCode(userInfoDTO.getRoleCode());
        if (Objects.nonNull(userInfoDTO.getRole())) {
            userDTO.setRole(ConverterHelper.toRoleDTO(userInfoDTO.getRole()));
        }
        return userDTO;
    }

    public static RoleDTO toRoleDTO(RoleInfoDTO roleInfoDTO) {
        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setCode(roleInfoDTO.getCode());
        roleDTO.setName(roleInfoDTO.getName());
        if (CollectionUtils.isNotEmpty(roleInfoDTO.getPermissions())) {
            List<PermissionsDTO> permissions = roleInfoDTO.getPermissions().stream()
                    .map(ConverterHelper::toPermissionsDTO).collect(Collectors.toList());
            roleDTO.setPermissions(permissions);
        }
        return roleDTO;
    }

    public static PermissionsDTO toPermissionsDTO(PermissionInfoDTO permissionInfoDTO) {
        PermissionsDTO permissionsDTO = new PermissionsDTO();
        permissionsDTO.setPermissionName(permissionInfoDTO.getName());
        permissionsDTO.setPermissionCode(permissionInfoDTO.getCode());
        return permissionsDTO;
    }

    public static UserVO toUserVO(UserDTO userInfoDTO) {
        UserVO userVO = new UserVO();
        userVO.setId(userInfoDTO.getId());
        userVO.setRealname(userInfoDTO.getRealname());
        userVO.setState(userInfoDTO.getState());
        userVO.setUsername(userInfoDTO.getUsername());
        userVO.setAvatar(userInfoDTO.getAvatar());
        userVO.setFreeze2Date(userInfoDTO.getFreeze2Date());
        userVO.setGender(userInfoDTO.getGender());
        userVO.setMobile(userInfoDTO.getMobile());
        userVO.setRoleCode(userInfoDTO.getRoleCode());
        return userVO;
    }

}
