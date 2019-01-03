
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.model.SysUserRole;
import cn.com.njdhy.muscle.biceps.service.BaseService;

import java.util.List;

/**
 * <类功能简述> 用户角色业务处理接口
 *
 * @author 胡志海
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {


    /**
     * 根据用户id查询用户的角色
     * @param userId
     * @return
     */
    List<SysUserRole> queryRoleList(String userId);

}
