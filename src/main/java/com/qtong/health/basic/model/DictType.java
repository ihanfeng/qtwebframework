package com.qtong.health.basic.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 字典类型
 */
@Entity
@Table(name = "t_dictType")
@Cacheable
public class DictType {
    //记录ID
    private String typeId;
    //上级类型
    private DictType parentType;
    //类型名称
    private String typeName;
    //描述
    private String description;

    private Set<Dictionary> dictionaries;

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
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

    @Column(name = "type_name")
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


    @OneToMany(targetEntity = Dictionary.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "type_id")
    public Set<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(Set<Dictionary> dictionaries) {
        this.dictionaries = dictionaries;
    }
}
