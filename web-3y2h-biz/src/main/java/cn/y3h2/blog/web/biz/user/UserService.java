package cn.y3h2.blog.web.biz.user;

import cn.y3h2.blog.web.common.dto.common.req.FindUserParam;
import cn.y3h2.blog.web.common.dto.common.req.LoginParam;
import cn.y3h2.blog.web.common.dto.user.UserDTO;

/**
 * @ClassName UserService
 * @Author kongming
 * @Date 2020/11/12 9:57 下午
 * @Description 用户服务接口
 */
public interface UserService {

    public UserDTO findUserByUsername(FindUserParam param);

    public UserDTO login(LoginParam param);

}
