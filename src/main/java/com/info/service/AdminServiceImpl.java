package com.info.service;

import com.info.model.TeamLeader;
import com.info.repository.AdminTeamLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminTeamLeaderRepository adminTeamLeaderRepository;

    @Override
    public List<TeamLeader> getAllTeamLeader() {
        return adminTeamLeaderRepository.getAll();
    }
}
