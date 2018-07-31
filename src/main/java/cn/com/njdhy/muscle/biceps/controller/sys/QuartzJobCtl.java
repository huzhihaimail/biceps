
package cn.com.njdhy.muscle.biceps.controller.sys;

import cn.com.njdhy.muscle.biceps.controller.Query;
import cn.com.njdhy.muscle.biceps.controller.Result;
import cn.com.njdhy.muscle.biceps.exception.ApplicationException;
import cn.com.njdhy.muscle.biceps.model.SysQuartzJob;
import cn.com.njdhy.muscle.biceps.service.sys.SysQuartzJobService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <类功能简述> 任务调度控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/sys/quartzJob")
public class QuartzJobCtl {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobCtl.class);

    @Autowired
    private SysQuartzJobService quartzJobService;

    /**
     * 查询所有任务集合
     *
     * @param params 查询参数
     * @return 查询结果列表
     */
    @RequestMapping("/list")
    public Result queryList(@RequestParam Map<String, Object> params, int pageNum, int pageSize) {

        try {
            Query queryParam = new Query(params);
            PageInfo<SysQuartzJob> result = quartzJobService.queryList(queryParam, pageNum, pageSize);

            return Result.success().put("page", result);
        } catch (ApplicationException e) {
            LOGGER.error("QuartzJobCtl | queryList | {}", e.getMsg());
            return Result.error(e.getCode(), e.getMsg());
        } catch (Exception e) {
            LOGGER.error("QuartzJobCtl | queryList | ", e);
            return Result.error(e.getMessage());

        }
    }
}
