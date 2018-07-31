
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.model.SysRole;
import cn.com.njdhy.muscle.biceps.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 任务调度业务处理接口
 *
 * @author 胡志海
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 加载角色列表
     *
     * @param userName 用户名称
     * @return 该用户所拥有的所有角色
     */
    List<SysRole> loadRoles(Map<String, Object> userName);


    /**
     * 根据用户名称加载角色列表
     *
     * @param userName 用户名称
     * @return 角色列表
     */
    List<String> queryRolesByUserName(String userName);

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    void insertRoleInfo(SysRole role);

    /**
     * 修改角色
     *
     * @param role
     */
    void updateRoleInfo(SysRole role);

}
