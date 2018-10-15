
package cn.com.njdhy.muscle.biceps.exception.sys;

/**
 * <类功能简述> 异常码常量类
 *
 * @author 胡志海
 */
public interface QuartzJobErrorCode {

    /**
     * 首页
     */
    String SYS_QUARTZJOB = "3000";


    /**
     * 新建角色系统异常
     */
    String SYS_QUARTZJOB_SAVE_APP_ERROR_CODE = SYS_QUARTZJOB + "1";
    String SYS_QUARTZJOB_SAVE_APP_ERROR_MESSAGE = "新建定时任务出现系统异常";

    /**
     * 新建角色根异常
     */
    String SYS_QUARTZJOB_SAVE_ERROR_CODE = SYS_QUARTZJOB + "2";
    String SYS_QUARTZJOB_SAVE_ERROR_MESSAGE = "新建定时任务出现根异常";

    /**
     * 更新角色系统异常
     */
    String SYS_QUARTZJOB_UPDATE_APP_ERROR_CODE = SYS_QUARTZJOB + "3";
    String SYS_QUARTZJOB_UPDATE_APP_ERROR_MESSAGE = "更新定时任务出现系统异常";

    /**
     * 更新角色根异常
     */
    String SYS_QUARTZJOB_UPDATE_ERROR_CODE = SYS_QUARTZJOB + "4";
    String SYS_QUARTZJOB_UPDATE_ERROR_MESSAGE = "更新定时任务出现根异常";

    /**
     * 查询角色系统异常
     */
    String SYS_QUARTZJOB_QUERY_APP_ERROR_CODE = SYS_QUARTZJOB + "5";
    String SYS_QUARTZJOB_QUERY_APP_ERROR_MESSAGE = "新建定时任务出现系统异常";

    /**
     * 查询角色系统异常
     */
    String SYS_QUARTZJOB_QUERY_ERROR_CODE = SYS_QUARTZJOB + "6";
    String SYS_QUARTZJOB_QUERY_ERROR_MESSAGE = "新建定时任务出现根异常";

    /**
     * 删除角色系统异常
     */
    String SYS_QUARTZJOB_DELETE_APP_ERROR_CODE = SYS_QUARTZJOB + "7";
    String SYS_QUARTZJOB_DELETE_APP_ERROR_MESSAGE = "删除定时任务出现系统异常";

    /**
     * 删除角色系统异常
     */
    String SYS_QUARTZJOB_DELETE_ERROR_CODE = SYS_QUARTZJOB + "8";
    String SYS_QUARTZJOB_DELETE_ERROR_MESSAGE = "删除定时任务出现根异常";
}
