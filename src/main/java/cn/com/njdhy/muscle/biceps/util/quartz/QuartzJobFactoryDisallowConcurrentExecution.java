package cn.com.njdhy.muscle.biceps.util.quartz;


import cn.com.njdhy.muscle.biceps.model.SysQuartzJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 不允许并发执行
 * 若一个方法一次执行不完下次轮转时则等待该方法执行完后才执行下一次操作
 * <p>
 * Created by huaijie on 2017/9/25 15:14.
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {

    public final Logger logger = LoggerFactory.getLogger(QuartzJobFactoryDisallowConcurrentExecution.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SysQuartzJob sysQuartzJob = (SysQuartzJob) context.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.invokMethod(sysQuartzJob);
    }
}
