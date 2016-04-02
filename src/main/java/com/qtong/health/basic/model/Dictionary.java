package com.qtong.health.basic.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 字典表
 */


@Entity
@Table(name = "t_dictionary")
@Cacheable
public class Dictionary {
    //记录ID
    private String dictId;
    //记录表达式
    private String expression;
    //表述
    private String description;
    //字典类型
    private DictType type;

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "dict_id")
    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(targetEntity = DictType.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "type_id")
    public DictType getType() {
        return type;
    }

    public void setType(DictType type) {
        this.type = type;
    }
}
