package com.info.service;

import com.info.model.Roles;
import com.info.model.TeamLeader;
import com.info.repository.TeamLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("teamLeaderService")
public class TeamLeaderServiceImpl implements TeamLeaderService {

    @Autowired
    private TeamLeaderRepository teamLeaderRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public TeamLeader findTeamLeaderByEmail(String email) {
        return null;
    }

    @Override
    public void save(TeamLeader teamLeader) {
        teamLeader.setRole(Roles.TEAM_LEADER.toString());
        teamLeader.setPassword(passwordEncoder.encode(teamLeader.getPassword()));
        teamLeaderRepository.save(teamLeader);
    }
}
