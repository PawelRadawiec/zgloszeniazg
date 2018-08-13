package com.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.info.model.ServiceStaff;

import javax.transaction.Transactional;

@Transactional
@Repository("serviceStaffRepository")
public interface ServiceStaffRepository extends CrudRepository<ServiceStaff, Long> {
	ServiceStaff findByEmail(String email);

	@Modifying
	@Query(value = "UPDATE service_staff SET first_name=?, last_name=?, type_service=?, diet=?, troops=?, ensign=? where id=?", nativeQuery = true)
	void updateAccount(@Param("firstName") String firstName,
							   @Param("lastName") String lastName,
							   @Param("typeService") String typeService,
							   @Param("diet") String diet,
							   @Param("troops") String troops,
							   @Param("ensign") String ensign,
							   @Param("id") int id);

	@Query(value = "select * from service_staff where id=?", nativeQuery = true)
	ServiceStaff getById(@Param("id") Long id);
}