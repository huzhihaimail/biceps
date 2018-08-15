
package cn.com.njdhy.muscle.biceps.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 通用业务层处理逻辑
 *
 * @author 胡志海
 */
public interface BaseService<T> {

    /**
     * 函数功能描述：插入操作示例
     *
     * @param t 示例实体对象
     * @return 影响行数
     */
    Integer insert(T t);

    /**
     * 函数功能描述：批量插入操作示例
     *
     * @param list 示例实体对象集合
     * @return 影响行数
     */
    Integer batchInsert(List<T> list);

    /**
     * 函数功能描述：修改操作示例
     *
     * @param t 示例实体对象
     * @return 需要行数
     */
    Integer update(T t);

    /**
     * 根据ID数组批量删除操作
     *
     * @param idsLst 列表
     * @return 影响行数
     */
    Integer deleteByIds(List<String> idsLst);

    /**
     * 查询所有object表记录
     *
     * @param map      查询参数
     * @param pageNum  页数
     * @param pageSize 每页大小
     * @return 指定页数据
     */
    PageInfo<T> queryList(Map<String, Object> map, Integer pageNum, Integer pageSize);

    /**
     * 函数功能描述：查询所有object表记录
     *
     * @param map 示例实体对象
     * @return 结果集合
     */
    List<T> queryByParams(Map<String, Object> map);

    /**
     * 函数功能描述：根据ID查询单个记录
     *
     * @param id 示例id
     * @return 示例对象
     */
    T queryById(String id);

    /**
     * 查询总记录数
     *
     * @param map 查询条件
     * @return 记录数量
     */
    Integer queryTotals(Map<String, Object> map);

    /**
     * 根据名称查询对象
     * 作用：检查名称是否存在
     *
     * @param name 名称
     * @return 查询结果对象
     */
    T queryByName(String name);
}
