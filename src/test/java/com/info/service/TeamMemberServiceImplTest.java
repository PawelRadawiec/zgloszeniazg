package com.info.service;


import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.repository.TeamLeaderRepository;
import com.info.repository.TeamMemberRepository;
import com.info.service.impl.TeamMemberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamMemberServiceImplTest {

    @InjectMocks
    TeamMemberServiceImpl teamMemberService;

    @Mock
    TeamMemberRepository teamMemberRepository;

    @Mock
    TeamLeaderRepository teamLeaderRepository;

    @Mock
    Authentication authentication;

    @Spy
    List<TeamMember> teamMembers = new ArrayList();

    @Spy
    TeamMember teamMember = new TeamMember();

    @Spy
    TeamLeader teamLeader = new TeamLeader();


    @Before
    public void setUp() {
        this.teamMembers = setUpTeamMembersList();
        this.teamMember = setTeamMember();
    }

    @Test
    public void getAll() {
        when(teamMemberRepository.getALl()).thenReturn(teamMembers);
        assertEquals(teamMemberService.getAll(), teamMembers);
    }

    @Test
    public void editById() {
        teamMemberService.editTeamMember(teamMember, 1);
        verify(teamMemberRepository, atLeastOnce()).editUser(teamMember.getFirstName(), teamMember.getLastName(),
                teamMember.getHomeCity(), teamMember.getStreet(), teamMember.getPhoneNumber(),
                teamMember.getTeamLeaderPhone(), teamMember.getMealCategory(), 1);
    }

    @Test
    public void findById() {
        when(teamMemberRepository.findById(1)).thenReturn(teamMember);
        assertEquals(teamMemberService.findById(1), teamMember);
    }

    @Test
    public void getAllMembers() {
        when(teamMemberRepository.getAllMembers(anyString())).thenReturn(teamMembers);
        assertEquals(teamMemberService.getAllMembers(""), teamMembers);
    }

    @Test
    public void deleteById() {
        teamMemberService.deleteById(1);
        verify(teamMemberRepository, atLeastOnce()).deleteById(1);
    }

    @Test
    public void save() {
//        teamLeader.setEmail("damian@gmail.com");
//        teamLeader.setFirstName("asd");
//        teamLeader.setLastName("asdad");
//        teamLeader.setTeamName("asda");
//
//        Authentication authentication = mock(Authentication.class);
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(teamLeader);
//        when(teamLeaderRepository.findByEmail("damian@gmail.com")).thenReturn(teamLeader);
//        teamMemberService.save(teamMember);
//        verify(teamMemberRepository, atLeastOnce()).save(teamMember);
    }

    private List<TeamMember> setUpTeamMembersList() {
        List<TeamMember> teamMemberList = new ArrayList();
        TeamMember teamMember = new TeamMember();
        teamMember.setLeaderName("test");
        teamMember.setId(1);
        teamMemberList.add(teamMember);
        teamMemberList.add(teamMember);
        teamMemberList.add(teamMember);
        teamMemberList.add(teamMember);

        return teamMemberList;
    }


    private TeamMember setTeamMember() {
        TeamMember teamMember = new TeamMember();
        teamMember.setId(1);
        teamMember.setLeaderName("test");
        return teamMember;
    }
}
