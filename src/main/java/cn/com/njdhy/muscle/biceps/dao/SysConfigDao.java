package cn.com.njdhy.muscle.biceps.dao;

import cn.com.njdhy.muscle.biceps.model.SysConfig;

import java.util.List;


/**
 * @description: 参数管理dao层接口
 * @author rain
 */
public interface SysConfigDao extends BaseDao<SysConfig> {


    /**
     * 查询所有父级ID
     * @return
     */
    List<SysConfig> selectByParentId();


    /**
     * 保存
     * @param sysConfig
     * @return
     */
    int saveConfig(SysConfig sysConfig);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 根据父级id查询id集合
     * @param id
     * @return
     */
    List<Integer> queryByParentId(String id);
}
