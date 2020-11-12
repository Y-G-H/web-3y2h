package cn.y3h2.blog.web.biz.helper;

import cn.y3h2.blog.user.common.dto.UserInfoDTO;
import cn.y3h2.blog.web.common.dto.user.UserDTO;

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
        // TODO role
        userDTO.setRole(null);
        return userDTO;
    }

}
