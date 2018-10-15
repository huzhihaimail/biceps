
package cn.com.njdhy.muscle.biceps.dao;

import cn.com.njdhy.muscle.biceps.model.SysQuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定时任务数据访问层接口
 *
 * @author 胡志海
 */
public interface SysQuartzJobDao extends BaseDao<SysQuartzJob> {


    List<SysQuartzJob> queryAllList();

}
