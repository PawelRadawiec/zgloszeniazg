package com.info.service;

import com.info.model.Admin;
import com.info.model.SearchModel;
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

import java.util.ArrayList;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    private AdminTeamLeaderRepository adminTeamLeaderRepository;
    private TeamMemberRepository teamMemberRepository;
    private AdminRepository adminRepository;
    private AdminTeamMemberRepository adminTeamMemberRepository;
    private TeamMemberServiceImpl teamMemberService;


    @Autowired
    public AdminServiceImpl(AdminTeamLeaderRepository adminTeamLeaderRepository,
                            TeamMemberRepository teamMemberRepository, AdminRepository adminRepository,
                            AdminTeamMemberRepository adminTeamMemberRepository,
                            TeamMemberServiceImpl teamMemberService) {

        this.adminTeamLeaderRepository = adminTeamLeaderRepository;
        this.adminRepository = adminRepository;
        this.adminTeamMemberRepository = adminTeamMemberRepository;
        this.teamMemberService = teamMemberService;
        this.teamMemberRepository = teamMemberRepository;
    }

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
    public List<TeamLeader> searchByLastName(SearchModel searchModel) {
        if(searchModel.getLastName() != null){
            return adminTeamLeaderRepository.searchByLastName(searchModel.getLastName());
        }
          return new ArrayList<>();
    }

    @Override
    public TeamMember getById(int id) {
        return adminTeamMemberRepository.getById(id);
    }

    @Override
    public void editTeamMember(TeamMember teamMember, int id) {
        if(teamMember != null){
            this.teamMemberService.editTeamMember(teamMember, id);
        }
    }

    @Override
    public List<TeamMember> getMembersByData(String surname) {
        if(surname != null){
            return adminTeamMemberRepository.getMembersByData(surname);
        }
        return new ArrayList<>();
    }


    public Admin getAdminFromSession(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminRepository.findAdminByEmail(authentication.getName());
        return admin;
    }


}
