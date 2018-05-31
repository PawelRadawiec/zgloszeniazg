package com.info.service;

import com.info.model.TeamLeader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<TeamLeader> getAllTeamLeader();
}
