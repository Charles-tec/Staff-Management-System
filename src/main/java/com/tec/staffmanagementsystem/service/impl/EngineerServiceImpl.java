package com.tec.staffmanagementsystem.service.impl;

import com.tec.staffmanagementsystem.entities.Engineer;
import com.tec.staffmanagementsystem.repository.EngineerRepository;
import com.tec.staffmanagementsystem.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 *
 * @author tec
 */
/**
 * The class Engineer service impl implements engineer service
 */
public class EngineerServiceImpl implements EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;


    @Override

/**
 *
 * Save
 *
 * @param entity  the entity
 * @return Engineer
 */
    public Engineer save(Engineer entity) {

        return engineerRepository.save(entity);
    }

    @Override

/**
 *
 * Update
 *
 * @param entity  the entity
 * @return Engineer
 */
    public Engineer update(Engineer entity) {

        return engineerRepository.save(entity);
    }

    @Override

/**
 *
 * Delete
 *
 * @param entity  the entity
 */
    public void delete(Engineer entity) {

        engineerRepository.delete(entity);

    }

    @Override

/**
 *
 * Delete
 *
 * @param id  the id
 */
    public void delete(Long id) {

        engineerRepository.delete(id);

    }

    @Override

/**
 *
 * Delete in batch
 *
 * @param engineers  the engineers
 */
    public void deleteInBatch(List<Engineer> engineers) {

        engineerRepository.deleteInBatch(engineers);

    }

    @Override

/**
 *
 * Find
 *
 * @param id  the id
 * @return Engineer
 */
    public Engineer find(Long id) {

        return engineerRepository.findOne(id);
    }

    @Override

/**
 *
 * Find all
 *
 * @return List<Engineer>
 */
    public List<Engineer> findAll() {

        return engineerRepository.findAll();
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

        Engineer engineer = this.findByUserName(userName);
        if(engineer == null){
            return false;
        }else{
            if(password.equals(engineer.getPassword())) return true;
            else return false;
        }
    }

    @Override

/**
 *
 * Find by user name
 *
 * @param userName  the user name
 * @return Engineer
 */
    public Engineer findByUserName(String userName) {

        return engineerRepository.findByUserName(userName);
    }
}
