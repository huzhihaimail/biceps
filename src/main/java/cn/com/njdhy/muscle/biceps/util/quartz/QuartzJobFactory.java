package cn.com.njdhy.muscle.biceps.util.quartz;


import cn.com.njdhy.muscle.biceps.model.SysQuartzJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 计划任务执行处 无状态
 * 当job的一个trigger被触发后，execute()方法会被scheduler的一个工作线程调用；
 * 传递给execute()方法的JobExecutionContext对象中保存着该job运行时的一些信息 ，执行job的scheduler的引用，
 * 触发job的trigger的引用，JobDetail对象引用，以及一些其它信息。
 * <p>
 * Created by huaijie on 2017/9/25 15:10.
 */
public class QuartzJobFactory implements Job {
    public final Logger log = LoggerFactory.getLogger(QuartzJobFactory.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SysQuartzJob sysQuartzJob = (SysQuartzJob) context.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.invokMethod(sysQuartzJob);
    }
}
