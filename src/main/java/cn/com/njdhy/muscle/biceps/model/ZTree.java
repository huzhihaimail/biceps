

package cn.com.njdhy.muscle.biceps.model;

import java.io.Serializable;

/**
 * <一句话功能简述> 树控件实体类
 * <功能详细描述>
 *
 * @author 胡贤
 * @version V0.0.1-SNAPSHOT
 */
public class ZTree implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 父级ID
     */
    private String parentId;

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

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
