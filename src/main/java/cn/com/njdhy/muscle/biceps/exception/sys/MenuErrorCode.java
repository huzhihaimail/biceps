
package cn.com.njdhy.muscle.biceps.exception.sys;

/**
 * <类功能简述> 异常码常量类
 *
 * @author 胡志海
 */
public interface MenuErrorCode {

    /**
     * 首页
     */
    String SYS_MENU = "2220";


    /**
     * 新建菜单系统异常
     */
    String SYS_MENU_SAVE_APP_ERROR_CODE = SYS_MENU + "1";
    String SYS_MENU_SAVE_APP_ERROR_MESSAGE = "新建菜单出现系统异常";

    /**
     * 新建菜单根异常
     */
    String SYS_MENU_SAVE_ERROR_CODE = SYS_MENU + "2";
    String SYS_MENU_SAVE_ERROR_MESSAGE = "新建菜单出现根异常";

    /**
     * 更新菜单系统异常
     */
    String SYS_MENU_UPDATE_APP_ERROR_CODE = SYS_MENU + "3";
    String SYS_MENU_UPDATE_APP_ERROR_MESSAGE = "更新菜单出现系统异常";

    /**
     * 更新菜单根异常
     */
    String SYS_MENU_UPDATE_ERROR_CODE = SYS_MENU + "4";
    String SYS_MENU_UPDATE_ERROR_MESSAGE = "更新菜单出现根异常";

    /**
     * 加载菜单系统异常
     */
    String SYS_MENU_SELECT_ERROR_CODE = SYS_MENU + "5";
    String SYS_MENU_SELECT_ERROR_MESSAGE = "查询菜单出现系统异常";

    /**
     * 加载菜单系统异常
     */
    String SYS_MENU_PARAMS_ERROR_CODE = SYS_MENU + "6";
    String SYS_MENU_PARAMS_ERROR_MESSAGE = "菜单参数不能为空";

    /**
     * 删除菜单系统异常
     */
    String SYS_MENU_DELETE_ERROR_CODE = SYS_MENU + "7";
    String SYS_MENU_DELETE_ERROR_MESSAGE = "删除菜单出现异常";
    /**
     * 删除菜单系统异常
     */
    String SYS_MENU_DELETE_APP_ERROR_CODE = SYS_MENU + "8";
    String SYS_MENU_DELETE_APP_ERROR_MESSAGE = "删除菜单出现异常";

}
