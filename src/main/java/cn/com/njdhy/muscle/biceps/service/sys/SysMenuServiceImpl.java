
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.dao.SysMenuDao;
import cn.com.njdhy.muscle.biceps.dao.SysRoleMenuDao;
import cn.com.njdhy.muscle.biceps.model.SysMenu;
import cn.com.njdhy.muscle.biceps.model.SysRoleMenu;
import cn.com.njdhy.muscle.biceps.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <类功能简述> 菜单业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {


    @Override
    public List<SysMenu> loadMenus(String userName) {
        return dao.loadMenus(userName);
    }

    /**
     * 查询一级和二级菜单
     *
     * @return
     */
    @Override
    public List<SysMenu> queryMenu() {
        return dao.queryMenu();
    }

    /**
     * 查詢所有菜單
     *
     * @return
     */
    @Override
    public List<SysMenu> queryAllMenu() {
        return dao.queryMenu();
    }

    @Override
    public List<String> queryMenuByRole(String roleId) {
        /*SysRoleMenu roleMenu = new SysRoleMenu();
        roleMenu.setRoleId(roleId);
        List<SysRoleMenu> roleMenuList = sysRoleMenuDao.queryMenuByRoleId(roleMenu);

        List<String> menuList = new ArrayList<>();
        if (!EmptyUtils.isEmpty(roleMenuList)){
            for (SysRoleMenu detail:roleMenuList){
                menuList.add(detail.getMenuId());
            }
        }*/
        return new ArrayList<String>();
    }

    @Override
    public List<String> queryPermissionByUserName(String loginName) {
        return this.dao.queryPermissionByUserName(loginName);
    }
}
