package com.info.repository;


import com.info.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("teamMemmberRepo")
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    @Query(value = "select * from team_member", nativeQuery = true)
    List<TeamMember> getAllMembers();
}
