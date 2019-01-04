
package cn.com.njdhy.muscle.biceps.exception.sys;

/**
 * <类功能简述> 异常码常量类
 *
 * @author 胡志海
 */
public interface UserErrorCode {

    /**
     * 首页
     */
    String SYS_USER = "2000";


    /**
     * 新建用户系统异常
     */
    String SYS_USER_SAVE_APP_ERROR_CODE = SYS_USER + "1";
    String SYS_USER_SAVE_APP_ERROR_MESSAGE = "新建用户出现系统异常";

    /**
     * 新建用户根异常
     */
    String SYS_USER_SAVE_ERROR_CODE = SYS_USER + "2";
    String SYS_USER_SAVE_ERROR_MESSAGE = "新建用户出现根异常";

    /**
     * 更新用户系统异常
     */
    String SYS_USER_UPDATE_APP_ERROR_CODE = SYS_USER + "3";
    String SYS_USER_UPDATE_APP_ERROR_MESSAGE = "更新用户出现系统异常";

    /**
     * 更新用户根异常
     */
    String SYS_USER_UPDATE_ERROR_CODE = SYS_USER + "4";
    String SYS_USER_UPDATE_ERROR_MESSAGE = "更新用户出现根异常";

    /**
     * 加载角色系统异常
     */
    String SYS_USER_LOAD_ROLES_APP_ERROR_CODE = SYS_USER + "5";
    String SYS_USER_LOAD_ROLES_APP_ERROR_MESSAGE = "查询角色出现系统异常";

    /**
     * 加载角色异常
     */
    String SYS_USER_LOAD_ROLES_ERROR_CODE = SYS_USER + "6";
    String SYS_USER_LOAD_ROLES_ERROR_MESSAGE = "查询角色出现根异常";

    /**
     * 加载角色异常
     */
    String SYS_USER_PARMETER_ERROR_CODE = SYS_USER + "7";
    String SYS_USER_PARMETER_ERROR_MESSAGE = "参数id不能为空";
}
