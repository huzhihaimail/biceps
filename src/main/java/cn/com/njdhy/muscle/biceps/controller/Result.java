package cn.com.njdhy.muscle.biceps.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <类功能简述> 返回结果封装类
 *
 * @author 胡志海
 */
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", 0);
    }

    public static Result error() {
        return error("500", "未知异常，请联系管理员");
    }

    /**
     * 处理根异常
     *
     * @param message 消息
     * @return 结果对象
     */
    public static Result error(String message) {
        return error("500", message);
    }

    public static Result error(String code, String message) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", message);
        return r;
    }

    public static Result success(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result success(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result success(Long total, List list) {
        Result r = new Result();
        r.put("total", total);
        r.put("rows", list);
        return r;
    }

    public static Result success() {
        return new Result();
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
