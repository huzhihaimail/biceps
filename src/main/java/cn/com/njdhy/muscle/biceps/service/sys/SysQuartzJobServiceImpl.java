
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.dao.SysQuartzJobDao;
import cn.com.njdhy.muscle.biceps.exception.ApplicationException;
import cn.com.njdhy.muscle.biceps.model.SysQuartzJob;
import cn.com.njdhy.muscle.biceps.service.BaseServiceImpl;
import cn.com.njdhy.muscle.biceps.util.quartz.QuartzJobFactory;
import cn.com.njdhy.muscle.biceps.util.quartz.QuartzJobFactoryDisallowConcurrentExecution;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <类功能简述> 任务调度业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysQuartzJobServiceImpl extends BaseServiceImpl<SysQuartzJobDao, SysQuartzJob> implements SysQuartzJobService {

    @Autowired
    private SysQuartzJobService sysQuartzJobService;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @PostConstruct
    public void init() throws Exception {
        // 这里获取任务信息数据
        List<SysQuartzJob> jobList = this.dao.queryAllList();
        for (SysQuartzJob job : jobList) {
            addJob(job);
        }
    }

    @Override
    public void saveQuartzJob(SysQuartzJob sysQuartzJob) {
        sysQuartzJob.setJobStatus("0");
        sysQuartzJobService.insert(sysQuartzJob);
    }

    @Override
    public void changeJobStart(String id) {
        try {
            SysQuartzJob sysQuartzJob = dao.queryById(id);
            if (sysQuartzJob == null) {
                throw new ApplicationException("500", "任务信息不存在");
            }
            if (sysQuartzJob.getJobStatus().equals(SysQuartzJob.STATUS_RUNNING)) {
                throw new ApplicationException("500", "该任务已启用");
            }
            SysQuartzJob detail = new SysQuartzJob();
            detail.setId(Integer.valueOf(id));
            detail.setJobStatus(SysQuartzJob.STATUS_RUNNING);
            sysQuartzJob.setJobStatus(SysQuartzJob.STATUS_RUNNING);
            addJob(sysQuartzJob);
            this.dao.update(detail);
        } catch (ApplicationException e) {
            throw new ApplicationException(e.getCode(), e.getMsg());
        } catch (Exception e) {
            throw new ApplicationException("500", "定时器异常");
        }
    }

    @Override
    public void changeJobStop(String id) {
        try {
            SysQuartzJob sysQuartzJob = dao.queryById(id);
            if (sysQuartzJob == null) {
                return;
            }
            if (sysQuartzJob.getJobStatus().equals(SysQuartzJob.STATUS_NOT_RUNNING)) {
                throw new ApplicationException("500", "该任务已停止");
            }
            deleteJob(sysQuartzJob);
            SysQuartzJob detail = new SysQuartzJob();
            detail.setId(Integer.valueOf(id));
            detail.setJobStatus(SysQuartzJob.STATUS_NOT_RUNNING);
            this.dao.update(detail);
        } catch (ApplicationException e) {
            throw new ApplicationException(e.getCode(), e.getMsg());
        } catch (Exception e) {
            throw new ApplicationException("500", "定时器异常");
        }
    }

    @Override
    public void addJob(SysQuartzJob job) throws Exception {
        if (job == null || !SysQuartzJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            return;
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName() + job.getId(), job.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 不存在，创建一个
        if (null == trigger) {
            Class clazz = SysQuartzJob.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;
            //JobDetail对象是在将job加入scheduler时，由客户端程序创建的,它包含job的各种属性设置
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob", job);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            // Trigger用于触发Job的执行
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
            // 使用定义的触发器trigger安排执行任务job
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    @Override
    public void deleteJob(SysQuartzJob sysQuartzJob) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sysQuartzJob.getJobName(), sysQuartzJob.getJobGroup());
        if (jobKey != null) {
            scheduler.deleteJob(jobKey);
        }
    }
}
