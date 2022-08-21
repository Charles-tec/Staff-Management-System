package com.tec.staffmanagementsystem.repository;

import com.tec.staffmanagementsystem.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tec
 */

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

        Admin  findByUserName(String userName);

}
