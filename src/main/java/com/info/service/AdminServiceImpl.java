package com.info.service;

import com.info.model.Admin;
import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.repository.AdminRepository;
import com.info.repository.AdminTeamLeaderRepository;
import com.info.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminTeamLeaderRepository adminTeamLeaderRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<TeamLeader> getAllTeamLeader() {
        return adminTeamLeaderRepository.getAll();
    }

    @Override
    public List<TeamMember> getAllTeamMember() {
        return teamMemberRepository.getALl();
    }

    public Admin getAdminFromSession(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminRepository.findAdminByEmail(authentication.getName());
        return admin;
    }
}
