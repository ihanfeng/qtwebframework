package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 部门表
 */
@Entity
@Table(name = "t_org")
public class Organization {
    //id
    private String orgId;
    //部门名称
    private String deptName;
    //部门路径
    private String deptPath;
    //部门级别
    private int deptlevel;
    //上级部门
    private Organization parent;
    //部门直接用户
    private Set<User> users;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "org_id")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptPath() {
        return deptPath;
    }

    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }

    @Column(name = "depn_level")
    public int getDeptlevel() {
        return deptlevel;
    }

    public void setDeptlevel(int deptlevel) {
        this.deptlevel = deptlevel;
    }

    @ManyToOne(targetEntity = Organization.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "t_dept_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "org_id"))
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
