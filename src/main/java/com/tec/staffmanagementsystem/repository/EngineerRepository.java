package com.tec.staffmanagementsystem.repository;

import com.tec.staffmanagementsystem.entities.Admin;
import com.tec.staffmanagementsystem.entities.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Long> {
    Engineer findByUserName(String userName);
}

