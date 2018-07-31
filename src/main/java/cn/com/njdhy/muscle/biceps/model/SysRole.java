
package cn.com.njdhy.muscle.biceps.model;

import java.util.List;

/**
 * <类功能简述> 角色实体
 *
 * @author 胡志海
 */
public class SysRole extends BaseModel {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色的中文名称
     */
    private String nameCn;

    /**
     * 是否禁用角色
     */
    private String status;

    /**
     * 新增時用
     */
    private List<String> menuIdList;

    public List<String> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<String> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "name='" + name + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
