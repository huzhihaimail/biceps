package cn.com.njdhy.muscle.biceps.dao;

import cn.com.njdhy.muscle.biceps.model.SysConfig;

import java.util.List;


/**
 * @description: 参数管理dao层接口
 * @author rain
 */
public interface SysConfigDao extends BaseDao<SysConfig> {

    /**
     * 根据configId 查询是否有父级ID
     * @param configId
     * @return
     */
    SysConfig selectById(Integer configId);

    /**
     * 查询所有父级ID
     * @return
     */
    List<SysConfig> selectByParentId();


    /**
     * 根据configId作逻辑删除
     * @param configId
     */
    void updateById(Integer configId);

    /**selectByParentId
     * 查询所有的参数
     * @return
     */
    List<SysConfig> selectAllConfig();

    /**
     * 保存
     * @param sysConfig
     * @return
     */
    int saveConfig(SysConfig sysConfig);
}
