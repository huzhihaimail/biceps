
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.dao.SysQuartzJobDao;
import cn.com.njdhy.muscle.biceps.model.SysQuartzJob;
import cn.com.njdhy.muscle.biceps.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <类功能简述> 任务调度业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysQuartzJobServiceImpl extends BaseServiceImpl<SysQuartzJobDao, SysQuartzJob> implements SysQuartzJobService {

    @Autowired
    private SysQuartzJobService sysQuartzJobService;

    @Override
    public void saveQuartzJob(SysQuartzJob sysQuartzJob) {
        sysQuartzJob.setJobStatus("1");
        sysQuartzJobService.insert(sysQuartzJob);
    }
}
