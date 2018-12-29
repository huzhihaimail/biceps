package cn.com.njdhy.muscle.biceps.util.quartz;

import cn.com.njdhy.muscle.biceps.model.SysQuartzJob;
import cn.com.njdhy.muscle.biceps.util.EmptyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * task工具类
 *
 * Created by huaijie on 2017/9/25 15:10.
 */
public class TaskUtils {

    public final static Logger logger = LoggerFactory.getLogger(TaskUtils.class);

    /**
     * 通过反射调用scheduleJob中定义的方法
     *
     * @param sysQuartzJob
     */
    public static void invokMethod(SysQuartzJob sysQuartzJob) {
        Object object = null;
        Class clazz = null;
        if (!EmptyUtils.isEmpty(sysQuartzJob.getSpringId())) {
            object = SpringUtils.getBean(sysQuartzJob.getSpringId());
        }
//        else if (!EmptyUtils.isEmpty(scheduleJob.getBeanClass())) {
//            try {
//                clazz = Class.forName(scheduleJob.getBeanClass());
//                object = clazz.newInstance();
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        }
        if (object == null) {
            logger.error("任务名称 = [" + sysQuartzJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(sysQuartzJob.getMethodName());
        } catch (NoSuchMethodException e) {
            logger.error("任务名称 = [" + sysQuartzJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (method != null) {
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        logger.info("任务名称 = [" + sysQuartzJob.getJobName() + "]----------启动成功");
    }

}
