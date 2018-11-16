package com.ninuxgithub.authserver.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     * 用户id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String id;

    /**
     * 用户名称
     */
    @Column(name = "userName", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String userName;


    @Column(name = "password")
    @NotNull
    @Size(min = 4, max = 50)
    private String password;

    /**
     * 是否启用用户
     */
    @Column(name = "enable")
    @NotNull
    private Boolean enable;


    /**
     * 用户的邮箱
     */
    @Column(name = "email", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    /**
     * 密码重置的时间
     */
    @Column(name = "resetPasswordTime")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date resetPasswordTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "authId")})
    private List<Authority> authorities;

    public User() {
    }

    public User(String userName, String password, Boolean enable, String email, Date resetPasswordTime, List<Authority> authorities) {
        this.userName = userName;
        this.password = password;
        this.enable = enable;
        this.email = email;
        this.resetPasswordTime = resetPasswordTime;
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getResetPasswordTime() {
        return resetPasswordTime;
    }

    public void setResetPasswordTime(Date resetPasswordTime) {
        this.resetPasswordTime = resetPasswordTime;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
