package com.lunarcd.userapp.controller;

import com.lunarcd.userapp.model.User;
import com.lunarcd.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User existing = userService.getUserById(id);
        if (existing != null) {
            existing.setName(userDetails.getName());
            existing.setAge(userDetails.getAge());
            existing.setEmail(userDetails.getEmail());
            existing.setStatus(userDetails.isStatus());
            return userService.saveUser(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/search/name/")
    public List<User> searchByName(@RequestParam String keyword) {
        return userService.findByName(keyword);
    }

    @GetMapping("/search")
    public List<User> searchByNameOrEmail(@RequestParam String keyword) {
        return userService.search(keyword);
    }

    @GetMapping("/age/{age}")
    public Page<User> getUsersByAge(
            @PathVariable int age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        String sortField = sort[0];
        Sort.Direction direction = sort.length > 1 && sort[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        return userService.getUsersByAge(age, pageable);
    }
}
