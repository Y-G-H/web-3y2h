package cn.y3h2.blog.web.controller.controller;

import cn.y3h2.blog.web.biz.user.UserService;
import cn.y3h2.blog.web.common.dto.user.UserDTO;
import cn.y3h2.blog.web.common.dto.user.req.FindUserParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

/**
 * @ClassName BaseController
 * @Author kongming
 * @Date 2020/11/14 4:53 下午
 * @Description 基础controller, 所有controller共享方法
 */
@Slf4j
public class BaseController {

    private UserService userService;

    protected UserDTO getCurrentUser() {
        String usernaem = (String) SecurityUtils.getSubject().getPrincipal();
        FindUserParam findUserParam = new FindUserParam();
        findUserParam.setUsername(usernaem);
        UserDTO user = userService.findUserByUsername(findUserParam);
        return user;
    }

}
