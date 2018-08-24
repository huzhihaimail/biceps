

package cn.com.njdhy.muscle.biceps.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <一句话功能简述> 树控件实体类
 * <功能详细描述>
 *
 * @author 胡贤
 * @version V0.0.1-SNAPSHOT
 */
@Getter
@Setter
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

}
