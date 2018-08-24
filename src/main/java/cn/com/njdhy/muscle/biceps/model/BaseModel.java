
package cn.com.njdhy.muscle.biceps.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <类功能简述> 通用数据表字段
 *
 * @author 胡志海
 */
@Getter
@Setter
public class BaseModel implements Serializable {

    /**
     * 数据行ID
     */
    private Integer id;

    /**
     * 逻辑删除标志
     */
    private String deleteFlag;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date updateDate;

}
