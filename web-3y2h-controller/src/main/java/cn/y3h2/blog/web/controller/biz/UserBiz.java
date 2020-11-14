package cn.y3h2.blog.web.controller.biz;

import cn.y3h2.blog.web.biz.helper.ConverterHelper;
import cn.y3h2.blog.web.biz.user.UserService;
import cn.y3h2.blog.web.common.dto.user.req.FindUserParam;
import cn.y3h2.blog.web.common.dto.user.req.LoginParam;
import cn.y3h2.blog.web.common.dto.user.UserDTO;
import cn.y3h2.blog.web.common.dto.user.req.RegisterParam;
import cn.y3h2.blog.web.common.dto.user.rsp.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBiz {

    @Autowired
    private UserService userService;

    public UserVO findUserByUsername(FindUserParam param) {
        return ConverterHelper.toUserVO(userService.findUserByUsername(param));
    }

    public UserVO login(LoginParam param) {
        return ConverterHelper.toUserVO(userService.login(param));
    }

    public Boolean register(RegisterParam param) {
        return userService.register(param);
    }

}
