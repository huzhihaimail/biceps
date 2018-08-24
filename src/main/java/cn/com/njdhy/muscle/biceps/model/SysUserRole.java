
package cn.com.njdhy.muscle.biceps.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <类功能简述> 用户角色关联实体
 *
 * @author 胡志海
 */
@Getter
@Setter
public class SysUserRole extends BaseModel {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
                "userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
