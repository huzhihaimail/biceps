
package cn.com.njdhy.muscle.biceps.exception.sys;

/**
 * <类功能简述> 异常码常量类
 *
 * @author 胡志海
 */
public interface RoleErrorCode {

    /**
     * 首页
     */
    String SYS_USER = "3000";


    /**
     * 新建角色系统异常
     */
    String SYS_ROLE_SAVE_APP_ERROR_CODE = SYS_USER + "1";
    String SYS_ROLE_SAVE_APP_ERROR_MESSAGE = "新建角色出现系统异常";

    /**
     * 新建角色根异常
     */
    String SYS_ROLE_SAVE_ERROR_CODE = SYS_USER + "2";
    String SYS_ROLE_SAVE_ERROR_MESSAGE = "新建角色出现根异常";

    /**
     * 更新角色系统异常
     */
    String SYS_ROLE_UPDATE_APP_ERROR_CODE = SYS_USER + "3";
    String SYS_ROLE_UPDATE_APP_ERROR_MESSAGE = "更新角色出现系统异常";

    /**
     * 更新角色根异常
     */
    String SYS_ROLE_UPDATE_ERROR_CODE = SYS_USER + "4";
    String SYS_ROLE_UPDATE_ERROR_MESSAGE = "更新角色出现根异常";

    /**
     * 查询角色系统异常
     */
    String SYS_ROLE_QUERY_APP_ERROR_CODE = SYS_USER + "5";
    String SYS_ROLE_QUERY_APP_ERROR_MESSAGE = "新建角色出现系统异常";

    /**
     * 查询角色系统异常
     */
    String SYS_ROLE_QUERY_ERROR_CODE = SYS_USER + "6";
    String SYS_ROLE_QUERY_ERROR_MESSAGE = "新建角色出现根异常";

    /**
     * 删除角色系统异常
     */
    String SYS_ROLE_DELETE_APP_ERROR_CODE = SYS_USER + "7";
    String SYS_ROLE_DELETE_APP_ERROR_MESSAGE = "删除角色出现系统异常";

    /**
     * 删除角色系统异常
     */
    String SYS_ROLE_DELETE_ERROR_CODE = SYS_USER + "8";
    String SYS_ROLE_DELETE_ERROR_MESSAGE = "删除角色出现根异常";
}
