
package cn.com.njdhy.muscle.biceps.controller.sys;

import cn.com.njdhy.muscle.biceps.controller.Result;
import cn.com.njdhy.muscle.biceps.exception.sys.IndexErrorCode;
import cn.com.njdhy.muscle.biceps.model.SysMenu;
import cn.com.njdhy.muscle.biceps.model.SysUser;
import cn.com.njdhy.muscle.biceps.service.sys.SysMenuService;
import cn.com.njdhy.muscle.biceps.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <类功能简述> 首页控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/")
public class IndexCtl {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexCtl.class);

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 登陆系统
     *
     * @param sysUser 用户对象
     * @return 登陆结果
     */
    @RequestMapping(value = "/login")
    public Result login(SysUser sysUser) {

        try {
            // todo 参数校验

            // 生成token
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getPassword());

            // 到realm中进行身份认证和鉴权
            SecurityUtils.getSubject().login(token);

        } catch (UnknownAccountException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_USERNAME_EMPTY_CODE, IndexErrorCode.SYS_INDEX_CTL_USERNAME_EMPTY_MESSAGE);
        } catch (IncorrectCredentialsException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_USER_PWD_WRONG_CODE, IndexErrorCode.SYS_INDEX_CTL_USER_PWD_WRONG_MESSAGE);
        } catch (LockedAccountException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_USER_LOCKED_CODE, IndexErrorCode.SYS_INDEX_CTL_USER_LOCKED_MESSAGE);
        } catch (AuthenticationException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_CHECK_USER_FAIL_CODE, IndexErrorCode.SYS_INDEX_CTL_CHECK_USER_FAIL_MESSAGE);
        } catch (Exception e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_CHECK_USER_FAIL_CODE, IndexErrorCode.SYS_INDEX_CTL_CHECK_USER_FAIL_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 函数功能描述：退出系统
     */
    @RequestMapping("/logout")
    public Result logOut() {

        // 获取当前用户
        Subject currentUser = SecurityUtils.getSubject();

        // 身份校验通过
        if (currentUser.isAuthenticated()) {

            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            currentUser.logout();
        }

        return Result.success();
    }

    /**
     * 登陆用户名称
     *
     * @return 用户名称
     */
    @RequestMapping("/loginUserName")
    public Result getLoginUser() {

        try {
            String loginUserName = ShiroUtil.getLoginUserName();

            if (StringUtils.isEmpty(loginUserName)) {
                return Result.error(IndexErrorCode.SYS_INDEX_CTL_GET_USERNAME_FROM_SHIRO_ERROR_CODE, IndexErrorCode.SYS_INDEX_CTL_GET_USERNAME_FROM_SHIRO_ERROR_MESSAGE);
            }

            return Result.success().put("loginUserName", loginUserName);

        } catch (RuntimeException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ERROR_CODE, IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ROOT_ERROR_CODE, IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ROOT_ERROR_MESSAGE);
        }
    }


    /**
     * 查询用户授权菜单
     *
     * @return 用户授权菜单列表
     */
    @RequestMapping("/loadMenus")
    public Result loadMenus() {

        try {

            String loginUserName = ShiroUtil.getLoginUserName();

            if (StringUtils.isEmpty(loginUserName)) {
                return Result.error(IndexErrorCode.SYS_INDEX_CTL_GET_USERNAME_FROM_SHIRO_ERROR_CODE, IndexErrorCode.SYS_INDEX_CTL_GET_USERNAME_FROM_SHIRO_ERROR_MESSAGE);
            }

            List<SysMenu> result = sysMenuService.loadMenus(loginUserName);

            if (CollectionUtils.isEmpty(result)) {
                result = new ArrayList<>();
            }

            return Result.success().put("page", result);

        } catch (RuntimeException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ERROR_CODE, IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ROOT_ERROR_CODE, IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ROOT_ERROR_MESSAGE);
        }
    }

}
