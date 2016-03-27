package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 权限表
 */
@Entity
@Table(name = "t_permission")
@Cacheable
public class Permission {

    public String permId;

    private String name;

    private String expression;

    private Set<Menu> menus;

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "perm_id")
    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Column(name = "perm_name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToMany(targetEntity = Menu.class)
    @JoinTable(name = "t_perm_menu",joinColumns = @JoinColumn(name = "perm_id"),inverseJoinColumns = @JoinColumn(name = "menu_id"))
    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
