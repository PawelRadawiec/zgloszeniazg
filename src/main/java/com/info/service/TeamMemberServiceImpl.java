package com.info.service;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.repository.TeamLeaderRepository;
import com.info.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        teamMember.setTeamLeaderEmail(teamLeader.getEmail());
        teamMember.setTeamName(teamLeader.getTeamName());
        memberRepository.save(teamMember);
    }

    @Override
    public List<TeamMember> getAllMembers(String teamLeaderEmail) {
        List<TeamMember> memberList = memberRepository.getAllMembers(teamLeaderEmail);
        return memberList;
    }

    @Override
    public TeamMember findById(int id) {
        TeamMember teamMemberById = memberRepository.findById(id);
        return teamMemberById;
    }

    @Override
    public void editTeamMember(TeamMember teamMember, int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TeamLeader teamLeader = teamLeaderRepository.findByEmail(authentication.getName());
        memberRepository.editUser(teamMember.getFirstName(), teamMember.getLastName(), teamMember.getHomeCity(),
                teamMember.getStreet(), teamMember.getPhoneNumber(), teamMember.getTeamLeaderPhone(), teamMember.getMealCategory(),
                teamMember.getId());

        //memberRepository.save(teamMember);
    }

    @Override
    public void deleteById(int id) {
        memberRepository.deleteById(id);
    }

    @Override
    public List<TeamMember> getAll() {
      return memberRepository.getALl();
    }
}
