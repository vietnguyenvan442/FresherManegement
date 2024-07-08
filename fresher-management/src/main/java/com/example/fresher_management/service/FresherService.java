package com.example.fresher_management.service;

import com.example.fresher_management.entity.Fresher;

import java.util.List;

<<<<<<< HEAD
public interface FresherService {
    public Fresher findById(int id);
    public List<Fresher> getFreshers(String token);
    public Fresher addFresher(Fresher fresher);
    public Fresher updateFresher(int id, Fresher fresherDetails);
    public void deleteFresher(int id);
=======
@Service
public class FresherService {

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Fresher findById(int id){
        return fresherRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Fresher> getAllFreshers() {
        return fresherRepository.findAll();
    }

    @Transactional
    public List<Fresher> getFreshers(int manager_id){
        return fresherRepository.getFresherByManagerId(manager_id);
    }

    @Transactional
    public Fresher addFresher(Fresher fresher) {
        // Set default position
        fresher.setPosition(positionService.findById(1));

        // Handle language
        if (fresher.getLanguage() != null) {
            Language existingLanguage = languageService.findById(fresher.getLanguage().getId());
            if (existingLanguage != null) {
                fresher.setLanguage(existingLanguage);
            } else {
                Language newLanguage = languageService.save(fresher.getLanguage());
                fresher.setLanguage(newLanguage);
            }
        }

        return fresherRepository.save(fresher);
    }

    @Transactional
    public Fresher updateFresher(int id, Fresher fresherDetails) {
        Optional<Fresher> optionalFresher = fresherRepository.findById(id);
        if (!optionalFresher.isPresent()) {
            return null;
        }

        Fresher fresher = optionalFresher.get();
        fresher.setUsername(fresherDetails.getUsername());
        fresher.setPassword(passwordEncoder.encode(fresherDetails.getPassword()));
        fresher.setName(fresherDetails.getName());
        fresher.setDob(fresherDetails.getDob());
        fresher.setCccd(fresherDetails.getCccd());
        fresher.setSdt(fresherDetails.getSdt());
        fresher.setAddress(fresherDetails.getAddress());
        fresher.setEmail(fresherDetails.getEmail());
        fresher.setSalary(fresherDetails.getSalary());
        fresher.setState(fresherDetails.isState());

        if (fresherDetails.getLanguage() != null) {
            Language existingLanguage = languageService.findById(fresherDetails.getLanguage().getId());
            if (existingLanguage != null) {
                fresher.setLanguage(existingLanguage);
            } else {
                Language newLanguage = languageService.save(fresherDetails.getLanguage());
                fresher.setLanguage(newLanguage);
            }
        }

        return fresherRepository.save(fresher);
    }

    @Transactional
    public void deleteFresher(int id) {
        Optional<Fresher> optionalFresher = fresherRepository.findById(id);
        if (optionalFresher.isPresent()) {
            Fresher existingFresher = optionalFresher.get();
            existingFresher.setState(false);
            fresherRepository.save(existingFresher);
        } else {
            throw new RuntimeException("Fresher not found with id " + id);
        }
    }
>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
}
