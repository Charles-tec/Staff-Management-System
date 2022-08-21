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

import com.tec.staffmanagementsystem.entities.Admin;
import com.tec.staffmanagementsystem.repository.AdminRepository;

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

@ContextConfiguration(classes = {AdminServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceImplTest {
    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    /**
     * Method under test: {@link AdminServiceImpl#save(Admin)}
     */
    @Test
    public void testSave() {
        Admin admin = new Admin();
        admin.setEmploymentDate(LocalDate.ofEpochDay(1L));
        admin.setFirstName("Jane");
        admin.setId(123L);
        admin.setLastName("Muti");
        admin.setPassword("123");
        admin.setPhoneNumber(1L);
        admin.setSalary(100);
        admin.setUserName("janedoe");
        when(adminRepository.save((Admin) any())).thenReturn(admin);

        Admin admin1 = new Admin();
        admin1.setEmploymentDate(LocalDate.ofEpochDay(1L));
        admin1.setFirstName("Jane");
        admin1.setId(123L);
        admin1.setLastName("Muti");
        admin1.setPassword("123");
        admin1.setPhoneNumber(1L);
        admin1.setSalary(100);
        admin1.setUserName("janedoe");
        assertSame(admin, adminServiceImpl.save(admin1));
        verify(adminRepository).save((Admin) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#update(Admin)}
     */
    @Test
    public void testUpdate() {
        Admin admin = new Admin();
        admin.setEmploymentDate(LocalDate.ofEpochDay(1L));
        admin.setFirstName("Jane");
        admin.setId(123L);
        admin.setLastName("Muti");
        admin.setPassword("123");
        admin.setPhoneNumber(1L);
        admin.setSalary(100);
        admin.setUserName("janedoe");
        when(adminRepository.save((Admin) any())).thenReturn(admin);

        Admin admin1 = new Admin();
        admin1.setEmploymentDate(LocalDate.ofEpochDay(1L));
        admin1.setFirstName("Jane");
        admin1.setId(123L);
        admin1.setLastName("Muti");
        admin1.setPassword("123");
        admin1.setPhoneNumber(1L);
        admin1.setSalary(100);
        admin1.setUserName("janedoe");
        assertSame(admin, adminServiceImpl.update(admin1));
        verify(adminRepository).save((Admin) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#delete(Admin)}
     */
    @Test
    public void testDelete() {
        doNothing().when(adminRepository).delete((Admin) any());

        Admin admin = new Admin();
        admin.setEmploymentDate(LocalDate.ofEpochDay(1L));
        admin.setFirstName("Jane");
        admin.setId(123L);
        admin.setLastName("Muti");
        admin.setPassword("123");
        admin.setPhoneNumber(1L);
        admin.setSalary(100);
        admin.setUserName("janedoe");
        adminServiceImpl.delete(admin);
        verify(adminRepository).delete((Admin) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#delete(Long)}
     */
    @Test
    public void testDelete2() {
        doNothing().when(adminRepository).delete((Long) any());
        adminServiceImpl.delete(123L);
        verify(adminRepository).delete((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteInBatch(List)}
     */
    @Test
    public void testDeleteInBatch() {
        doNothing().when(adminRepository).deleteInBatch((Iterable<Admin>) any());
        adminServiceImpl.deleteInBatch(new ArrayList<>());
        verify(adminRepository).deleteInBatch((Iterable<Admin>) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#find(Long)}
     */
    @Test
    public void testFind() {
        Admin admin = new Admin();
        admin.setEmploymentDate(LocalDate.ofEpochDay(1L));
        admin.setFirstName("Jane");
        admin.setId(123L);
        admin.setLastName("Muti");
        admin.setPassword("123");
        admin.setPhoneNumber(1L);
        admin.setSalary(100);
        admin.setUserName("janedoe");
        when(adminRepository.findOne((Long) any())).thenReturn(admin);
        assertSame(admin, adminServiceImpl.find(123L));
        verify(adminRepository).findOne((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#findAll()}
     */
    @Test
    public void testFindAll() {
        ArrayList<Admin> adminList = new ArrayList<>();
        when(adminRepository.findAll()).thenReturn(adminList);
        List<Admin> actualFindAllResult = adminServiceImpl.findAll();
        assertSame(adminList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(adminRepository).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#authenticate(String, String)}
     */

    @Test
    public void testFindByUserName() {
        Admin admin = new Admin();
        admin.setEmploymentDate(LocalDate.ofEpochDay(1L));
        admin.setFirstName("Jane");
        admin.setId(123L);
        admin.setLastName("Muti");
        admin.setPassword("123");
        admin.setPhoneNumber(1L);
        admin.setSalary(1L);
        admin.setUserName("janedoe");
        when(adminRepository.findByUserName((String) any())).thenReturn(admin);
        assertSame(admin, adminServiceImpl.findByUserName("janedoe"));
        verify(adminRepository).findByUserName((String) any());
    }
}

