package com.info.repository;

import com.info.model.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminTeamLeaderRepository extends JpaRepository<TeamLeader, Long> {

    @Query(value = "SELECT * FROM TEAM_LEADER WHERE role = 'TEAM_LEADER'", nativeQuery = true)
    List<TeamLeader> getAll();

    @Query(value = "SELECT * FROM TEAM_LEADER WHERE id=?", nativeQuery = true)
    TeamLeader getTeamLeaderById(int id);


}
