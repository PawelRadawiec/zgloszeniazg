package com.info.repository;

import com.info.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminByEmail(String email);
}
