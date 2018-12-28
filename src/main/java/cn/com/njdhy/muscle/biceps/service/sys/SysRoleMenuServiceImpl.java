package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.dao.SysRoleMenuDao;
import cn.com.njdhy.muscle.biceps.model.SysRoleMenu;
import cn.com.njdhy.muscle.biceps.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao,SysRoleMenu> implements SysRoleMenuService{

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public int deleteById(String roleId) {
        return sysRoleMenuDao.deleteById(roleId);
    }

    @Override
    public List<SysRoleMenu> queryMenuByRoleId(String roleId) {
        return sysRoleMenuDao.queryMenuByRoleId(roleId);
    }
}
