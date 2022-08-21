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

import com.tec.staffmanagementsystem.entities.Engineer;
import com.tec.staffmanagementsystem.repository.EngineerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {EngineerServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class EngineerServiceImplTest {
    @MockBean
    private EngineerRepository engineerRepository;

    @Autowired
    private EngineerServiceImpl engineerServiceImpl;

    /**
     * Method under test: {@link EngineerServiceImpl#save(Engineer)}
     */
    @Test
    public void testSave() {
        Engineer engineer = new Engineer();
        engineer.setEmail("jane.doe@example.org");
        engineer.setEmploymentDate(LocalDate.ofEpochDay(1L));
        engineer.setFirstName("Jane");
        engineer.setId(123L);
        engineer.setLastName("Bere");
        engineer.setPassword("123");
        engineer.setSalary(1L);
        engineer.setUserName("janedoe");
        when(engineerRepository.save((Engineer) any())).thenReturn(engineer);

        Engineer engineer1 = new Engineer();
        engineer1.setEmail("jane.doe@example.org");
        engineer1.setEmploymentDate(LocalDate.ofEpochDay(1L));
        engineer1.setFirstName("Jane");
        engineer1.setId(123L);
        engineer1.setLastName("Bere");
        engineer1.setPassword("123");
        engineer1.setSalary(500);
        engineer1.setUserName("janedoe");
        assertSame(engineer, engineerServiceImpl.save(engineer1));
        verify(engineerRepository).save((Engineer) any());
    }

    /**
     * Method under test: {@link EngineerServiceImpl#update(Engineer)}
     */
    @Test
    public void testUpdate() {
        Engineer engineer = new Engineer();
        engineer.setEmail("jane.doe@example.org");
        engineer.setEmploymentDate(LocalDate.ofEpochDay(1L));
        engineer.setFirstName("Jane");
        engineer.setId(123L);
        engineer.setLastName("Bere");
        engineer.setPassword("123");
        engineer.setSalary(100);
        engineer.setUserName("janedoe");
        when(engineerRepository.save((Engineer) any())).thenReturn(engineer);

        Engineer engineer1 = new Engineer();
        engineer1.setEmail("jane.doe@example.org");
        engineer1.setEmploymentDate(LocalDate.ofEpochDay(1L));
        engineer1.setFirstName("Jane");
        engineer1.setId(123L);
        engineer1.setLastName("Bere");
        engineer1.setPassword("123");
        engineer1.setSalary(100);
        engineer1.setUserName("janedoe");
        assertSame(engineer, engineerServiceImpl.update(engineer1));
        verify(engineerRepository).save((Engineer) any());
    }

    /**
     * Method under test: {@link EngineerServiceImpl#delete(Engineer)}
     */
    @Test
    public void testDelete() {
        doNothing().when(engineerRepository).delete((Engineer) any());

        Engineer engineer = new Engineer();
        engineer.setEmail("jane.doe@example.org");
        engineer.setEmploymentDate(LocalDate.ofEpochDay(1L));
        engineer.setFirstName("Jane");
        engineer.setId(123L);
        engineer.setLastName("Muti");
        engineer.setPassword("123");
        engineer.setSalary(100);
        engineer.setUserName("janedoe");
        engineerServiceImpl.delete(engineer);
        verify(engineerRepository).delete((Engineer) any());
    }

    /**
     * Method under test: {@link EngineerServiceImpl#delete(Long)}
     */
    @Test
    public void testDelete2() {
        doNothing().when(engineerRepository).delete((Long) any());
        engineerServiceImpl.delete(123L);
        verify(engineerRepository).delete((Long) any());
    }

    /**
     * Method under test: {@link EngineerServiceImpl#deleteInBatch(List)}
     */
    @Test
    public void testDeleteInBatch() {
        doNothing().when(engineerRepository).deleteInBatch((Iterable<Engineer>) any());
        engineerServiceImpl.deleteInBatch(new ArrayList<>());
        verify(engineerRepository).deleteInBatch((Iterable<Engineer>) any());
    }

    /**
     * Method under test: {@link EngineerServiceImpl#find(Long)}
     */
    @Test
    public void testFind() {
        Engineer engineer = new Engineer();
        engineer.setEmail("jane.doe@example.org");
        engineer.setEmploymentDate(LocalDate.ofEpochDay(1L));
        engineer.setFirstName("Jane");
        engineer.setId(123L);
        engineer.setLastName("Bere");
        engineer.setPassword("123");
        engineer.setSalary(100);
        engineer.setUserName("janedoe");
        when(engineerRepository.findOne((Long) any())).thenReturn(engineer);
        assertSame(engineer, engineerServiceImpl.find(123L));
        verify(engineerRepository).findOne((Long) any());
    }

    /**
     * Method under test: {@link EngineerServiceImpl#findAll()}
     */
    @Test
    public void testFindAll() {
        ArrayList<Engineer> engineerList = new ArrayList<>();
        when(engineerRepository.findAll()).thenReturn(engineerList);
        List<Engineer> actualFindAllResult = engineerServiceImpl.findAll();
        assertSame(engineerList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(engineerRepository).findAll();
    }

    /**
     * Method under test: {@link EngineerServiceImpl#findByUserName(String)}
     */
    @Test
    public void testFindByUserName() {
        Engineer engineer = new Engineer();
        engineer.setEmail("jane.doe@example.org");
        engineer.setEmploymentDate(LocalDate.ofEpochDay(1L));
        engineer.setFirstName("Jane");
        engineer.setId(123L);
        engineer.setLastName("Doe");
        engineer.setPassword("iloveyou");
        engineer.setSalary(100);
        engineer.setUserName("janedoe");
        when(engineerRepository.findByUserName((String) any())).thenReturn(engineer);
        assertSame(engineer, engineerServiceImpl.findByUserName("janedoe"));
        verify(engineerRepository).findByUserName((String) any());
    }


}

