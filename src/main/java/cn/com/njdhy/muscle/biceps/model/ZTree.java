/*
 * 公司名称：江苏华招网信息技术有限公司
 * 版权信息：江苏华招网信息技术有限公司版权所有
 * 文件名称：Tree.java
 * 修改时间：2017-3-8
 * 修改人：胡贤
 * 修改内容：
 * 跟踪单号：
 * 修改单号 ：
 */

package cn.com.njdhy.muscle.biceps.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <一句话功能简述> 树控件实体类
 * <功能详细描述>
 * @author 胡贤
 * @version V0.0.1-SNAPSHOT
 */
@Getter
@Setter
public class ZTree {
    
    /**
     * 菜单ID
     */
    private int menuId;
    
    /**
     * 父级ID
     */
    private int parentId;
    
    /**
     * 菜单名称
     */
    private String name;
    
    /**
     * 是否打开
     */
    private boolean open;
    
    /**
     * 是否选中
     */
    private boolean checked;
}
