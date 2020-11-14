package cn.y3h2.blog.web.intergration.user;

import cn.y3h2.blog.user.api.UserFacade;
import cn.y3h2.blog.user.api.domain.req.FindUserCondition;
import cn.y3h2.blog.user.api.domain.req.FindUserPart;
import cn.y3h2.blog.user.common.dto.UserInfoDTO;
import cn.y3h2.blog.user.common.model.Response;
import cn.y3h2.blog.web.common.dto.user.UserDTO;
import cn.y3h2.blog.web.common.enums.MessageEnums;
import cn.y3h2.blog.web.common.excaption.ExceptionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName UserRPC
 * @Author kongming
 * @Date 2020/11/12 9:37 下午
 * @Description 用户RPC
 */
@Service
@Slf4j
public class UserRPC {

    @Autowired
    private UserFacade userFacade;

    public UserInfoDTO findUserByUsername(String username) {
        try {
            FindUserCondition findUserCondition = new FindUserCondition();
            findUserCondition.setUsername(username);

            FindUserPart findUserPart = new FindUserPart();
            findUserPart.setNeedPermission(true);
            Response<UserInfoDTO> response = userFacade.loadUserByUsername(findUserCondition, findUserPart);

            if (Objects.isNull(response)) {
                log.warn("UserRPC#findUserByUsername rpc response is null, param is {}", username);
                throw ExceptionFactory.getBusinessException("", "查询用户信息response为空");
            }
            if (!response.isSuccess()) {
                log.warn("UserRPC#findUserByUsername rpc response is fail, param is {}", username);
                throw ExceptionFactory.getBusinessException("", "查询用户信息失败");
            }
            UserInfoDTO user = response.getData();
            if (Objects.isNull(user)) {
                log.warn("UserRPC#findUserByUsername find user empty, param is {}", username);
                throw ExceptionFactory.getBusinessException(MessageEnums.NO_RESULT, "找不到该用户");
            }
            return user;
        } catch (Exception e) {
            log.warn("UserRPC#findUserByUsername rpc error is {}, param is {}", e, username);
            throw ExceptionFactory.getRpcException(MessageEnums.RPC_ERROR, "查询用户信息异常");
        }
    }

    public Boolean addUser(UserInfoDTO userInfoDTO) {
        try {
            Response<Boolean> response = userFacade.addUser(userInfoDTO);
            if (Objects.isNull(response)) {
                log.warn("UserRPC#findUserByUsername rpc response is null, param is {}", userInfoDTO);
                throw ExceptionFactory.getBusinessException("", "添加用户信息response为空");
            }
            if (!response.isSuccess() || !response.getData()) {
                log.warn("UserRPC#findUserByUsername rpc response is fail, param is {}", userInfoDTO);
                throw ExceptionFactory.getBusinessException("", "添加用户信息失败");
            }
            return true;
        } catch (Exception e) {
            log.warn("UserRPC#findUserByUsername rpc error is {}, param is {}", e, userInfoDTO);
            throw ExceptionFactory.getRpcException(MessageEnums.RPC_ERROR, "添加用户信息异常");
        }
    }

}
