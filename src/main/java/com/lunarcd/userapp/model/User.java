package com.lunarcd.userapp.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class User {

    @NotNull(message = "ID cannot be null")
    @Min(value = 1, message = "ID must be greater than 0")
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    public User(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
