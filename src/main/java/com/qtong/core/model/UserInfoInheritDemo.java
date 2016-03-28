package com.qtong.core.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 表继承关系的示例，所有用户信息表要继承AbstractUserInfo
 */

@Entity
@Table(name = "demo_userinfo_inherit")
public class UserInfoInheritDemo extends AbstractUserInfo {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfoInheritDemo{" +
                "age=" + age +
                "} " + super.toString();
    }
}
