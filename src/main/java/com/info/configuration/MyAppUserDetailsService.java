package com.info.configuration;

import com.info.model.TeamLeader;
import com.info.repository.TeamLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MyAppUserDetailsService implements UserDetailsService {

    @Autowired
    TeamLeaderRepository teamLeaderRepository;

    @Override
    public UserDetails loadUserByUsername(String teamLeaderEmail) throws UsernameNotFoundException {
        TeamLeader teamLeader = teamLeaderRepository.findByEmail(teamLeaderEmail);
        if(teamLeader == null){
            throw new UsernameNotFoundException("User not authorized.");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(teamLeader.getRole());
        UserDetails userDetails = new User(teamLeader.getEmail(), teamLeader.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
