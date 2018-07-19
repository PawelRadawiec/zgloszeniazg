package com.info.service;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<TeamLeader> getAllTeamLeader();
    List<TeamMember> getAllTeamMember();
    Object getDetails(int id);
    List<TeamMember> getTeamMembersByLeader(String teamLeaderEmail);
    List<TeamLeader> searchByLastName(String lastName);
    TeamMember getById(int id);
    void editTeamMember(TeamMember teamMember, int id);

}
