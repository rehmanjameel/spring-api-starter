package com.codewithmosh.store.dto;

import lombok.Data;

@Data // work for both the getter and setter
public class RegisterUserRequest {
    private String name;
    private String email;
    private String password;
}
