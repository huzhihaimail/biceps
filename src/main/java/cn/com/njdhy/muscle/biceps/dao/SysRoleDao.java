
package cn.com.njdhy.muscle.biceps.dao;

import cn.com.njdhy.muscle.biceps.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色数据访问层接口
 *
 * @author 胡志海
 */
public interface SysRoleDao extends BaseDao<SysRole> {

    /**
     * 加载角色列表
     *
     * @param userName 用户名称
     * @return 该用户所拥有的所有角色
     */
    List<SysRole> loadRoles(Map<String, Object> userName);

    /**
     * 根据用户名称查询用户角色信息
     *
     * @param map 用户名查询参数
     * @return 用户角色列表
     */
    List<String> queryRolesByUserName(Map<String, Object> map);

    /**
     * 根据用户名查询角色
     *
     * @param userName 用户名称
     * @return 角色对象
     */
    SysRole queryRoleByUserName(String userName);

}
