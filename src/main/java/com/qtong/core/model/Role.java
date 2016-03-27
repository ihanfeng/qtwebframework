package com.qtong.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色表
 */
@Entity
@Table(name = "t_role")
@Cacheable(value = true)
public class Role {
    //记录ID
    private String roleId;
    //角色名称
    private String roleName;
    //该角色拥有的权限
    @JsonIgnore
    private List<Permission> permissions;


    private Set<Menu> menus = new HashSet<>();

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "role_name", unique = true, nullable = false)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(targetEntity = Permission.class, cascade = CascadeType.ALL)
    @JoinTable(name = "t_role_perm", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "perm_id"))
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Transient
    public Set<Menu> getMenus() {
        //该角色所拥有的菜单数为0
        if (this.menus.size() == 0) {
            //说明该角色没权限
            if (this.getPermissions() == null || this.getPermissions().size() == 0) {
                return menus;
            } else {
                for (Permission permission : this.getPermissions()) {
                    if (permission.getMenus() != null) {
                        menus.addAll(permission.getMenus());
                    }
                }
            }
        }
        return menus;
    }
}
