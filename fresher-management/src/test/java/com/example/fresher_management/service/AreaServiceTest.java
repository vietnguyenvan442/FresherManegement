package com.example.fresher_management.service;

import com.example.fresher_management.entity.Area;
import com.example.fresher_management.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class AreaServiceTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAllAreas() {
        List<Area> result = areaService.getAllAreas();
        assertEquals(4, result.size());
    }

    @Test
    public void testSave() {
        Area newArea = new Area();
        newArea.setName("New Area");

        Area result = areaService.save(newArea);
        assertNotNull(result);
        assertEquals("New Area", result.getName());
    }

    @Test
    public void testFindById_Success() {
        Area result = areaService.findById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Japane", result.getName());
    }

    @Test
    public void testFindById_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            areaService.findById(999);
        });
    }
}
