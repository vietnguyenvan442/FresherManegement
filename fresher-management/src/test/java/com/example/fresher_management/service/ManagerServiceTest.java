package com.example.fresher_management.service;

import com.example.fresher_management.entity.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
public class ManagerServiceTest {

    @Autowired
    private ManagerService managerService;

    @Test
    public void testGetAllManagers(){
        List<Manager> managers = managerService.getAllManagers();

        assertEquals(2, managers.size());
    }

    @Test
    public void testFindById_Success(){
        Manager manager = managerService.findById(8);

        assertNotNull(manager);
        assertEquals("Nguyen V", manager.getName());
    }

    @Test
    public void testFindById_Null(){
        Manager manager = managerService.findById(1);

        assertNull(manager);
    }
}
