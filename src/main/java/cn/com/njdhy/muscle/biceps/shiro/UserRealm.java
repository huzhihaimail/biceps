

package cn.com.njdhy.muscle.biceps.shiro;

import cn.com.njdhy.muscle.biceps.model.SysUser;
import cn.com.njdhy.muscle.biceps.service.sys.SysMenuService;
import cn.com.njdhy.muscle.biceps.service.sys.SysRoleService;
import cn.com.njdhy.muscle.biceps.service.sys.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 用户身份认证和授权
 *
 * @author 胡志海
 * @version V0.0.1-SNAPSHOT
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //获取登录时输入的用户名  
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        String loginName = user.getUserName();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //用户角色授权
        addRole(loginName, info);
        //用户权限授权
        addPermission(loginName, info);

        return info;
    }

    /**
     * 用户身份认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取前台用户输入的用户名和密码
        String userName = String.valueOf(token.getPrincipal());

        // 用户为空
        if (StringUtils.isEmpty(userName)) {
            throw new UnknownAccountException();
        }

        //查询用户信息
        SysUser user = this.sysUserService.queryByName(userName);

        //账号不存在
        if (user == null) {
            throw new UnknownAccountException();
        }

        // 角色已被禁用
        if (user.getStatus() == 0) {
            throw new LockedAccountException();
        }

        //若存在，将此用户存放到登录认证中进行密码匹配
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getUserName() + user.getSalt()), getName());
    }

    /**
     * 函数功能描述：根据用户名查询权限
     *
     * @param loginName 登录名称
     * @param info      简单身份认证对象
     */
    private void addPermission(String loginName, SimpleAuthorizationInfo info) {

        // 根据登录名称查询权限（菜单）信息
        List<String> permissionLst = sysMenuService.queryPermissionByUserName(loginName);
        //用户角色列表
        Set<String> permissionSet = new HashSet<>();
        for (String perm : permissionLst) {
            if (StringUtils.isEmpty(perm)) {
                continue;
            }
            permissionSet.addAll(Arrays.asList(perm.trim().split(",")));
        }
        info.setStringPermissions(permissionSet);
    }

    /**
     * 函数功能描述：用户角色授权
     *
     * @param loginName 登录用户名
     * @param info      身份认证对象
     */
    private void addRole(String loginName, SimpleAuthorizationInfo info) {
        List<String> roleLst = sysRoleService.queryRolesByUserName(loginName);
        //用户角色列表
        Set<String> roleSet = new HashSet<>();
        for (String role : roleLst) {
            if (StringUtils.isEmpty(role)) {
                continue;
            }
            roleSet.addAll(Arrays.asList(role.trim().split(",")));
        }
        info.setRoles(roleSet);
    }
}
