package com.info.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.model.Roles;
import com.info.model.ServiceStaff;
import com.info.model.TeamLeader;
import com.info.repository.ServiceStaffRepository;

@Service
public class ServiceStaffServiceIml implements ServiceStaffService {
	
	private ServiceStaffRepository serviceRepository;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public ServiceStaffServiceIml(ServiceStaffRepository serviceRepository,BCryptPasswordEncoder passwordEncoder ) {
		this.serviceRepository = serviceRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void save(ServiceStaff serviceStaff) {
		if(serviceStaff != null) {
			Date date = new Date();
			SimpleDateFormat currentDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			serviceStaff.setRole(Roles.SERVICE_STAFF.toString());
			serviceStaff.setData(currentDate.format(date));
			serviceStaff.setPassword(passwordEncoder.encode(serviceStaff.getPassword()));
			serviceRepository.save(serviceStaff);
		}
		
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
