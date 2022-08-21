package com.tec.staffmanagementsystem.service;

import com.tec.staffmanagementsystem.entities.Engineer;
import com.tec.staffmanagementsystem.generic.GenericService;

/**
 *
 * @author tec
 */
public interface EngineerService extends GenericService<Engineer> {

    boolean authenticate(String userName, String password);

   Engineer findByUserName(String userName);
}
