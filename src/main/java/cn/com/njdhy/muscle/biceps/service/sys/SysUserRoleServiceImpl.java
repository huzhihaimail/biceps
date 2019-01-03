
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.dao.SysUserRoleDao;
import cn.com.njdhy.muscle.biceps.model.SysUserRole;
import cn.com.njdhy.muscle.biceps.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <类功能简述> 用户角色业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    @Resource
    private SysUserRoleDao dao;

    @Override
    public List<SysUserRole> queryRoleList(String userId) {
        List<SysUserRole> roleList = dao.queryUserRoleList(userId);
        return roleList;
    }
}
