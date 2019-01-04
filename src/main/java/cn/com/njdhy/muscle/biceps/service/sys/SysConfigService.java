package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.model.SysConfig;
import cn.com.njdhy.muscle.biceps.service.BaseService;

import java.util.List;

/**
 * @description:
 *
 * @author: rain
 * @date: 2018/12/29 11:06
 */
public interface SysConfigService extends BaseService<SysConfig> {

    /**
     * 根据configId逻辑删除
     * @return
     */
    List<SysConfig> selectByParentId();

    /**
     * 保存
     * @param sysConfig
     * @return
     */
    void saveConfig(SysConfig sysConfig);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 根据父级id查询id集合
     * @param id
     * @return
     */
    List<Integer> queryByParentId(String id);
}
