package com.lunarcd.userapp.service;

import com.lunarcd.userapp.model.User;
import com.lunarcd.userapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByName(String keyword) {
        return userRepository.findByNameContaining(keyword);
    }

    public Page<User> getUsersByAge(int age, Pageable pageable) {
        return userRepository.findByAgeGreaterThan(age, pageable);
    }

    public List<User> search(String keyword) {
        return userRepository.searchByNameOrEmail(keyword);
    }
}
