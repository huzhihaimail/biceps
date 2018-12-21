
package cn.com.njdhy.muscle.biceps.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <类功能简述>
 *
 * @author 胡志海
 */
@Setter
@Getter
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserVerifyCode() {
        return userVerifyCode;
    }

    public void setUserVerifyCode(String userVerifyCode) {
        this.userVerifyCode = userVerifyCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getIsRememberMe() {
        return isRememberMe;
    }

    public void setIsRememberMe(String isRememberMe) {
        this.isRememberMe = isRememberMe;
    }
}
