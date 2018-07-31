
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.model.SysMenu;
import cn.com.njdhy.muscle.biceps.service.BaseService;

import java.util.List;

/**
 * <类功能简述> 菜单业务处理接口
 *
 * @author 胡志海
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 根据用户授权查询系统菜单
     *
     * @param userName 查询条件（用户名）
     * @return 授权菜单列表
     */
    List<SysMenu> loadMenus(String userName);

    /**
     * 查询一级菜单和二级菜单
     *
     * @return
     */
    List<SysMenu> queryMenu();

    /**
     * 查询所有菜單
     *
     * @return
     */
    List<SysMenu> queryAllMenu();

    /**
     * 根据roleId查询菜单
     *
     * @param roleId 角色ID
     * @return
     */
    List<String> queryMenuByRole(String roleId);

    /**
     * 根据用户名称查询用户的菜单权限
     *
     * @param loginName 登录名称
     * @return 该用户所具有的菜单权限
     */
    List<String> queryPermissionByUserName(String loginName);

}
