package com.info.service;

import com.info.model.Roles;
import com.info.model.ServiceStaff;
import com.info.repository.ServiceStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ServiceStaff getStaffById(int id){
        return serviceRepository.getById(id);
    }

    @Override
    public ServiceStaff updateAccount(ServiceStaff serviceStaff) {
        ServiceStaff staff = serviceRepository.updateAccount(serviceStaff.getFirstName(),
                serviceStaff.getLastName(), serviceStaff.getTypeService(),
                serviceStaff.getDiet(), serviceStaff.getTroops(),
                serviceStaff.getEnsign(), serviceStaff.getId());
        return staff;
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
