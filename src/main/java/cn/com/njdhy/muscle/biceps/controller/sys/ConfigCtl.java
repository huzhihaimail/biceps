package cn.com.njdhy.muscle.biceps.controller.sys;

import cn.com.njdhy.muscle.biceps.controller.Query;
import cn.com.njdhy.muscle.biceps.controller.Result;
import cn.com.njdhy.muscle.biceps.exception.ApplicationException;
import cn.com.njdhy.muscle.biceps.exception.sys.ConfigErrorCode;
import cn.com.njdhy.muscle.biceps.exception.sys.UserErrorCode;
import cn.com.njdhy.muscle.biceps.model.*;
import cn.com.njdhy.muscle.biceps.service.sys.SysConfigService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description
 *
 *
 * @author rain
 * @date 2018/12/29 11:10
 */
@RestController
@RequestMapping("/sys/config")
@Slf4j
public class ConfigCtl {

    @Autowired
    private SysConfigService sysConfigService;

    @RequestMapping("/list")
    public Result index(@RequestParam Map<String, Object> params, Integer pageNumber, Integer pageSize) {
        Query queryParam = new Query(params);
        PageInfo<SysConfig> result=null;
        try {
            result = sysConfigService.queryList(queryParam, pageNumber, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ConfigErrorCode.SYS_CONFIG_QUERY_ERROR_CODE,ConfigErrorCode.SYS_CONFIG_QUERY_ERROR_MESSAGE);
        }

        return Result.success(result.getTotal(), result.getList());
    }
    /**
     * 修改操作
     *
     * @param sysConfig 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysConfig sysConfig) {

        try {
            // 执行修改
            sysConfigService.update(sysConfig);
        } catch (RuntimeException e) {
            return Result.error(ConfigErrorCode.SYS_CONFIG_UPDATE_APP_ERROR_CODE, ConfigErrorCode.SYS_CONFIG_UPDATE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(ConfigErrorCode.SYS_CONFIG_UPDATE_ERROR_CODE, ConfigErrorCode.SYS_CONFIG_UPDATE_ERROR_MESSAGE);
        }

        return Result.success();
    }
    /**
     * 根据id查询用户信息
     *
     * @param id 用户ID
     * @return 用户实体
     */
    @RequestMapping("/{id}")
    public Result queryById(@PathVariable String id) {
        SysConfig model=null;
        try {
            model= sysConfigService.queryById(id);

            if (ObjectUtils.isEmpty(model)) {
                model = new SysConfig();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ConfigErrorCode.SYS_CONFIG_QUERY_ERROR_CODE,ConfigErrorCode.SYS_CONFIG_QUERY_ERROR_MESSAGE);
        }

        return Result.success().put("model", model);
    }

    /**
     * 保存
     *
     * @param sysConfig 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/insert")
    public Result insert(@RequestBody SysConfig sysConfig) {

        try {
            // 校验参数
            sysConfigService.saveConfig(sysConfig);
        } catch (ApplicationException e) {
            e.printStackTrace();
            return Result.error(ConfigErrorCode.SYS_CONFIG_SAVE_APP_ERROR_CODE, ConfigErrorCode.SYS_CONFIG_SAVE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ConfigErrorCode.SYS_CONFIG_SAVE_ERROR_CODE, ConfigErrorCode.SYS_CONFIG_SAVE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 删除一个记录
     *
     * @param ids 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/delete")
    public Result deleteByIds(@RequestBody List<String> ids) {

        try {

            sysConfigService.deleteByIds(ids);
        } catch (ApplicationException e) {
            e.printStackTrace();
            return Result.error(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

        return Result.success();
    }

    /**
     * 查询所有父级id
     * @return
     */
    @RequestMapping("/queryAllParentId")
    public Result selectAllParentId() {
        List<SysConfig> list=null;
        try {
            list= sysConfigService.selectByParentId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success().put("parentIds",list);
    }

}
