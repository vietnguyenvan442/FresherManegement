package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.StatCenterOutputDto;
import com.example.fresher_management.dto.StatisticInputDto;
import com.example.fresher_management.entity.Area;
import com.example.fresher_management.entity.Center;
import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.entity.User;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.CenterRepository;
import com.example.fresher_management.service.AreaService;
import com.example.fresher_management.service.CenterService;
import com.example.fresher_management.service.ManagerService;
import com.example.fresher_management.service.UserService;
import com.example.fresher_management.validate.CenterValidate;
import com.example.fresher_management.validate.EmailFormatValidate;
import com.example.fresher_management.validate.PhoneNumberFormatValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class CenterServiceImpl implements CenterService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private CenterValidate centerValidate;

    @Autowired
    private PhoneNumberFormatValidate phoneNumberFormatValidate;

    @Autowired
    private EmailFormatValidate emailFormatValidate;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    @Cacheable(value = "centers", key = "#user")
    public List<Center> getAll(User user) {
        log.info("Fetching centers for user: {}", user);
        if ("ADMIN".equalsIgnoreCase(user.getRole().getName())) {
            return centerRepository.findByStateTrue();
        } else if ("MANAGER".equalsIgnoreCase(user.getRole().getName())) {
            return centerRepository.getCenterByManagerIdAndStateTrue(user.getId());
        }
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(value = "centers", allEntries = true)
    public Center save(Center center) {
        log.info("Saving center id: {}", center.getId());
        validateCenter(center);

        center.setArea(getOrSaveArea(center.getArea()));
        center.setManager(getOrSaveManager(center.getManager()));
        center.setState(true);

        Center savedCenter = centerRepository.save(center);
        log.info("Saved center id: {}", savedCenter.getId());
        return savedCenter;
    }

    @Override
    @Transactional
    @CachePut(value = "centers", key = "#id")
    public Center updateById(int id, Center updatedCenter) {
        log.info("Updating center with id: {}", id);
        Center existingCenter = findById(id);
        updateCenterDetails(existingCenter, updatedCenter);
        Center updatedCenterResult = centerRepository.save(existingCenter);
        log.info("Updated center: {}", updatedCenterResult);
        return updatedCenterResult;
    }

    @Override
    @Transactional
    @CacheEvict(value = "centers", allEntries = true)
    public void deleteById(int id) {
        log.info("Deleting center with id: {}", id);
        Center existingCenter = findById(id);
        existingCenter.setState(false);
        centerRepository.save(existingCenter);
        log.info("Deleted center with id: {}", id);
    }

    @Override
    @Transactional
    @Cacheable(value = "centers", key = "#id")
    public Center findById(int id) {
        log.info("Finding center by id: {}", id);
        return centerRepository.findByIdAndStateTrue(id)
                .orElseThrow(() -> {
                    log.error("Center not found with id {}", id);
                    return new ResourceNotFoundException("Center not found with id " + id);
                });
    }

    @Override
    @Transactional
    public List<StatCenterOutputDto> statNumOfFresToCenter(StatisticInputDto statisticInputDto) {
        log.info("Statistics on the number of freshers at the center during the period from {} to {}", statisticInputDto.getStart_date(), statisticInputDto.getEnd_date());
        return centerRepository.statNumOfFresherToCenter(statisticInputDto.getStart_date(), statisticInputDto.getEnd_date());
    }

    @Override
    @Transactional
    public List<StatCenterOutputDto> statNumOfFresToCenterForManager(StatisticInputDto statisticInputDto, int manager_id) {
        log.info("Statistics on the number of freshers at the center for manager id: {} during the period from {} to {}", manager_id, statisticInputDto.getStart_date(), statisticInputDto.getEnd_date());
        return centerRepository.statNumOfFresherToCenterForManager(statisticInputDto.getStart_date(), statisticInputDto.getEnd_date(), manager_id);
    }

    private void validateCenter(Center center) {
        centerValidate.validateMandatoryFields(center);
        phoneNumberFormatValidate.validatePhoneNumberFormat(center.getSdt());
        emailFormatValidate.validateEmailFormat(center.getEmail());
        centerValidate.validateUniqueEmail(center.getEmail());
        centerValidate.validateUniquePhongNumber(center.getSdt());
    }

    private Area getOrSaveArea(Area area) {
        Area existingArea = areaService.findById(area.getId());
        if (existingArea == null) {
            areaService.save(area);
            return area;
        }
        return existingArea;
    }

    private Manager getOrSaveManager(Manager manager) {
        Manager existingManager = managerService.findById(manager.getId());
        if (existingManager == null) {
            managerService.save(manager);
            return manager;
        }
        return existingManager;
    }

    private void updateCenterDetails(Center existingCenter, Center updatedCenter) {
        if (updatedCenter.getName() != null && !existingCenter.getName().equals(updatedCenter.getName())) {
            existingCenter.setName(updatedCenter.getName());
        }
        if (updatedCenter.getSdt() != null && !existingCenter.getSdt().equals(updatedCenter.getSdt())) {
            phoneNumberFormatValidate.validatePhoneNumberFormat(updatedCenter.getSdt());
            centerValidate.validateUniquePhongNumber(updatedCenter.getSdt());
            existingCenter.setSdt(updatedCenter.getSdt());
        }
        if (updatedCenter.getAddress() != null && !existingCenter.getAddress().equals(updatedCenter.getAddress())) {
            existingCenter.setAddress(updatedCenter.getAddress());
        }
        if (updatedCenter.getEmail() != null && !existingCenter.getEmail().equals(updatedCenter.getEmail())) {
            emailFormatValidate.validateEmailFormat(updatedCenter.getEmail());
            centerValidate.validateUniqueEmail(updatedCenter.getEmail());
            existingCenter.setEmail(updatedCenter.getEmail());
        }
        if (updatedCenter.getDescription() != null && !existingCenter.getDescription().equals(updatedCenter.getDescription())) {
            existingCenter.setDescription(updatedCenter.getDescription());
        }

        if (updatedCenter.getArea() != null) {
            existingCenter.setArea(getOrSaveArea(updatedCenter.getArea()));
        }

        if (updatedCenter.getManager() != null) {
            existingCenter.setManager(getOrSaveManager(updatedCenter.getManager()));
        }
    }
}
