
package cn.com.njdhy.muscle.biceps.model;

/**
 * <类功能简述> 系统菜单实体
 *
 * @author 胡志海
 */
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", icon='" + icon + '\'' +
                ", orderNum='" + orderNum + '\'' +
                '}';
    }
}
