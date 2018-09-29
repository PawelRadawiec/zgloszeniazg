package com.info.repository;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("teamleaderRepository")
public interface TeamLeaderRepository extends JpaRepository<TeamLeader, Long> {
    TeamLeader findByEmail(String email);

    @Modifying
    @Query(value = "update team_member  SET fiest_name=?, last_name=?, city=?, street=? , phone_number=?, team_leader_phone=?, meal_category=? where id=?", nativeQuery = true)
    void editTeamLeader(@Param("firstName") String firstName,
                  @Param("lastName") String lastName,
                  @Param("teamName") String teamName,
                  @Param("phonenumber") String phonenumber,
                  @Param("troops") String troops,
                  @Param("id") Long id);

}
