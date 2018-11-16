package com.ninuxgithub.authserver.repository;

import com.ninuxgithub.authserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUserName(String userName);
}
