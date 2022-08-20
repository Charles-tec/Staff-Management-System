package com.tec.staffmanagementsystem.service;

import com.tec.staffmanagementsystem.entities.Engineer;
import com.tec.staffmanagementsystem.entities.Student;
import com.tec.staffmanagementsystem.generic.GenericService;

public interface StudentService extends GenericService<Student> {
    Student findByUserName(String userName);
    boolean authenticate(String userName, String password);
}

