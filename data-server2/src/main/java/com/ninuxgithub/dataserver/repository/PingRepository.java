package com.ninuxgithub.dataserver.repository;

import com.ninuxgithub.dataserver.model.Ping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PingRepository extends JpaRepository<Ping, String> {
}
