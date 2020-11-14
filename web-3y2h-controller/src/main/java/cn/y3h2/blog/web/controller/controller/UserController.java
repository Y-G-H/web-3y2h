package cn.y3h2.blog.web.controller.controller;

import cn.y3h2.blog.web.common.WebResponse;
import cn.y3h2.blog.web.common.dto.user.UserDTO;
import cn.y3h2.blog.web.common.dto.user.req.LoginParam;
import cn.y3h2.blog.web.common.dto.user.req.RegisterParam;
import cn.y3h2.blog.web.common.dto.user.rsp.UserVO;
import cn.y3h2.blog.web.controller.annotation.WebResponseHandler;
import cn.y3h2.blog.web.controller.biz.UserBiz;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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
public class UserController extends BaseController {

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

    @PostMapping("/register")
    @WebResponseHandler
    public WebResponse<Boolean> register(@RequestBody RegisterParam param) {
        // 加密盐值
        Object salt = ByteSource.Util.bytes(param.getUsername());
        // 使用md5加密password一千次
        String newPassword = new SimpleHash("MD5", param.getPassword(), salt, 1000).toHex();
        param.setPassword(newPassword);
        return WebResponse.ok(userBiz.register(param));
    }


}
