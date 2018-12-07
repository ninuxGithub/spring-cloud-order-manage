package com.ninuxgithub.dataserver.repository;

import com.ninuxgithub.dataserver.model.Pong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PongRepository extends JpaRepository<Pong, String> {
}
