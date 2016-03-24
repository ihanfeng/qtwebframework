package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ZML on 2015/8/6.
 */


@Entity
@Table(name = "t_dictionary")
public class Dictionary {

    private String dictId;

    private String expression;

    private String description;

    private DictType type;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
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

    @ManyToOne(targetEntity = DictType.class)
    @JoinColumn(name = "type")
    public DictType getType() {
        return type;
    }

    public void setType(DictType type) {
        this.type = type;
    }
}
