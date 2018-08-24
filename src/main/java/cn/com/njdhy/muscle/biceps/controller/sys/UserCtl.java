
package cn.com.njdhy.muscle.biceps.controller.sys;

import cn.com.njdhy.muscle.biceps.controller.Query;
import cn.com.njdhy.muscle.biceps.controller.Result;
import cn.com.njdhy.muscle.biceps.exception.ApplicationException;
import cn.com.njdhy.muscle.biceps.exception.sys.UserErrorCode;
import cn.com.njdhy.muscle.biceps.model.SysRole;
import cn.com.njdhy.muscle.biceps.model.SysUser;
import cn.com.njdhy.muscle.biceps.service.sys.SysRoleService;
import cn.com.njdhy.muscle.biceps.service.sys.SysUserService;
import cn.com.njdhy.muscle.biceps.util.ShiroUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 用户管理控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/sys/user")
public class UserCtl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCtl.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询用户列表
     *
     * @param params     参数列表
     * @param pageNumber 当前页码
     * @param pageSize   每页大小
     * @return 用户列表
     */
    @RequestMapping("/list")
    public Result index(@RequestParam Map<String, Object> params, Integer pageNumber, Integer pageSize) {
        Query queryParam = new Query(params);
        PageInfo<SysUser> result = sysUserService.queryList(queryParam, pageNumber, pageSize);

        return Result.success(result.getTotal(), result.getList());
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 用户ID
     * @return 用户实体
     */
    @RequestMapping("/{id}")
    public Result queryById(@PathVariable String id) {
        SysUser user = new SysUser();
        user.setId(Integer.valueOf(id));
        SysUser model = sysUserService.queryUserInfo(user);

        if (ObjectUtils.isEmpty(model)) {
            model = new SysUser();
        }

        return Result.success().put("model", model);
    }


    /**
     * 保存
     *
     * @param sysUser 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/insert")
    public Result insert(@RequestBody SysUser sysUser) {

        try {
            // 校验参数
            // TODO: 2018/3/14
            // 执行入库操作
            sysUserService.saveUser(sysUser);
        } catch (ApplicationException e) {
            return Result.error(UserErrorCode.SYS_USER_SAVE_APP_ERROR_CODE, UserErrorCode.SYS_USER_SAVE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(UserErrorCode.SYS_USER_SAVE_ERROR_CODE, UserErrorCode.SYS_USER_SAVE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 修改操作
     *
     * @param sysUser 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysUser sysUser) {

        try {
            // 校验参数
            // TODO: 2018/3/14

            // 执行修改
            sysUserService.update(sysUser);
        } catch (RuntimeException e) {
            return Result.error(UserErrorCode.SYS_USER_UPDATE_APP_ERROR_CODE, UserErrorCode.SYS_USER_UPDATE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(UserErrorCode.SYS_USER_UPDATE_ERROR_CODE, UserErrorCode.SYS_USER_UPDATE_ERROR_MESSAGE);
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
            // 校验参数 todo
            sysUserService.deleteByIds(ids);
        } catch (ApplicationException e) {
            return Result.error(e.getCode(), e.getMsg());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

        return Result.success();
    }

    /**
     * 加载角色列表
     *
     * @return 角色列表
     */
    @RequestMapping("/loadRoles")
    public Result loadRoles() {

        try {
            // 校验参数

            // 获取登用户名
            String loginUserName = ShiroUtil.getLoginUserName();

            if (StringUtils.isEmpty(loginUserName)) {
                return Result.error(UserErrorCode.SYS_USER_LOAD_ROLES_APP_ERROR_CODE, UserErrorCode.SYS_USER_LOAD_ROLES_APP_ERROR_MESSAGE);
            }


            // 设置查询参数
            Map<String, Object> params = new HashMap<>();
            params.put("userName", loginUserName);

            Query query = new Query(params);

            List<SysRole> rolesLst = sysRoleService.loadRoles(query);
            return Result.success().put("page", rolesLst);
        } catch (RuntimeException e) {
            return Result.error(UserErrorCode.SYS_USER_LOAD_ROLES_APP_ERROR_CODE, UserErrorCode.SYS_USER_LOAD_ROLES_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(UserErrorCode.SYS_USER_LOAD_ROLES_ERROR_CODE, UserErrorCode.SYS_USER_LOAD_ROLES_ERROR_MESSAGE);
        }
    }

}
