
package cn.com.njdhy.muscle.biceps.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <类功能简述> 系统菜单实体
 *
 * @author 胡志海
 */
@Data
public class SysMenu extends BaseModel {

    /**
     * 父级菜单ID
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String parentName;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单跳转页面
     */
    private String url;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单排序
     */
    private String orderNum;

    /**
     * 权限标志
     */
    private String permission;

}
