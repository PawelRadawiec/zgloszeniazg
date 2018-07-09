package com.info.configuration;

import com.info.model.Admin;
import com.info.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.security.provider.PolicyParser;

import java.util.Arrays;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String adminEmail) throws UsernameNotFoundException {
        Admin admin = adminRepository.findAdminByEmail(adminEmail);
        if(admin == null){
            throw  new UsernameNotFoundException("User not authorized");
        }

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(admin.getRole());
        UserDetails userDetails = new User(admin.getEmail(), admin.getPassword(), Arrays.asList(grantedAuthority));
        return  userDetails;
    }
}
