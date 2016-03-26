package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 用户详情表
 */

@Entity
@Table(name = "t_userinfo")
public class UserInfo {

    private String infoId;

    private String nickName;

    private Attachment avartar;

    private Set<Contacts> contacts;

    private User user;

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
    @JoinColumn(name = "avartarId")
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

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
