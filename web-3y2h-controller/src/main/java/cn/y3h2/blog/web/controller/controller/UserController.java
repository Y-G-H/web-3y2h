package cn.y3h2.blog.web.controller.controller;

import cn.y3h2.blog.web.common.WebResponse;
import cn.y3h2.blog.web.common.dto.common.req.LoginParam;
import cn.y3h2.blog.web.controller.annotation.WebResponseHandler;
import cn.y3h2.blog.web.controller.biz.UserBiz;
import cn.y3h2.blog.web.controller.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @ClassName TestController
* @Author kongming
* @Date 2020/11/8 5:16 下午
* @Description 这里需要描述这个类的核心业务
*/
@Slf4j
@RestController
@RequestMapping("/api/y3h2/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    /**
     * 登录
     * @param param
     * @return
     */
    @PostMapping("/login")
    @WebResponseHandler
    public WebResponse<UserVO> login(@RequestBody LoginParam param) {
        return WebResponse.ok(userBiz.login(param));
    }


    /**
     * shiro测试
     * @return
     */
    @GetMapping("/shiro/test")
    @WebResponseHandler
    public WebResponse<Boolean> test() {
        return WebResponse.ok(true);
    }


}
