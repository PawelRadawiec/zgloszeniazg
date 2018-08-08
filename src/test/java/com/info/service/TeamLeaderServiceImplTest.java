package com.info.service;

import com.info.model.TeamLeader;
import com.info.repository.TeamLeaderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamLeaderServiceImplTest {


    @Mock
    TeamLeaderRepository teamLeaderRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    TeamLeaderServiceImpl teamLeaderService;

    @Spy
    TeamLeader teamLeader = new TeamLeader();


    @Test
    public void saveTeamLeaderTest() {
        teamLeader.setPassword(passwordEncoder.encode(teamLeader.getPassword()));
        teamLeaderService.save(teamLeader);
        verify(teamLeaderRepository, atLeastOnce()).save(teamLeader);
    }

    @Test
    public void findTeamLeaderByEmail() {
        TeamLeader leader = new TeamLeader();
        leader.setEmail("asdadasds@gmail.com");
        when(teamLeaderRepository.findByEmail(anyString())).thenReturn(leader);
        assertEquals(teamLeaderService.findByEmail(leader.getEmail()), leader);
    }



    @Test
    public void userDontExistTest() {
        assertFalse(teamLeaderService.userExist(teamLeader));
    }


    @Test
    public void userExistTest() {
        TeamLeader leader = new TeamLeader();
        leader.setEmail("asdadasds@gmail.com");
        when(teamLeaderRepository.findByEmail(anyString())).thenReturn(leader);
        assertTrue(teamLeaderService.userExist(leader));
    }


}
