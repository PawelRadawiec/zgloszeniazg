package com.info.service;

import com.info.model.TeamLeader;
import org.springframework.stereotype.Service;

@Service
public interface TeamLeaderService {
    TeamLeader findTeamLeaderByEmail(String email);
    void save(TeamLeader teamLeader);
    TeamLeader findByEmail(String email);
    String helloTeamLeader();
}
