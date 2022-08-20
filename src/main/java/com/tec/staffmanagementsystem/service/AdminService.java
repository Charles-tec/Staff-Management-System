package com.tec.staffmanagementsystem.service;

import com.tec.staffmanagementsystem.entities.Admin;
import com.tec.staffmanagementsystem.generic.GenericService;
import org.springframework.stereotype.Service;

public interface AdminService extends GenericService<Admin> {
    boolean authenticate(String userName, String password);

    Admin findByUserName(String userName);
}
