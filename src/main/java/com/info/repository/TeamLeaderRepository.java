package com.info.repository;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("teamleaderRepository")
public interface TeamLeaderRepository extends JpaRepository<TeamLeader, Long> {
    TeamLeader findByEmail(String email);
}
