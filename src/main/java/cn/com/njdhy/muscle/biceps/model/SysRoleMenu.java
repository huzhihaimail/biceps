
package cn.com.njdhy.muscle.biceps.model;

/**
 * <类功能简述> 角色和菜單关联实体
 *
 * @author 胡志海
 */
public class SysRoleMenu {

    private String roleId;

    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
                "roleId='" + roleId + '\'' +
                ", privilegeId='" + menuId + '\'' +
                '}';
    }
}
