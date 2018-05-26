package com.info.repository;


import com.info.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("teamMemmberRepo")
public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {

    @Query(value = "select * from team_member WHERE team_leader_email=?", nativeQuery = true)
    List<TeamMember> getAllMembers(@Param("team_leader_email") String teamLeaderEmail);

    TeamMember findById(int id);

    @Modifying
    @Query(value = "update team_member  SET fiest_name=?, last_name=?, city=?, street=? , phone_number=?, team_leader_phone=?, meal_category=? where id=?", nativeQuery = true)
    void editUser(@Param("firstName") String firstName,
                  @Param("lastName") String lastName,
                  @Param("homeCity") String homeCity,
                  @Param("street") String street,
                  @Param("phoneNumber") String phoneNumber,
                  @Param("teamLeaderPhone") String teamLeaderPhone,
                  @Param("mealCategory") String mealCategory ,
                  @Param("id") int id);

    void deleteById(int id);

}
