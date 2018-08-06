package com.info.configuration;

import com.info.model.ServiceStaff;
import com.info.repository.ServiceStaffRepository;
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
public class StaffDetailsService implements UserDetailsService {

    @Autowired
    ServiceStaffRepository serviceStaffRepository;

    @Override
    public UserDetails loadUserByUsername(String staffEmail) throws UsernameNotFoundException {
        ServiceStaff serviceStaff = serviceStaffRepository.findByEmail(staffEmail);
        if(serviceStaff == null){
            throw  new UsernameNotFoundException("User not authorized");
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(serviceStaff.getRole());
        UserDetails userDetails = new User(serviceStaff.getEmail(), serviceStaff.getPassword(), Arrays.asList(grantedAuthority));
        return  userDetails;
    }
}
