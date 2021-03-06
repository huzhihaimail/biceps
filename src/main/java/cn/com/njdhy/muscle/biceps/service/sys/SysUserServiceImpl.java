
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.dao.SysUserDao;
import cn.com.njdhy.muscle.biceps.dao.SysUserRoleDao;
import cn.com.njdhy.muscle.biceps.model.SysUser;
import cn.com.njdhy.muscle.biceps.model.SysUserRole;
import cn.com.njdhy.muscle.biceps.service.BaseServiceImpl;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <类功能简述> 用户管理业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 插入用户
     *
     * @param sysUser 用户信息（包含角色信息）
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public void saveUser(SysUser sysUser) {

        // 获取密码盐
        String salt = new SecureRandomNumberGenerator().nextBytes(3).toHex();
        sysUser.setSalt(salt);
        // 获取密码
        String pwd = new SimpleHash("md5", sysUser.getPassword(), sysUser.getUserName() + salt, 3).toHex();
        sysUser.setPassword(pwd);
        // 用户信息入库
        dao.insert(sysUser);
        List<SysUserRole> sysUserRolesLst = new ArrayList<>();
        // 获取角色信息
        List<String> roles = sysUser.getUserRoles();
        // 获取用户ID
        String userId = String.valueOf(sysUser.getId());

        for (String roleId : roles) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(roleId);
            sysUserRolesLst.add(sysUserRole);
        }

        // 用户配置角色信息入库
        sysUserRoleService.batchInsert(sysUserRolesLst);
    }

    /**
     * 根据用户id查询用户信息和角色信息(修改用户时用)
     *
     * @param sysUser 查询参数
     * @return 用户信息
     */
    @Override
    public SysUser queryUserInfo(SysUser sysUser) {

        // 查询用户信息
        SysUser user = this.dao.queryById(String.valueOf(sysUser.getId()));
        // 查询用户的角色信息
        List<SysUserRole> roleList = sysUserRoleService.queryRoleList(String.valueOf(sysUser.getId()));
        List<String> list = new ArrayList<>();
        for (SysUserRole role : roleList){
            list.add(role.getRoleId());
        }
        user.setUserRoles(list);

        return user;
    }

    /**
     * 修改用户
     * @param sysUser
     */
    @Override
    public void updateUser(SysUser sysUser) {
        //修改用户
        dao.update(sysUser);
        //删除用户角色关联表
        sysUserRoleDao.deleteByUserId(String.valueOf(sysUser.getId()));
        //重新增加用户角色关联表
        List<SysUserRole> sysUserRolesLst = new ArrayList<>();
        // 获取角色信息
        List<String> roles = sysUser.getUserRoles();

        for (String roleId : roles) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(roleId);
            sysUserRolesLst.add(sysUserRole);
        }

        // 用户配置角色信息入库
        sysUserRoleService.batchInsert(sysUserRolesLst);
    }

    /**
     * 删除用户
     * @param ids
     */
    @Transactional
    @Override
    public void deleteUser(List<String> ids) {
        //删除用户
        dao.deleteByIds(ids);
        //删除用户角色关联信息
        for (String id:ids){
            sysUserRoleDao.deleteByUserId(id);
        }
    }


}
