package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 权限表
 */
@Entity
@Table(name = "t_permission")
public class Permission {

    public String permId;

    private String name;

    private String expression;

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
}
