package com.info.service.impl;

import com.info.model.Roles;
import com.info.model.ServiceStaff;
import com.info.model.StaffUpdate;
import com.info.repository.ServiceStaffRepository;
import com.info.service.ServiceStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ServiceStaffServiceIml implements ServiceStaffService {

    @Autowired
    private ServiceStaffRepository serviceRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ServiceStaffServiceIml(ServiceStaffRepository serviceRepository, BCryptPasswordEncoder passwordEncoder) {
        this.serviceRepository = serviceRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(ServiceStaff serviceStaff) {
        if (serviceStaff != null) {
            Date date = new Date();
            SimpleDateFormat currentDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            serviceStaff.setRole(Roles.SERVICE_STAFF.toString());
            serviceStaff.setData(currentDate.format(date));
            serviceStaff.setPassword(passwordEncoder.encode(serviceStaff.getPassword()));
            serviceRepository.save(serviceStaff);
        }

    }

    @Override
    public ServiceStaff getStaffById(Long id){
        return serviceRepository.getById(id);
    }

    @Override
    public ServiceStaff updateAccount(ServiceStaff serviceStaff, Long id) {
        ServiceStaff staff =  new ServiceStaff();
                serviceRepository.updateAccount(serviceStaff.getFirstName(),
                serviceStaff.getLastName(), serviceStaff.getTypeService(),
                serviceStaff.getDiet(), serviceStaff.getTroops(),
                serviceStaff.getEnsign(), id.intValue());
        return staff;
    }


    public void editAccount(StaffUpdate serviceStaff, Long id) {
        serviceRepository.updateAccount(serviceStaff.getFirstName(),
                serviceStaff.getLastName(), serviceStaff.getTypeService(),
                serviceStaff.getDiet(), serviceStaff.getTroops(),
                serviceStaff.getEnsign(), id.intValue());
    }

    public StaffUpdate setStaffUpdate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ServiceStaff fromAuth = serviceRepository.findByEmail(authentication.getName());
        ServiceStaff staff = getStaffById(fromAuth.getId());

        StaffUpdate serviceStaff = new StaffUpdate();
        serviceStaff.setId(staff.getId());
        serviceStaff.setFirstName(staff.getFirstName());
        serviceStaff.setLastName(staff.getLastName());
        serviceStaff.setDiet(staff.getDiet());
        serviceStaff.setEnsign(staff.getEnsign());
        serviceStaff.setTroops(staff.getTroops());

        return serviceStaff;
    }


    @Override
    public boolean userExist(ServiceStaff serviceStaff) {
        ServiceStaff existServiceStaff = serviceRepository.findByEmail(serviceStaff.getEmail());
        if (existServiceStaff == null)
            return false;
        if (serviceStaff.getEmail().equals(existServiceStaff.getEmail())) {
            return true;
        } else {
            return false;
        }
    }


}
