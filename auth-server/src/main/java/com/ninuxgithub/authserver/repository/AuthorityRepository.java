package com.ninuxgithub.authserver.repository;

import com.ninuxgithub.authserver.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
