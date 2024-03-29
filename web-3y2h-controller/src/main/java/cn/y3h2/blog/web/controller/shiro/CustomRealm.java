package cn.y3h2.blog.web.controller.shiro;
import cn.y3h2.blog.web.biz.user.UserService;
import cn.y3h2.blog.web.common.dto.user.req.FindUserParam;
import cn.y3h2.blog.web.common.dto.user.PermissionsDTO;
import cn.y3h2.blog.web.common.dto.user.RoleDTO;
import cn.y3h2.blog.web.common.dto.user.UserDTO;
import cn.y3h2.blog.web.controller.biz.UserBiz;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * @MethodName doGetAuthorizationInfo
     * @Description 登录授权，配置当前登录态角色和权限
     * @Param [principalCollection]
     * @Return AuthorizationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //查询用户、角色
        FindUserParam param = new FindUserParam();
        param.setUsername(username);
        UserDTO user = userService.findUserByUsername(param);
        RoleDTO role = user.getRole();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加角色
        simpleAuthorizationInfo.addRole(role.getCode());
        //添加权限
        for (PermissionsDTO permissions : role.getPermissions()) {
            simpleAuthorizationInfo.addStringPermission(permissions.getPermissionCode());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * @MethodName doGetAuthenticationInfo
     * @Description 认证鉴权
     * @Param [authenticationToken]
     * @Return AuthenticationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (StringUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        //获取用户信息
        String username = authenticationToken.getPrincipal().toString();
        FindUserParam param = new FindUserParam();
        param.setUsername(username);
        UserDTO user = userService.findUserByUsername(param);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), credentialsSalt, getName());
            return simpleAuthenticationInfo;
        }
    }
}