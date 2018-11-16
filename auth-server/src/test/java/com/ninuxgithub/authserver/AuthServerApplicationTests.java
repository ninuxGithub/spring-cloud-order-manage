package com.ninuxgithub.authserver;

import com.ninuxgithub.authserver.model.Authority;
import com.ninuxgithub.authserver.model.AuthorityName;
import com.ninuxgithub.authserver.model.User;
import com.ninuxgithub.authserver.repository.AuthorityRepository;
import com.ninuxgithub.authserver.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerApplication.class)
public class AuthServerApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void contextLoads() {
        /*createAdmin();
        createUser();*/

    }

    public void createAdmin() {
        String password = passwordEncoder.encode("admin");
        System.out.println(password);
        Authority authority = new Authority(AuthorityName.ROLE_ADMIN, null);
        User admin = new User("admin", password, true, "admin@qq.com", new Date(), Arrays.asList(authority));
        authority.setUsers(Arrays.asList(admin));
        authorityRepository.save(authority);
        userRepository.save(admin);
    }

    public void createUser() {
        String password = passwordEncoder.encode("user");
        System.out.println(password);
        Authority authority = new Authority(AuthorityName.ROLE_USER, null);
        User user = new User("user", password, true, "user@qq.com", new Date(), Arrays.asList(authority));
        authority.setUsers(Arrays.asList(user));
        authorityRepository.save(authority);
        userRepository.save(user);
    }

}
