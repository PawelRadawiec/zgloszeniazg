package com.info.service;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.repository.AdminRepository;
import com.info.repository.AdminTeamLeaderRepository;
import com.info.repository.AdminTeamMemberRepository;
import com.info.repository.TeamMemberRepository;
import com.info.service.impl.AdminServiceImpl;
import com.info.service.impl.TeamMemberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @Mock
    AdminTeamLeaderRepository adminTeamLeaderRepository;

    @Mock
    TeamMemberRepository teamMemberRepository;

    @Mock
    AdminRepository adminRepository;

    @Mock
    AdminTeamMemberRepository adminTeamMemberRepository;

    @Mock
    TeamMemberServiceImpl teamMemberService;

    @InjectMocks
    AdminServiceImpl adminService;

    @Spy
    List<TeamMember> teamMembers = new ArrayList();

    @Before
    public void setUp(){
        teamMembers = setUpTeamMembers();
    }

    @Test
    public void getAllTeamLeader() {
        List<TeamLeader> teamLeaderList = new ArrayList();
        when(adminTeamLeaderRepository.getAll()).thenReturn(teamLeaderList);
        assertEquals(adminService.getAllTeamLeader(), teamLeaderList);
    }

    @Test
    public void getAllTeamMember(){
        List<TeamMember> teamMemberList = new ArrayList();
        when(teamMemberRepository.getALl()).thenReturn(teamMemberList);
        assertEquals(adminService.getAllTeamMember(), teamMemberList);
    }

    @Test
    public void getDetails(){
        TeamLeader teamLeader = new TeamLeader();
        when(adminTeamLeaderRepository.getTeamLeaderById(anyInt())).thenReturn(teamLeader);
        assertEquals(adminService.getDetails(anyInt()), teamLeader);
    }

    @Test
    public void getTeamMembersByLeader(){
        when(adminTeamMemberRepository.getTeamMembersByLeader("test1")).thenReturn(teamMembers);
        assertEquals(teamMembers, adminService.getTeamMembersByLeader("test1"));
    }


    @Test
    public void searchByLastName(){

    }

    private List<TeamMember> setUpTeamMembers(){
        List<TeamMember> members = new ArrayList();
        TeamMember teamMember1 = new TeamMember();
        teamMember1.setTeamLeaderEmail("test1");

        TeamMember teamMember2 = new TeamMember();
        teamMember1.setTeamLeaderEmail("test2");
        members.add(teamMember1);
        members.add(teamMember2);

        return members;
    }
}
