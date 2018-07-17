package com.info.repository;

import com.info.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("adminTeamMemberRepo")
public interface AdminTeamMemberRepository extends JpaRepository<TeamMember, Long> {

    @Query(value = "SELECT * FROM TEAM_MEMBER WHERE team_leader_email=?", nativeQuery = true)
    List<TeamMember> getTeamMembersByLeader(@Param("team_leader_email") String teamLeaderEmail);

    @Query(value = "SELECT * FROM TEAM_MEMBER", nativeQuery = true)
    List<TeamMember> getAll();

    @Query(value = "SELECT * FROM TEAM_MEMBER WHERE id=?", nativeQuery = true)
    TeamMember getById(@Param("id") int id);
}
