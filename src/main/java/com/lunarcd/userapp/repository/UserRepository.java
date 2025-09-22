package com.lunarcd.userapp.repository;

import com.lunarcd.userapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<Long, User> database = new HashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(database.values());
    }

    public void save(User user) {
        database.put(user.getId(), user);
    }
}
