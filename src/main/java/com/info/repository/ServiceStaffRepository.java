package com.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.info.model.ServiceStaff;

@Repository("serviceStaffRepository")
public interface ServiceStaffRepository extends JpaRepository<ServiceStaff, Long> {
	ServiceStaff findByEmail(String email);

	@Query(value = "update service_staff set first_name=?, last_name=?, type_service=?, diet=?, troops=?, ensign=? where id=?", nativeQuery = true)
	ServiceStaff updateAccount(@Param("firstName") String firstName,
							   @Param("lastName") String lastName,
							   @Param("typeService") String typeService,
							   @Param("diet") String diet,
							   @Param("troops") String troops,
							   @Param("ensign") String ensign,
							   @Param("id") Long id);

	@Query(value = "select * from service_staff where id=?", nativeQuery = true)
	ServiceStaff getById(@Param("id") int id);
}