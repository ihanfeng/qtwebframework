package com.qtong.health.basic.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 地区表
 */

@Entity
@Table(name = "t_district")
@Cacheable
public class District {

    public String distId;//id

    public String distName;//地区名称

    public District parentDist;//上级地区

    public String postcode;//邮政编码

    public String areaCode;//电话区号

    public Set<District> subDistricts;/*下级地区*/

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "dist_id")
    public String getDistId() {
        return distId;
    }

    public void setDistId(String distId) {
        this.distId = distId;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    @ManyToOne(targetEntity = District.class)
    @JoinColumn(name = "parentId")
    public District getParentDist() {
        return parentDist;
    }

    public void setParentDist(District parentDist) {
        this.parentDist = parentDist;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @OneToMany(targetEntity = District.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    public Set<District> getSubDistricts() {
        return subDistricts;
    }

    public void setSubDistricts(Set<District> subDistricts) {
        this.subDistricts = subDistricts;
    }
}
