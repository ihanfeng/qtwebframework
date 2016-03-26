package com.qtong.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 用户账户表
 */
@Entity
@Table(name = "t_user")
@Cacheable(true)
public class User {

    //用户ID，不是用int型而使用uuid是为了防止被人遍历
    private String userId;
    //用户名称
    private String username;
    //用户email，在此处的email和contacts里面的email没有直接关系，属于登录选项的一部分
    private String email;
    //用户的手机号，同上
    private String phone;
    //该用户是否是活跃用户
    private boolean actived = true;
    //该用户是否允许登录
    private boolean enable = true;
    //该用户是否过期
    private boolean expired = false;
    //该账户创建时间
    private Date createTime = new Date();
    //该账户最后被修改时间
    private Date lastModified = new Date();

    //密码，在向前台写这个对象的时候，隐藏掉密码
    @JsonIgnore
    private String password;

    //加密用的盐值，在向前台写这个对象的时候，隐藏掉
    @JsonIgnore
    private String salt;

    //用户的角色集合
    @JsonIgnore
    private Set<Role> roles;

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "email", unique = true, nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone", unique = true, nullable = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", actived=" + actived +
                ", enable=" + enable +
                ", expired=" + expired +
                ", createTime=" + createTime +
                ", lastModified=" + lastModified +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", roles=" + roles +
                '}';
    }
}
