
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.dao.SysRoleDao;
import cn.com.njdhy.muscle.biceps.dao.SysRoleMenuDao;
import cn.com.njdhy.muscle.biceps.model.SysRole;
import cn.com.njdhy.muscle.biceps.model.SysRoleMenu;
import cn.com.njdhy.muscle.biceps.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 角色业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    protected SysRoleMenuDao roleMenuDao;

    @Override
    public List<SysRole> loadRoles(Map<String, Object> userName) {
        return dao.loadRoles(userName);
    }

    @Override
    public List<String> queryRolesByUserName(String userName) {

        Map<String, Object> query = new HashMap<>();
        query.put("userName", userName);

        return dao.queryRolesByUserName(query);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @Override
    public void insertRoleInfo(SysRole role) {

        try {
            //新增角色
            SysRole detail = new SysRole();
            detail.setName(role.getName());
            detail.setNameCn(role.getNameCn());
            detail.setStatus("0");
            this.dao.insert(detail);

            //新增角色菜單關聯表
            List<SysRoleMenu> list = new ArrayList<>();
            for (String menuId:role.getMenuIdList()){
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(String.valueOf(detail.getId()));
                roleMenu.setMenuId(menuId);
                list.add(roleMenu);
            }
            roleMenuDao.batchInsert(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改角色
     * @param role
     */
    @Override
    public void updateRoleInfo(SysRole role) {
        try {
            //修改菜单
            SysRole detail = new SysRole();
            detail.setId(role.getId());
            detail.setStatus("0");
            detail.setDeleteFlag("0");
            detail.setName(role.getName());
            detail.setNameCn(role.getNameCn());
            this.dao.update(detail);

            //删除该角色所有的菜单关系
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(role.getId()));
            roleMenuDao.deleteByIds(list);

            //重新增加该角色所有的菜单关系
            List<SysRoleMenu> roleMenuList = new ArrayList<>();
            for (String menuId:role.getMenuIdList()){
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(String.valueOf(role.getId()));
                roleMenu.setMenuId(menuId);
                roleMenuList.add(roleMenu);
            }
            roleMenuDao.batchInsert(roleMenuList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
