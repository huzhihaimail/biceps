
package cn.com.njdhy.muscle.biceps.dao;

import cn.com.njdhy.muscle.biceps.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色管理数据访问层接口
 *
 * @author 胡志海
 */
public interface SysUserRoleDao extends BaseDao<SysUserRole> {

    /**
     * 根据用户id
     * @param userId
     * @return
     */
    List<SysUserRole> queryUserRoleList(String userId);

    /**
     * 删除用户角色关联表信息通过用户id
     * @param userId
     */
    void deleteByUserId(String userId);
}
