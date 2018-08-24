package cn.com.njdhy.muscle.biceps.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <一句话功能简述> 任务调度表领域模型
 * <功能详细描述>
 *
 * @author 胡志海
 */
@Getter
@Setter
public class SysQuartzJob {

    /**
     * 记录ID
     */
    private Integer id;

    /**
     * 任务ID
     */
    private String jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务状态:(0：停用，1：启用)
     */
    private String jobStatus;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 描述
     */
    private String description;

    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String beanClass;

    /**
     * 任务是否可以并行运行（0：不可以，1：可以）
     */
    private String isConcurrent;

    /**
     * spring bean
     */
    private String springId;

    /**
     * 任务调用的方法名
     */
    private String methodName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;



}
