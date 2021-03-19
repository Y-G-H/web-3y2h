package cn.y3h2.blog.web.biz.user.impl;

import cn.y3h2.blog.user.common.dto.UserInfoDTO;
import cn.y3h2.blog.web.biz.helper.ConverterHelper;
import cn.y3h2.blog.web.biz.user.UserService;
import cn.y3h2.blog.web.common.dto.user.req.FindUserParam;
import cn.y3h2.blog.web.common.dto.user.req.LoginParam;
import cn.y3h2.blog.web.common.dto.user.UserDTO;
import cn.y3h2.blog.web.common.dto.user.req.RegisterParam;
import cn.y3h2.blog.web.common.enums.MessageEnums;
import cn.y3h2.blog.web.common.excaption.ExceptionFactory;
import cn.y3h2.blog.web.intergration.user.UserRPC;
import com.alibaba.dubbo.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @ClassName UserServiceImpl
* @Author kongming
* @Date 2020/11/12 9:57 下午
* @Description 用户服务接口实现
*/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRPC userRPC;

    @Override
    public UserDTO findUserByUsername(FindUserParam param) {
        String username = param.getUsername();
        if (StringUtils.isBlank(username)) throw ExceptionFactory.getBusinessException(MessageEnums.PARAM_ERROR, "用户名为空");
        return ConverterHelper.toUserDTO(userRPC.findUserByUsername(username));
    }

    @Override
    public UserDTO login(LoginParam param) {
        if (StringUtils.isEmpty(param.getUsername()))
            throw ExceptionFactory.getBusinessException(MessageEnums.PARAM_ERROR, "登录用户名为空");
        if (StringUtils.isEmpty(param.getPassword()))
            throw ExceptionFactory.getBusinessException(MessageEnums.PARAM_ERROR, "登录密码为空");
        //用户认证信息
        log.info("UserServiceImpl#login user Is the login, user is [{}]", param);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(param.getUsername(), param.getPassword());
        try {
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
            return ConverterHelper.toUserDTO(userRPC.findUserByUsername(param.getUsername()));
        } catch (UnknownAccountException e) {
            log.warn("UserServiceImpl#login username is not exist, error is {}", e);
            throw ExceptionFactory.getBusinessException(MessageEnums.NOT_FIND_USER);
        } catch (AuthenticationException e) {
            log.warn("UserServiceImpl#login password wrong, error is {}", e);
            throw ExceptionFactory.getBusinessException(MessageEnums.PASSWORD_MISTAKE);
        } catch (AuthorizationException e) {
            log.warn("UserServiceImpl#login no privilege, error is {}", e);
            throw ExceptionFactory.getBusinessException(MessageEnums.NO_PRIVILEGE);
        }
    }

    @Override
    public Boolean register(RegisterParam param) {
        if (StringUtils.isBlank(param.getUsername()))
            throw ExceptionFactory.getBusinessException(MessageEnums.PARAM_ERROR, "注册用户名为空");
        if (StringUtils.isBlank(param.getPassword()))
            throw ExceptionFactory.getBusinessException(MessageEnums.PARAM_ERROR, "注册密码为空");

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUsername(param.getUsername());
        userInfoDTO.setPassword(param.getPassword());
        userRPC.addUser(userInfoDTO);
        return true;
    }
}
