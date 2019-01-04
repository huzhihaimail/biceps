package cn.com.njdhy.muscle.biceps.exception.sys;

/**
 * @author rain
 * @description 异常码常量类
 * @date 2018/12/29 11:25
 */
public interface ConfigErrorCode {

    /**
     * 首页
     */
    String SYS_CONFIG = "1110";


    /**
     * 新建参数系统异常
     */
    String SYS_CONFIG_SAVE_APP_ERROR_CODE = SYS_CONFIG + "1";
    String SYS_CONFIG_SAVE_APP_ERROR_MESSAGE = "新建参数出现系统异常";

    /**
     * 新建参数
     */
    String SYS_CONFIG_SAVE_ERROR_CODE = SYS_CONFIG + "2";
    String SYS_CONFIG_SAVE_ERROR_MESSAGE = "新建参数出现根异常";

    /**
     * 更新参数
     */
    String SYS_CONFIG_UPDATE_APP_ERROR_CODE = SYS_CONFIG + "3";
    String SYS_CONFIG_UPDATE_APP_ERROR_MESSAGE = "更新参数出现系统异常";

    /**
     * 更新参数
     */
    String SYS_CONFIG_UPDATE_ERROR_CODE = SYS_CONFIG + "4";
    String SYS_CONFIG_UPDATE_ERROR_MESSAGE = "更新参数出现根异常";

    /**
     * 查询参数
     */
    String SYS_CONFIG_QUERY_APP_ERROR_CODE = SYS_CONFIG + "5";
    String SYS_CONFIG_QUERY_APP_ERROR_MESSAGE = "查询参数出现系统异常";

    /**
     * 查询参数
     */
    String SYS_CONFIG_QUERY_ERROR_CODE = SYS_CONFIG + "6";
    String SYS_CONFIG_QUERY_ERROR_MESSAGE = "查询参数出现根异常";

    /**
     * 删除参数
     */
    String SYS_CONFIG_DELETE_APP_ERROR_CODE = SYS_CONFIG + "7";
    String SYS_CONFIG_DELETE_APP_ERROR_MESSAGE = "删除参数出现系统异常";

    /**
     * 删除参数
     */
    String SYS_CONFIG_DELETE_ERROR_CODE = SYS_CONFIG + "8";
    String SYS_CONFIG_DELETE_ERROR_MESSAGE = "删除参数出现根异常";

    String SYS_CONFIG_PARAMS_ERROR_CODE = SYS_CONFIG + "9";
    String SYS_CONFIG_PARAMS_ERROR_MESSAGE =  "参数不能为空";
}
