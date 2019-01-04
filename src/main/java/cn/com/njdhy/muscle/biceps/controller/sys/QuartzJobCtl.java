
package cn.com.njdhy.muscle.biceps.controller.sys;

import cn.com.njdhy.muscle.biceps.controller.Query;
import cn.com.njdhy.muscle.biceps.controller.Result;
import cn.com.njdhy.muscle.biceps.exception.ApplicationException;
import cn.com.njdhy.muscle.biceps.exception.sys.QuartzJobErrorCode;
import cn.com.njdhy.muscle.biceps.model.SysQuartzJob;
import cn.com.njdhy.muscle.biceps.service.sys.SysQuartzJobService;
import cn.com.njdhy.muscle.biceps.util.EmptyUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 角色控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/sys/quartzJob")
public class QuartzJobCtl {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobCtl.class);

    @Autowired
    private SysQuartzJobService sysQuartzJobService;

    /**
     * 查询角色列表
     *
     * @param params     参数列表
     * @param pageNumber 当前页码
     * @param pageSize   每页大小
     * @return 角色列表
     */
    @RequestMapping("/list")
    public Result index(@RequestParam Map<String, Object> params, Integer pageNumber, Integer pageSize) {

        try {
            Query queryParam = new Query(params);
            PageInfo<SysQuartzJob> result = sysQuartzJobService.queryList(queryParam, pageNumber, pageSize);

            return Result.success(result.getTotal(), result.getList());

        } catch (RuntimeException e) {
            return Result.error(QuartzJobErrorCode.SYS_QUARTZJOB_QUERY_APP_ERROR_CODE, QuartzJobErrorCode.SYS_QUARTZJOB_QUERY_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(QuartzJobErrorCode.SYS_QUARTZJOB_QUERY_ERROR_CODE, QuartzJobErrorCode.SYS_QUARTZJOB_QUERY_ERROR_MESSAGE);
        }
    }

    /**
     * 根据id查询角色信息
     *
     * @param id 角色ID
     * @return 定时任务实体
     */
    @RequestMapping("/{id}")
    public Result queryById(@PathVariable String id) {
        SysQuartzJob model=null;
        try {
            if (EmptyUtils.isEmpty(id)) {
                return Result.error("请选择一条数据");
            }
            model = sysQuartzJobService.queryById(id);
            if (ObjectUtils.isEmpty(model)) {
                model = new SysQuartzJob();
            }
        } catch (Exception e) {
            return Result.error(QuartzJobErrorCode.SYS_QUARTZJOB_QUERY_ERROR_CODE,QuartzJobErrorCode.SYS_QUARTZJOB_QUERY_ERROR_MESSAGE);
        }

        return Result.success().put("model", model);
    }

    /**
     * 保存
     *
     * @param sysQuartzJob 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/insert")
    public Result insert(@RequestBody SysQuartzJob sysQuartzJob) {

        try {
            // 校验参数
            if (EmptyUtils.isEmpty(sysQuartzJob.getJobName())) {
                return Result.error("500","任务名称不可以是空!");
            }
            // 执行入库操作
            sysQuartzJobService.saveQuartzJob(sysQuartzJob);
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            return Result.error(QuartzJobErrorCode.SYS_QUARTZJOB_SAVE_APP_ERROR_CODE, QuartzJobErrorCode.SYS_QUARTZJOB_SAVE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Result.error(QuartzJobErrorCode.SYS_QUARTZJOB_SAVE_ERROR_CODE, QuartzJobErrorCode.SYS_QUARTZJOB_SAVE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 修改操作
     *
     * @param sysQuartzJob 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysQuartzJob sysQuartzJob) {

        try {
            // 校验参数
            if (EmptyUtils.isEmpty(sysQuartzJob.getJobName())) {
                return Result.error("请填写任务名称");
            }
            // 执行修改
            sysQuartzJobService.update(sysQuartzJob);
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage());
            return Result.error(QuartzJobErrorCode.SYS_QUARTZJOB_UPDATE_APP_ERROR_CODE, QuartzJobErrorCode.SYS_QUARTZJOB_UPDATE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Result.error(QuartzJobErrorCode.SYS_QUARTZJOB_UPDATE_ERROR_CODE, QuartzJobErrorCode.SYS_QUARTZJOB_UPDATE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 删除多个记录
     *
     * @param ids 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/delete")
    public Result deleteByIds(@RequestBody List<String> ids) {

        try {
            // 校验参数
            if (EmptyUtils.isEmpty(ids)) {
                return Result.error("请选择删除的对象");
            }
            sysQuartzJobService.deleteByIds(ids);
        } catch (ApplicationException e) {
            return Result.error(QuartzJobErrorCode.SYS_QUARTZJOB_DELETE_APP_ERROR_CODE, QuartzJobErrorCode.SYS_QUARTZJOB_DELETE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(QuartzJobErrorCode.SYS_QUARTZJOB_DELETE_ERROR_CODE, QuartzJobErrorCode.SYS_QUARTZJOB_DELETE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 启动任务
     *
     * @param ids
     * @return
     */
    @RequestMapping("/changeJobStart")
    public Result changeStart(@RequestBody List<String> ids) {

        try {
            // 校验参数
            if (EmptyUtils.isEmpty(ids)) {
                return Result.error("请选择启动的对象");
            }
            sysQuartzJobService.changeJobStart(ids.get(0));
        } catch (ApplicationException e) {
            return Result.error(e.getCode(), e.getMsg());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    /**
     * 停止任务
     *
     * @param ids
     * @return
     */
    @RequestMapping("/changeJobStop")
    public Result changeStop(@RequestBody List<String> ids) {

        try {
            // 校验参数
            if (EmptyUtils.isEmpty(ids)) {
                return Result.error("请选择要停止的对象");
            }
            sysQuartzJobService.changeJobStop(ids.get(0));
        } catch (ApplicationException e) {
            return Result.error(e.getCode(), e.getMsg());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }
}
