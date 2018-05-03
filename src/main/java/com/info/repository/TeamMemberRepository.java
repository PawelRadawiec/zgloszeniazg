package com.info.repository;


import com.info.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("teamMemmberRepo")
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

}
