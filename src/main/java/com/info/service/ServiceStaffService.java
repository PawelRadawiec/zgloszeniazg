package com.info.service;

import org.springframework.stereotype.Service;

import com.info.model.ServiceStaff;

@Service
public interface ServiceStaffService {
	
	void save(ServiceStaff serviceStaff);

	boolean userExist(ServiceStaff serviceStaff);

	ServiceStaff updateAccount(ServiceStaff serviceStaff, Long id);

	ServiceStaff getStaffById(Long id);
}
 
