package com.example.fresher_management.service;

import com.example.fresher_management.entity.Fresher;

import java.util.List;

public interface FresherService {
    public Fresher findById(int id);
    public List<Fresher> getFreshers(String token);
    public Fresher addFresher(Fresher fresher);
    public Fresher updateFresher(int id, Fresher fresherDetails);
    public void deleteFresher(int id);
}
