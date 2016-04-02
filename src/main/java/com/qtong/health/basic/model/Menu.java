package com.qtong.health.basic.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 用户菜单表
 */
@Entity
@Table(name = "t_menu")
@Cacheable
public class Menu {
    //记录ID
    private String menuId;
    //菜单名称
    private String menuName;
    //菜单类型，如连接地址，下拉框等
    private String type;
    //连接地址和执行的js方法
    private String href;
    //是否只最下级菜单
    private boolean isLeaf;
    //菜单级别
    private int level;
    //上级菜单
    private Menu parent;
    //CSS 样式名称
    private String className;
    //CSS 样式
    private String style;

    private Set<Permission> permissions;

    private Set<Menu> subMenus;

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "menu_id")
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }


    @ManyToOne(targetEntity = Menu.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @ManyToMany(targetEntity = Permission.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "menus")
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @OneToMany(targetEntity = Menu.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "parent")
    public Set<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(Set<Menu> subMenus) {
        this.subMenus = subMenus;
    }
}
