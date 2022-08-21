package com.tec.staffmanagementsystem.repository;

import com.tec.staffmanagementsystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tec
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUserName(String userName);
}

