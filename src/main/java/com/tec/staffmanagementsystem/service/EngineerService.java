package com.tec.staffmanagementsystem.service;

import com.tec.staffmanagementsystem.entities.Admin;
import com.tec.staffmanagementsystem.entities.Engineer;
import com.tec.staffmanagementsystem.generic.GenericService;

public interface EngineerService extends GenericService<Engineer> {

    boolean authenticate(String userName, String password);

   Engineer findByUserName(String userName);
}
