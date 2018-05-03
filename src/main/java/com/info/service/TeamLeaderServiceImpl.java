package com.info.service;

import com.info.model.TeamLeader;
import com.info.repository.TeamLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teamLeaderService")
public class TeamLeaderServiceImpl implements TeamLeaderService {

    @Autowired
    private TeamLeaderRepository teamLeaderRepository;

    @Override
    public TeamLeader findTeamLeaderByEmail(String email) {
        return null;
    }

    @Override
    public void save(TeamLeader teamLeader) {
        teamLeaderRepository.save(teamLeader);
    }
}
