
package cn.com.njdhy.muscle.biceps.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <类功能简述>
 *
 * @author 胡志海
 */
@Getter
@Setter
public class SysUser extends BaseModel {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 修改密码
     */

    private String newPassword;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 验证码
     */
    private String userVerifyCode;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private int status;

    /**
     * 角色列表
     */
    private List<String> userRoles;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 记住我
     */
    private String isRememberMe;

}
