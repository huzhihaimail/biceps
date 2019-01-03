package cn.com.njdhy.muscle.biceps.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 参数管理实体类
 * @author rain
 */
@Getter
@Setter
public class SysConfig {
    /**
     * 主键id
     */
    private Integer configId;
    /**
     * 父级id
     */
    private Integer parentId;
    /**
     * key
     */
    private String key;
    /**
     * value
     */
    private String value;
    /**
     * 状态：0隐藏 1显示
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}
