package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户详情表
 */
@Entity
@Table(name = "t_userinfo_basic")
@Inheritance(strategy = InheritanceType.JOINED)
@Cacheable
public abstract class AbstractUserInfo {
    //记录ID
    private String infoId;
    //昵称
    private String nickName;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "info_id")
    public String getDetailId() {
        return infoId;
    }

    public void setDetailId(String detailId) {
        this.infoId = detailId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "AbstractUserInfo{" +
                "infoId='" + infoId + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
