package cn.cat.springmvc.demo.shiro;


import cn.cat.springmvc.demo.pojo.User;
import cn.cat.springmvc.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author: cat
 * @Date: Created in 2018/1/30
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class MyRealm extends AuthorizingRealm {
    private UserService userService;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 授权的回调
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证的回调
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken instanceof UsernamePasswordToken) {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            User user = userService.getByUsername(token.getUsername());
            if (user == null) {
                return null;
            }
            return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        } else {
            throw new RuntimeException("authenticationToken is not a UsernamePasswordToken");
        }

    }
}
