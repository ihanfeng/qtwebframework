package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 联系人表
 * 存放用户的联系方式
 */
@Entity
@Table(name = "t_contacts")
public class Contacts {
    //id
    private String contactId;
    //是否是默认的
    private boolean defaults;
    //联系人说在的地区
    private District area;
    //街道名称
    private String street;
    //具体地址
    private String address;
    //公司名称
    private String company;
    //手机号
    private String mobileNumber;
    //邮政编码
    private String postcode;
    //QQ号码
    private String qq;
    //邮箱
    private String email;
    //微博帐号
    private String microblog;
    //个人主页
    private String homepage;

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "contact_id")
    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @ManyToOne(targetEntity = District.class)
    @JoinColumn(name = "areaId")
    public District getArea() {
        return area;
    }

    public void setArea(District area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMicroblog() {
        return microblog;
    }

    public void setMicroblog(String microblog) {
        this.microblog = microblog;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public boolean isDefaults() {
        return defaults;
    }

    public void setDefaults(boolean defaults) {
        this.defaults = defaults;
    }

}
