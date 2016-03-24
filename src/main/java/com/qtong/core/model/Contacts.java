package com.qtong.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ZML on 2015/8/7.
 */
@Entity
@Table(name = "t_contacts")
public class Contacts {

    private String contactId;

    private District area;

    private String street;

    private String address;

    private String company;

    private String mobileNumber;

    private String phone;

    private String postCode;

    private String qq;

    private String email;

    private String microblog;

    private String personSite;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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

    public String getPersonSite() {
        return personSite;
    }

    public void setPersonSite(String personSite) {
        this.personSite = personSite;
    }
}
