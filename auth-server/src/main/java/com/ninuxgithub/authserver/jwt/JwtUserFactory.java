package com.ninuxgithub.authserver.jwt;

import com.ninuxgithub.authserver.model.Authority;
import com.ninuxgithub.authserver.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }


    public static JwtUser create(User user) {
        return new JwtUser(user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getEnable(),
                user.getResetPasswordTime(),
                map2GrantedAuthorities(user.getAuthorities()));
    }

    private static List<GrantedAuthority> map2GrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());

    }
}
