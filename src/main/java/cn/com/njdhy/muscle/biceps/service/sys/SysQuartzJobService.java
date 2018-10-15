
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.model.SysQuartzJob;
import cn.com.njdhy.muscle.biceps.service.BaseService;

/**
 * <类功能简述> 任务调度业务处理接口
 *
 * @author 胡志海
 */
public interface SysQuartzJobService extends BaseService<SysQuartzJob> {

    /**
     * 新增定时任务
     * @param sysQuartzJob
     */
    void saveQuartzJob(SysQuartzJob sysQuartzJob);

    void changeJobStart(String id);

    void changeJobStop(String id);

    void addJob(SysQuartzJob job) throws Exception;

    void deleteJob(SysQuartzJob sysQuartzJob) throws Exception;

}
