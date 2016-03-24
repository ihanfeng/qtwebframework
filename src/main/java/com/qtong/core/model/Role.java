package com.qtong.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ZML on 2015/8/6.
 */
@Entity
@Table(name = "t_role")
@Cacheable(value = true)
public class Role {

    private String roleId;

    private String roleName;

    @JsonIgnore
    private List<Access> accesses;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(targetEntity = Access.class)
    @JoinTable(name = "t_role_access", joinColumns = @JoinColumn(name = "access_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<Access> getAccesses() {
        return accesses;
    }

    public void setAccesses(List<Access> accesses) {
        this.accesses = accesses;
    }
}
