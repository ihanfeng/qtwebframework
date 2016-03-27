package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 用户详情表
 */

@Entity
@Table(name = "t_userinfo")
@Cacheable
public class UserInfo {
    //记录ID
    private String infoId;
    //昵称
    private String nickName;
    //头像
    private Attachment avartar;
    //联系方式
    private Set<Contacts> contacts;

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

    @OneToOne(targetEntity = Attachment.class)
    @JoinColumn(name = "avartar_Id")
    public Attachment getAvartar() {
        return avartar;
    }

    public void setAvartar(Attachment avartar) {
        this.avartar = avartar;
    }

    @OneToMany(targetEntity = Contacts.class)
    @JoinColumn(name = "info_id")
    public Set<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contacts> contacts) {
        this.contacts = contacts;
    }

}
