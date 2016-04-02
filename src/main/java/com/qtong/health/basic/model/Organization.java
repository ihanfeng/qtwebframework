package com.qtong.health.basic.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 部门表
 */
@Entity
@Table(name = "t_org")
@Cacheable
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

    private Set<Organization> subOrgs;

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
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

    @Column(name = "dept_level")
    public int getDeptlevel() {
        return deptlevel;
    }

    public void setDeptlevel(int deptlevel) {
        this.deptlevel = deptlevel;
    }

    @ManyToOne(targetEntity = Organization.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_id")
    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    @ManyToMany(targetEntity = User.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "t_dept_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "org_id"))
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @OneToMany(targetEntity = Organization.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parent_id")
    public Set<Organization> getSubOrgs() {
        return subOrgs;
    }

    public void setSubOrgs(Set<Organization> subOrgs) {
        this.subOrgs = subOrgs;
    }
}
