
package cn.com.njdhy.muscle.biceps.model;

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
    private Integer userId;

    /**
     * 角色ID
     */
    private String roleId;


}
