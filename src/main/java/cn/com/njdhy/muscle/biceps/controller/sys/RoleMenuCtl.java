
package cn.com.njdhy.muscle.biceps.controller.sys;

import cn.com.njdhy.muscle.biceps.controller.Result;
import cn.com.njdhy.muscle.biceps.model.SysRoleMenu;
import cn.com.njdhy.muscle.biceps.service.sys.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <类功能简述> 角色控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/sys/roleMenu")
public class RoleMenuCtl {


    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    /**
     * 根据id查询角色信息
     *
     * @param id 角色ID
     * @return 角色实体
     */
    @RequestMapping("/{id}")
    public Result queryById(@PathVariable String id) {
        List<SysRoleMenu> model=null;
        try {
            if (ObjectUtils.isEmpty(id)) {
                return Result.error("500","查询角色信息参数不能为空!");
            }
            model = sysRoleMenuService.queryMenuByRoleId(id);
        } catch (Exception e) {
            return Result.error("500","查询角色信息出错");
        }

        return Result.success().put("model", model);
    }


}
