package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ZML on 2015/8/7.
 * 字典类型
 */
@Entity
@Table(name = "t_dictType")
public class DictType {

    private String typeId;

    private DictType parentType;

    private String typeName;

    private String description;


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "type_id")
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @ManyToOne(targetEntity = DictType.class)
    @JoinColumn(name = "parentType")
    public DictType getParentType() {
        return parentType;
    }


    public void setParentType(DictType parentType) {
        this.parentType = parentType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
