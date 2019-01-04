package cn.com.njdhy.muscle.biceps.service.sys;

import cn.com.njdhy.muscle.biceps.dao.SysConfigDao;
import cn.com.njdhy.muscle.biceps.model.SysConfig;
import cn.com.njdhy.muscle.biceps.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: rain
 * @date: 2018/12/29 11:06
 */
@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfigDao,SysConfig> implements SysConfigService{

    @Autowired
    private SysConfigDao sysConfigDao;

    /**
     * 根据configId逻辑删除
     */
    @Override
    public List<SysConfig> selectByParentId() {
        List<SysConfig> list = sysConfigDao.selectByParentId();
        return list;
    }

    @Override
    public void saveConfig(SysConfig sysConfig) {
        if (sysConfig.getParentId() == null) {
            sysConfig.setParentId(0);
        }
        sysConfigDao.saveConfig(sysConfig);
    }

    @Override
    public void delete(String id) {
        int count = sysConfigDao.delete(id);
        if(count !=1){
            throw new RuntimeException("删除失败！");
        }
    }

    @Override
    public List<Integer> queryByParentId(String id) {
        return sysConfigDao.queryByParentId(id);
    }
}
