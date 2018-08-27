package com.info.service.impl;

import com.info.model.Admin;
import com.info.model.ServiceStaff;
import com.info.model.TeamLeader;
import com.info.repository.AdminRepository;
import com.info.repository.ServiceStaffRepository;
import com.info.repository.TeamLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    private ServiceStaffRepository serviceStaffRepository;
    private TeamLeaderRepository teamLeaderRepository;
    private AdminRepository adminRepository;

    @Autowired
    public CommonService(ServiceStaffRepository serviceStaffRepository,
                         TeamLeaderRepository teamLeaderRepository,
                         AdminRepository adminRepository) {
        this.serviceStaffRepository = serviceStaffRepository;
        this.teamLeaderRepository = teamLeaderRepository;
        this.adminRepository = adminRepository;
    }

    public boolean checkEmail(String email) {
        TeamLeader leaderByemail = teamLeaderRepository.findByEmail(email);
        Admin adminByEmail = adminRepository.findAdminByEmail(email);
        ServiceStaff serviceStaff = serviceStaffRepository.findByEmail(email);

        if(leaderByemail != null || adminByEmail != null || serviceStaff != null){
            return true;
        } else {
            return false;
        }
    }

}
