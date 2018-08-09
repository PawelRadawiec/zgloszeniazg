package com.info.controller;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.service.TeamLeaderServiceImpl;
import com.info.service.TeamMemberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TeamLeaderControllerTest {

    @Mock
    TeamLeaderServiceImpl teamLeaderService;

    @Mock
    TeamMemberServiceImpl teamMemberService;

    @InjectMocks
    TeamLeaderController teamLeaderController;

    @Mock
    ModelAndView modelAndView;

    @Spy
    List<TeamMember> teamMembers = new ArrayList();

    @Before
    public void setUp(){
        teamMembers = getTeamMembers();
    }



    private List<TeamMember> getTeamMembers(){
        List<TeamMember> teamMemberList = new ArrayList();
        TeamMember teamMember = new TeamMember();
        teamMember.setLeaderName("pawel@gmail.com");
        teamMemberList.add(teamMember);
        teamMemberList.add(teamMember);
        teamMemberList.add(teamMember);
        teamMemberList.add(teamMember);

        return teamMemberList;
    }

}
