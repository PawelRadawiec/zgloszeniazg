package com.info.repository;

import com.info.model.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("teamleaderRepository")
public interface TeamLeaderRepository extends JpaRepository<TeamLeader, Long> {
    TeamLeader findByEmail(String email);
}
