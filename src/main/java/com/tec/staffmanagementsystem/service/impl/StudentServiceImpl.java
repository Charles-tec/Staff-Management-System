package com.tec.staffmanagementsystem.service.impl;

import com.tec.staffmanagementsystem.entities.Student;
import com.tec.staffmanagementsystem.repository.StudentRepository;
import com.tec.staffmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 * @author tec
 */
@Service

/**
 * The class Student service impl implements student service
 */
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;


    @Override

/**
 *
 * Save
 *
 * @param entity  the entity
 * @return Student
 */
    public Student save(Student entity) {

        return studentRepository.save(entity);
    }

    @Override

/**
 *
 * Update
 *
 * @param entity  the entity
 * @return Student
 */
    public Student update(Student entity) {

        return studentRepository.save(entity);
    }

    @Override

/**
 *
 * Delete
 *
 * @param entity  the entity
 */
    public void delete(Student entity) {

        studentRepository.delete(entity);

    }

    @Override

/**
 *
 * Delete
 *
 * @param id  the id
 */
    public void delete(Long id) {

        studentRepository.delete(id);

    }

    @Override

/**
 *
 * Delete in batch
 *
 * @param students  the students
 */
    public void deleteInBatch(List<Student> students) {

        studentRepository.deleteInBatch(students);

    }

    @Override

/**
 *
 * Find
 *
 * @param id  the id
 * @return Student
 */
    public Student find(Long id) {

        return studentRepository.findOne(id);
    }

    @Override

/**
 *
 * Find all
 *
 * @return List<Student>
 */
    public List<Student> findAll() {

        return studentRepository.findAll();
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

        Student student = this.findByUserName(userName);
        if(student == null){
            return false;
        }else{
            if(password.equals(student.getPassword())) return true;
            else return false;
        }
    }

    @Override

/**
 *
 * Find by user name
 *
 * @param userName  the user name
 * @return Student
 */
    public Student findByUserName(String userName) {

        return studentRepository.findByUserName(userName);
    }
}

