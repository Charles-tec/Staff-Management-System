package com.tec.staffmanagementsystem.service.impl;
import com.tec.staffmanagementsystem.entities.Student;
import com.tec.staffmanagementsystem.repository.StudentRepository;
import com.tec.staffmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Student save(Student entity) {
        return studentRepository.save(entity);
    }

    @Override
    public Student update(Student entity) {
        return studentRepository.save(entity);
    }

    @Override
    public void delete(Student entity) {
        studentRepository.delete(entity);

    }

    @Override
    public void delete(Long id) {
        studentRepository.delete(id);

    }

    @Override
    public void deleteInBatch(List<Student> students) {
        studentRepository.deleteInBatch(students);

    }

    @Override
    public Student find(Long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
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
    public Student findByUserName(String userName) {
        return studentRepository.findByUserName(userName);
    }
}

