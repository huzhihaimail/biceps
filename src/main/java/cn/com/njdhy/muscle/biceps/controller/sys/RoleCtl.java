
package cn.com.njdhy.muscle.biceps.controller.sys;

import cn.com.njdhy.muscle.biceps.controller.Query;
import cn.com.njdhy.muscle.biceps.controller.Result;
import cn.com.njdhy.muscle.biceps.exception.ApplicationException;
import cn.com.njdhy.muscle.biceps.exception.sys.RoleErrorCode;
import cn.com.njdhy.muscle.biceps.model.SysRole;
import cn.com.njdhy.muscle.biceps.service.sys.SysRoleService;
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
@RequestMapping("/sys/role")
public class RoleCtl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleCtl.class);

    @Autowired
    private SysRoleService sysRoleService;

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
            PageInfo<SysRole> result = sysRoleService.queryList(queryParam, pageNumber, pageSize);

            return Result.success(result.getTotal(), result.getList());

        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage());
            return Result.error(RoleErrorCode.SYS_ROLE_QUERY_APP_ERROR_CODE, RoleErrorCode.SYS_ROLE_QUERY_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Result.error(RoleErrorCode.SYS_ROLE_QUERY_ERROR_CODE, RoleErrorCode.SYS_ROLE_QUERY_ERROR_MESSAGE);
        }
    }

    /**
     * 根据id查询角色信息
     *
     * @param id 角色ID
     * @return 角色实体
     */
    @RequestMapping("/{id}")
    public Result queryById(@PathVariable String id) {
        SysRole model=null;
        try {
            if (ObjectUtils.isEmpty(id)){
                return Result.error("500", "参数不能为空");
            }
            model = sysRoleService.queryById(id);
            if (ObjectUtils.isEmpty(model)) {
                model = new SysRole();
            }
        } catch (Exception e) {
            return Result.error(RoleErrorCode.SYS_ROLE_QUERY_ERROR_CODE,RoleErrorCode.SYS_ROLE_QUERY_ERROR_MESSAGE);
        }

        return Result.success().put("model", model);
    }


    /**
     * 保存
     *
     * @param sysRole 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/insert")
    public Result insert(@RequestBody SysRole sysRole) {

        try {
            // 校验参数
            if (ObjectUtils.isEmpty(sysRole)){
                return Result.error("500", "角色信息不能为空");
            }
            // 执行入库操作
            sysRoleService.insertRoleInfo(sysRole);
        } catch (ApplicationException e) {
            LOGGER.error(e.getMsg());
            return Result.error(RoleErrorCode.SYS_ROLE_SAVE_APP_ERROR_CODE, RoleErrorCode.SYS_ROLE_SAVE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Result.error(RoleErrorCode.SYS_ROLE_SAVE_ERROR_CODE, RoleErrorCode.SYS_ROLE_SAVE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 修改操作
     *
     * @param sysRole 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysRole sysRole) {

        try {
            // 校验参数
            if (ObjectUtils.isEmpty(sysRole)){
                return Result.error("500", "角色信息不能为空");
            }
            if (ObjectUtils.isEmpty(sysRole.getId())){
                return Result.error("500", "角色id不能为空");
            }
            // 执行修改
            sysRoleService.updateRoleInfo(sysRole);
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage());
            return Result.error(RoleErrorCode.SYS_ROLE_QUERY_APP_ERROR_CODE, RoleErrorCode.SYS_ROLE_QUERY_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Result.error(RoleErrorCode.SYS_ROLE_QUERY_ERROR_CODE, RoleErrorCode.SYS_ROLE_QUERY_ERROR_MESSAGE);
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
            if (ObjectUtils.isEmpty(ids)){
                return Result.error("500", "角色id不能为空");
            }
            sysRoleService.deleteByIds(ids);
        } catch (ApplicationException e) {
            LOGGER.error(e.getMsg());
            return Result.error(RoleErrorCode.SYS_ROLE_DELETE_APP_ERROR_CODE, RoleErrorCode.SYS_ROLE_DELETE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Result.error(RoleErrorCode.SYS_ROLE_DELETE_ERROR_CODE, RoleErrorCode.SYS_ROLE_DELETE_ERROR_MESSAGE);
        }

        return Result.success();
    }

}
