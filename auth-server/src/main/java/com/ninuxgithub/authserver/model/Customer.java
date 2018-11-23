package com.ninuxgithub.authserver.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 8366465396021871600L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String id;

    private String phoneNum;

    private String userName;

    private String password;

    @Transient
    private String repassword;


    public Customer() {

    }

    public Customer(String phoneNum, String userName, String password, String repassword) {
        this.phoneNum = phoneNum;
        this.userName = userName;
        this.password = password;
        this.repassword = repassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", repassword='" + repassword + '\'' +
                '}';
    }
}
