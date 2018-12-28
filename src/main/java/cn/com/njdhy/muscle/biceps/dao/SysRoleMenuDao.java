
package cn.com.njdhy.muscle.biceps.dao;

import cn.com.njdhy.muscle.biceps.model.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色菜单关联
 *
 * @author 胡志海
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {

    /**
     * 根据roleId查询该用户所有的菜单
     *
     * @param roleId 角色
     * @return 角色具有的菜单列表
     */
    List<SysRoleMenu> queryMenuByRoleId(String roleId);

    /**
     * 根据roleId删除所有菜单
     * @param roleId
     * @return
     */
    int deleteById(String roleId);

}
