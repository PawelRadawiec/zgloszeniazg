package com.info.service;

import com.info.model.Admin;
import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.repository.AdminRepository;
import com.info.repository.AdminTeamLeaderRepository;
import com.info.repository.AdminTeamMemberRepository;
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

    @Autowired
    private AdminTeamMemberRepository adminTeamMemberRepository;

    @Override
    public List<TeamLeader> getAllTeamLeader() {
        return adminTeamLeaderRepository.getAll();
    }

    @Override
    public List<TeamMember> getAllTeamMember() {
        return teamMemberRepository.getALl();
    }

    @Override
    public Object getDetails(int id) {
        TeamLeader teamLeader = adminTeamLeaderRepository.getTeamLeaderById(id);
        return teamLeader;
    }

    @Override
    public List<TeamMember> getTeamMembersByLeader(String teamLeaderEmail) {
        return adminTeamMemberRepository.getTeamMembersByLeader(teamLeaderEmail);
    }

    @Override
    public List<TeamLeader> searchByLastName(String lastName) {
        return adminTeamLeaderRepository.searchByLastName(lastName);
    }


    public Admin getAdminFromSession(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminRepository.findAdminByEmail(authentication.getName());
        return admin;
    }


}
