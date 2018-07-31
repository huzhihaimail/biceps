
package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.model.SysUser;
import cn.com.njdhy.muscle.biceps.service.BaseService;

/**
 * <类功能简述> 用户管理业务处理接口
 *
 * @author 胡志海
 * @date 2018-07-25
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 保存用户和角色信息到数据库
     *
     * @param sysUser 用户信息（包含角色信息）
     * @return 无
     */
    void saveUser(SysUser sysUser);

    /**
     * 查询用户信息
     *
     * @param sysUser 查询参数
     * @return 用户信息
     */
    SysUser queryUserInfo(SysUser sysUser);

}
