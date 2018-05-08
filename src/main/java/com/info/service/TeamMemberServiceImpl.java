package com.info.service;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.repository.TeamLeaderRepository;
import com.info.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("teamMemberService")
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberRepository memberRepository;

    @Autowired
    private TeamLeaderRepository teamLeaderRepository;


    @Override
    public void save(TeamMember teamMember) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        TeamLeader teamLeader = teamLeaderRepository.findByEmail(auth.getName());
        teamMember.setLeaderName( teamLeader.getFirstName() + " " +  teamLeader.getLastName());
        teamMember.setTeamName(teamLeader.getTeamName());
        memberRepository.save(teamMember);
    }
}
