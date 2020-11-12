package cn.y3h2.blog.web.controller.biz;

import cn.y3h2.blog.web.biz.user.UserService;
import cn.y3h2.blog.web.common.dto.common.req.FindUserParam;
import cn.y3h2.blog.web.common.dto.common.req.LoginParam;
import cn.y3h2.blog.web.common.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBiz {

    @Autowired
    private UserService userService;

    public UserDTO findUserByUsername(FindUserParam param) {
        return userService.findUserByUsername(param);
    }

    public UserDTO login(LoginParam param) {
        return userService.login(param);
    }

}
