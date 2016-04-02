package com.qtong.health.basic.model;

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

    private User user;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "info_id")
    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY, mappedBy = "userInfo",cascade = CascadeType.PERSIST)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AbstractUserInfo{" +
                "infoId='" + infoId + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
