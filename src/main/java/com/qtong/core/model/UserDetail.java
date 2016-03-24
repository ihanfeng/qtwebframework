package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ZML on 2015/8/7.
 */

@Entity
@Table(name = "t_userDetail")
public class UserDetail {

    private String detailId;

    private String nickName;

    private Attachment avartar;

    private Set<Contacts> contacts;

    private User user;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "detail_id")
    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
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
    @JoinColumn(name = "detail_id")
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
