package com.tec.staffmanagementsystem.service.impl;

import com.tec.staffmanagementsystem.entities.Admin;
import com.tec.staffmanagementsystem.repository.AdminRepository;
import com.tec.staffmanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public Admin save(Admin entity) {
        return adminRepository.save(entity);
    }

    @Override
    public Admin update(Admin entity) {
        return adminRepository.save(entity);
    }

    @Override
    public void delete(Admin entity) {
        adminRepository.delete(entity);

    }

    @Override
    public void delete(Long id) {
        adminRepository.delete(id);

    }

    @Override
    public void deleteInBatch(List<Admin> admins) {
      adminRepository.deleteInBatch(admins);

    }

    @Override
    public Admin find(Long id) {
       return adminRepository.findOne(id);
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
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
    public Admin findByUserName(String userName) {
        return adminRepository.findByUserName(userName);
    }
}

