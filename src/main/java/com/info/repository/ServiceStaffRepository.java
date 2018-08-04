package com.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.info.model.ServiceStaff;

@Repository("serviceStaffRepository")
public interface ServiceStaffRepository extends JpaRepository<ServiceStaff, Long> {
	ServiceStaff findByEmail(String email);
}
