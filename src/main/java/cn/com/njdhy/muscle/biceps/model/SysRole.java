
package cn.com.njdhy.muscle.biceps.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <类功能简述> 角色实体
 *
 * @author 胡志海
 */
@Getter
@Setter
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

}
