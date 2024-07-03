package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Center;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CenterRepositoryImpl implements CustomCenterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> T merge(T entity) {
        return entityManager.merge(entity);
    }
}
