/*
 * 公司名称：江苏华招网
 * 版权信息：江苏华招网 版权所有
 * 描述：非空判断工具类
 * 文件名称：IsEmptyUtils.java
 * 修改时间：2015-6-2
 * 修改人：HuZhihai
 * 修改内容：
 * 跟踪单号：
 * 修改单号 ：
 */

package cn.com.njdhy.muscle.biceps.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <一句话功能简述> 非空判断工具类
 * <功能详细描述>
 *
 * @author HuZhihai
 * @version V1.0[产品/模块版本]
 */
public class EmptyUtils implements Serializable {


    private static final long serialVersionUID = 2592027324178294984L;


    /**
     * 函数功能描述：判断字符串是否为null(空)
     *
     * @param input 待判断的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String input) {
        return input == null || input.length() == 0 || input.equals("") || input.equals("null");
    }

    /**
     * 函数功能描述：判断Integer是否为null(空)
     *
     * @param input 待判断的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(Integer input) {
        return input == null;
    }

    /**
     * 函数功能描述：判断Float是否为null(空)
     *
     * @param input 待判断的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(Float input) {
        return input == null;
    }

    /**
     * 函数功能描述：判断Byte是否为null(空)
     *
     * @param input 待判断的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(Byte input) {
        return input == null;
    }

    /**
     * 函数功能描述：判断Short是否为null(空)
     *
     * @param input 待判断的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(Short input) {
        return input == null;
    }

    /**
     * 函数功能描述：判断Character是否为null(空)
     *
     * @param input 待判断的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(Character input) {
        return input == null;
    }


    /**
     * 函数功能描述：判断Double是否为null(空)
     *
     * @param input 待判断的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(Double input) {
        return input == null;
    }

    /**
     * 功能描述: <br>
     * 判断一个类是否为空
     *
     * @param input 类
     * @return 是否为空
     */
    public static boolean isEmpty(Object input) {
        return input == null;
    }

    /**
     * 函数功能描述：判断一个集合是否为空
     *
     * @param input 待判断的集合
     * @return 是否为空
     */
    public static boolean isEmpty(List<?> input) {
        return input == null || input.size() <= 0 || input.isEmpty();
    }

    /**
     * 函数功能描述：判断Map<String, Object>是否为空
     *
     * @param input 待判断的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(Map<String, Object> input) {
        return input == null || input.isEmpty();
    }

    /**
     * 函数功能描述：判断Integer类型数组是否为空
     *
     * @param input 待判断的Integer类型的数组
     * @return 是否为空
     */
    public static boolean isEmpty(Integer[] input) {
        return input == null || input.length <= 0;
    }

    /**
     * 功能描述: <br>判断数组是否为空
     *
     * @param input 字符串数组
     * @return 是否为空
     */
    public static boolean isEmpty(String[] input) {
        return null == input || input.length == 0;
    }

    /**
     * 功能描述: <br>判断Flat数组是否为空
     *
     * @param input 字符串数组
     * @return 是否为空
     */
    public static boolean isEmpty(Float[] input) {
        return null == input || input.length == 0;
    }

    /**
     * 功能描述: <br>判断Double数组是否为空
     *
     * @param input 字符串数组
     * @return 是否为空
     */
    public static boolean isEmpty(Double[] input) {
        return null == input || input.length == 0;
    }

}
