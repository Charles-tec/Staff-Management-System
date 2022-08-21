package com.tec.staffmanagementsystem.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tec.staffmanagementsystem.entities.Student;
import com.tec.staffmanagementsystem.repository.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentServiceImplTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    /**
     * Method under test: {@link StudentServiceImpl#save(Student)}
     */
    @Test
    public void testSave() {
        Student student = new Student();
        student.setEmploymentDate(LocalDate.ofEpochDay(1L));
        student.setEngineerId(123L);
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Doe");
        student.setPassword("iloveyou");
        student.setSalary(1L);
        student.setUniversity("University");
        student.setUserName("janedoe");
        when(studentRepository.save((Student) any())).thenReturn(student);

        Student student1 = new Student();
        student1.setEmploymentDate(LocalDate.ofEpochDay(1L));
        student1.setEngineerId(123L);
        student1.setFirstName("Jane");
        student1.setId(123L);
        student1.setLastName("Muti");
        student1.setPassword("123");
        student1.setSalary(100);
        student1.setUniversity("University");
        student1.setUserName("janedoe");
        assertSame(student, studentServiceImpl.save(student1));
        verify(studentRepository).save((Student) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#update(Student)}
     */
    @Test
    public void testUpdate() {
        Student student = new Student();
        student.setEmploymentDate(LocalDate.ofEpochDay(1L));
        student.setEngineerId(123L);
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Muti");
        student.setPassword("123");
        student.setSalary(100);
        student.setUniversity("University");
        student.setUserName("janedoe");
        when(studentRepository.save((Student) any())).thenReturn(student);

        Student student1 = new Student();
        student1.setEmploymentDate(LocalDate.ofEpochDay(1L));
        student1.setEngineerId(123L);
        student1.setFirstName("Jane");
        student1.setId(123L);
        student1.setLastName("Doe");
        student1.setPassword("123");
        student1.setSalary(100);
        student1.setUniversity("University");
        student1.setUserName("janedoe");
        assertSame(student, studentServiceImpl.update(student1));
        verify(studentRepository).save((Student) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#delete(Student)}
     */
    @Test
    public void testDelete() {
        doNothing().when(studentRepository).delete((Student) any());

        Student student = new Student();
        student.setEmploymentDate(LocalDate.ofEpochDay(1L));
        student.setEngineerId(123L);
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Muti");
        student.setPassword("123");
        student.setSalary(100);
        student.setUniversity("University");
        student.setUserName("janedoe");
        studentServiceImpl.delete(student);
        verify(studentRepository).delete((Student) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#delete(Long)}
     */
    @Test
    public void testDelete2() {
        doNothing().when(studentRepository).delete((Long) any());
        studentServiceImpl.delete(123L);
        verify(studentRepository).delete((Long) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#deleteInBatch(List)}
     */
    @Test
    public void testDeleteInBatch() {
        doNothing().when(studentRepository).deleteInBatch((Iterable<Student>) any());
        studentServiceImpl.deleteInBatch(new ArrayList<>());
        verify(studentRepository).deleteInBatch((Iterable<Student>) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#find(Long)}
     */
    @Test
    public void testFind() {
        Student student = new Student();
        student.setEmploymentDate(LocalDate.ofEpochDay(1L));
        student.setEngineerId(123L);
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Muti");
        student.setPassword("123");
        student.setSalary(1L);
        student.setUniversity("University");
        student.setUserName("janedoe");
        when(studentRepository.findOne((Long) any())).thenReturn(student);
        assertSame(student, studentServiceImpl.find(123L));
        verify(studentRepository).findOne((Long) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#findAll()}
     */
    @Test
    public void testFindAll() {
        ArrayList<Student> studentList = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> actualFindAllResult = studentServiceImpl.findAll();
        assertSame(studentList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentServiceImpl#findByUserName(String)}
     */
    @Test
    public void testFindByUserName() {
        Student student = new Student();
        student.setEmploymentDate(LocalDate.ofEpochDay(1L));
        student.setEngineerId(123L);
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Muti");
        student.setPassword("123");
        student.setSalary(100);
        student.setUniversity("University");
        student.setUserName("janedoe");
        when(studentRepository.findByUserName((String) any())).thenReturn(student);
        assertSame(student, studentServiceImpl.findByUserName("janedoe"));
        verify(studentRepository).findByUserName((String) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#authenticate(String, String)}
     */

}

