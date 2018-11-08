package com.ninuxgithub.authserver.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;


/**
 * 实现UserDetails
 */
public class JwtUser implements UserDetails {


    private static final long serialVersionUID = -4146020095727421200L;

    private String id;

    private String userName;

    private String password;

    private String email;

    private Boolean enable;

    private Date resetPasswordTime;

    public JwtUser(String id, String userName, String password, String email, Boolean enable, Date resetPasswordTime, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.enable = enable;
        this.resetPasswordTime = resetPasswordTime;
        this.authorities = authorities;
    }

    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public Date getResetPasswordTime() {
        return resetPasswordTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
