
package cn.com.njdhy.muscle.biceps.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <类功能简述> 角色和菜單关联实体
 *
 * @author 胡志海
 */
@Getter
@Setter
public class SysRoleMenu {

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 数据行ID
     */
    private Integer id;
    /**
     * 父级菜单ID
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String name;

}
