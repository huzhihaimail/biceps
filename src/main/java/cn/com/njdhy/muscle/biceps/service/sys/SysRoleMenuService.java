package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.model.SysRoleMenu;
import cn.com.njdhy.muscle.biceps.service.BaseService;

import java.util.List;

public interface SysRoleMenuService extends BaseService<SysRoleMenu> {

    /**
     * 根据roleId删除所有菜单
     * @param roleId
     * @return
     */
    int deleteById(String roleId);

    /**
     * 根据roleId查询该用户所有的菜单
     *
     * @param roleId 角色
     * @return 角色具有的菜单列表
     */
    List<SysRoleMenu> queryMenuByRoleId(String roleId);
}
