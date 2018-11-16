package com.ninuxgithub.authserver;

import com.ninuxgithub.authserver.model.Authority;
import com.ninuxgithub.authserver.model.AuthorityName;
import com.ninuxgithub.authserver.model.User;
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

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void contextLoads() {
        String password = passwordEncoder.encode("admin");
        System.out.println(password);
        User admin = new User("admin", "admin", true, "admin@qq.com", new Date(), null);
        Authority authority = new Authority(AuthorityName.ROLE_ADMIN, Arrays.asList(admin));
        admin.setAuthorities(Arrays.asList(authority));
        userRepository.save(admin);

    }

}
