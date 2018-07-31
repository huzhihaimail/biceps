package cn.com.njdhy.muscle.biceps.controller;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * <类功能简述> 查询参数封装类
 *
 * @author 胡志海
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页条数
     */
    private int pageSize;

    public Query(Map<String, Object> params) {
        if (params != null && params.size() != 0) {
            this.putAll(params);
        }
    }


    public int getPage() {
        return pageNum;
    }

    public void setPage(int page) {
        this.pageNum = page;
    }

    public int getLimit() {
        return pageSize;
    }

    public void setLimit(int limit) {
        this.pageSize = limit;
    }
}
