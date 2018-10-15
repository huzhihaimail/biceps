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

    private static final long serialVersionUID = 1L;

    public static final String STATUS_RUNNING = "1";
    public static final String STATUS_NOT_RUNNING = "0";
    public static final String CONCURRENT_IS = "1";
    public static final String CONCURRENT_NOT = "0";

    //
    private Integer id;
    //任务名称
    private String jobName;
    //任务分组
    private String jobGroup;
    //任务状态:(0：停用，1：启用)
    private String jobStatus;
    //cron表达式
    private String cronExpression;
    //描述
    private String description;
    //任务是否可以并行运行（0：不可以，1：可以）
    private String isConcurrent;
    //spring bean
    private String springId;
    //任务调用的方法名
    private String methodName;
    //
    private Date createTime;
    //
    private Date updateTime;

    @Override
    public String toString() {
        return "SysQuartzJob{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", description='" + description + '\'' +
                ", isConcurrent='" + isConcurrent + '\'' +
                ", springId='" + springId + '\'' +
                ", methodName='" + methodName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
