
package cn.com.njdhy.muscle.biceps.exception.sys;

/**
 * <类功能简述> 异常码常量类
 *
 * @author 胡志海
 */
public interface IndexErrorCode {

    /**
     * 首页
     */
    String SYS_INDEX = "1000";

    /**
     * 用户名为空
     */
    String SYS_INDEX_CTL_USERNAME_EMPTY_CODE = SYS_INDEX + "1";
    String SYS_INDEX_CTL_USERNAME_EMPTY_MESSAGE = "用户名为空";

    /**
     * 密码错误
     */
    String SYS_INDEX_CTL_USER_PWD_WRONG_CODE = SYS_INDEX + "2";
    String SYS_INDEX_CTL_USER_PWD_WRONG_MESSAGE = "密码错误";

    /**
     * 账号被锁定
     */
    String SYS_INDEX_CTL_USER_LOCKED_CODE = SYS_INDEX + "3";
    String SYS_INDEX_CTL_USER_LOCKED_MESSAGE = "账号被锁定";

    /**
     * 身份校验失败
     */
    String SYS_INDEX_CTL_CHECK_USER_FAIL_CODE = SYS_INDEX + "4";
    String SYS_INDEX_CTL_CHECK_USER_FAIL_MESSAGE = "身份校验失败";

    /**
     * 从shiro获取登陆用户名失败
     */
    String SYS_INDEX_CTL_GET_USERNAME_FROM_SHIRO_ERROR_CODE = SYS_INDEX + "5";
    String SYS_INDEX_CTL_GET_USERNAME_FROM_SHIRO_ERROR_MESSAGE = "从shiro获取登陆用户名失败";

    /**
     * 用户登录名存在，但是查询用户授权菜单为空
     */
    String SYS_INDEX_CTL_QUERY_MENU_IS_EMPTY_CODE = SYS_INDEX + "6";
    String SYS_INDEX_CTL_QUERY_MENU_IS_EMPTY_MESSAGE = "用户登录名存在，但是查询用户授权菜单为空";

    /**
     * 根据权限查询系统菜单，查询系统异常
     */
    String SYS_INDEX_CTL_GET_MENUS_ERROR_CODE = SYS_INDEX + "6";
    String SYS_INDEX_CTL_GET_MENUS_ERROR_MESSAGE = "根据权限查询系统菜单，查询系统ApplicationException异常";

    /**
     * 根据权限查询系统菜单，查询Exception异常
     */
    String SYS_INDEX_CTL_GET_MENUS_ROOT_ERROR_CODE = SYS_INDEX + "6";
    String SYS_INDEX_CTL_GET_MENUS_ROOT_ERROR_MESSAGE = "根据权限查询系统菜单，查询Exception异常";

    /**
     * 根据权限查询系统菜单，查询Exception异常
     */
    String SYS_INDEX_CTL_LOGIN_ERROR_CODE = SYS_INDEX + "6";
    String SYS_INDEX_CTL_LOGIN_ERROR_MESSAGE = "登录系统，查询参数异常，用户名或者密码为空";

}
