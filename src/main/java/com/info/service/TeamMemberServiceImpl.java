package com.info.service;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teamMemberService")
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberRepository memberRepository;


    @Override
    public void save(TeamMember teamMember) {
        memberRepository.save(teamMember);
    }
}
