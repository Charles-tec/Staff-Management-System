package com.tec.staffmanagementsystem.service.impl;

import com.tec.staffmanagementsystem.entities.Admin;
import com.tec.staffmanagementsystem.entities.Engineer;
import com.tec.staffmanagementsystem.repository.AdminRepository;
import com.tec.staffmanagementsystem.repository.EngineerRepository;
import com.tec.staffmanagementsystem.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineerServiceImpl implements EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;


    @Override
    public Engineer save(Engineer entity) {
        return engineerRepository.save(entity);
    }

    @Override
    public Engineer update(Engineer entity) {
        return engineerRepository.save(entity);
    }

    @Override
    public void delete(Engineer entity) {
        engineerRepository.delete(entity);

    }

    @Override
    public void delete(Long id) {
        engineerRepository.delete(id);

    }

    @Override
    public void deleteInBatch(List<Engineer> engineers) {
        engineerRepository.deleteInBatch(engineers);

    }

    @Override
    public Engineer find(Long id) {
        return engineerRepository.findOne(id);
    }

    @Override
    public List<Engineer> findAll() {
        return engineerRepository.findAll();
    }

    @Override
    public boolean authenticate(String userName, String password) {
        Engineer engineer = this.findByUserName(userName);
        if(engineer == null){
            return false;
        }else{
            if(password.equals(engineer.getPassword())) return true;
            else return false;
        }
    }

    @Override
    public Engineer findByUserName(String userName) {
        return engineerRepository.findByUserName(userName);
    }
}
