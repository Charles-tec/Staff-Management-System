package com.tec.staffmanagementsystem.service.impl;

import com.tec.staffmanagementsystem.entities.Admin;
import com.tec.staffmanagementsystem.repository.AdminRepository;
import com.tec.staffmanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

/**
 * @author tec
 *
 * The class Admin service impl implements admin service
 */
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Override

/**
 *
 * Save
 *
 * @param entity  the entity
 * @return Admin
 */
    public Admin save(Admin entity) {

        return adminRepository.save(entity);
    }

    @Override

/**
 *
 * Update
 *
 * @param entity  the entity
 * @return Admin
 */
    public Admin update(Admin entity) {

        return adminRepository.save(entity);
    }

    @Override

/**
 *
 * Delete
 *
 * @param entity  the entity
 */
    public void delete(Admin entity) {

        adminRepository.delete(entity);

    }

    @Override

/**
 *
 * Delete
 *
 * @param id  the id
 */
    public void delete(Long id) {

        adminRepository.delete(id);

    }

    @Override

/**
 *
 * Delete in batch
 *
 * @param admins  the admins
 */
    public void deleteInBatch(List<Admin> admins) {

        adminRepository.deleteInBatch(admins);

    }

    @Override

/**
 *
 * Find
 *
 * @param id  the id
 * @return Admin
 */
    public Admin find(Long id) {

        return adminRepository.findOne(id);
    }

    @Override

/**
 *
 * Find all
 *
 * @return List<Admin>
 */
    public List<Admin> findAll() {

        return adminRepository.findAll();
    }

    @Override

/**
 *
 * Authenticate
 *
 * @param userName  the user name
 * @param password  the password
 * @return boolean
 */
    public boolean authenticate(String userName, String password) {

        Admin admin = this.findByUserName(userName);
        if(admin == null){
            return false;
        }else{
            if(password.equals(admin.getPassword())) return true;
            else return false;
        }
    }

    @Override

/**
 *
 * Find by user name
 *
 * @param userName  the user name
 * @return Admin
 */
    public Admin findByUserName(String userName) {

        return adminRepository.findByUserName(userName);
    }
}

