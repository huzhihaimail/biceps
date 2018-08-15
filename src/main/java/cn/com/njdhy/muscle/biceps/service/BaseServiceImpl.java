package cn.com.njdhy.muscle.biceps.service;

import cn.com.njdhy.muscle.biceps.dao.BaseDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 通用业务层处理实现类
 *
 * @author 胡志海
 */
public class BaseServiceImpl<DAO extends BaseDao<T>, T> implements BaseService<T> {

    @Autowired
    protected DAO dao;

    /**
     * 函数功能描述：插入操作示例
     *
     * @param t 示例实体对象
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Integer insert(T t) {
        return this.dao.insert(t);
    }

    /**
     * 函数功能描述：批量插入操作示例
     *
     * @param list 示例实体对象集合
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Integer batchInsert(List<T> list) {
        return this.dao.batchInsert(list);
    }

    /**
     * 函数功能描述：修改操作示例
     *
     * @param t 示例实体对象
     * @return 需要行数
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Integer update(T t) {
        return this.dao.update(t);
    }

    /**
     * 函数功能描述：根据ID数组批量删除操作
     *
     * @param idsLst id集合
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Integer deleteByIds(List<String> idsLst) {
        return this.dao.deleteByIds(idsLst);
    }

    /**
     * 函数功能描述：分页查询所有表记录
     *
     * @param queryParams 查询参数
     * @return 结果集合
     */
    @Override
    public PageInfo<T> queryList(Map<String, Object> queryParams, Integer pageNum, Integer pageSize) {
        // 开始分页(pageNum：表示第几页；pageSize表示每页查询多少条)
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<T>(this.dao.queryList(queryParams));
    }

    /**
     * 根据条件查询列表结果（不分页）
     *
     * @param map 示例参数对象
     * @return 查询结果列表
     */
    @Override
    public List<T> queryByParams(Map<String, Object> map) {
        return this.dao.queryByParams(map);
    }


    /**
     * 函数功能描述：根据ID查询单个记录
     *
     * @param id 示例id
     * @return 示例对象
     */
    @Override
    public T queryById(String id) {
        return this.dao.queryById(id);
    }

    /**
     * 根据名称查询对象
     * 作用：检查名称是否存在
     *
     * @param name 名称
     * @return 查询结果对象
     */
    @Override
    public T queryByName(String name) {
        return this.dao.queryByName(name);
    }


    /**
     * 函数功能描述：查询总记录数
     *
     * @param map 示例查询对象
     * @return 示例对象
     */
    @Override
    public Integer queryTotals(Map<String, Object> map) {
        return this.dao.queryTotals(map);
    }

}
