package com.codewithmosh.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// to shorten the user's object to get only necessary or public data in response
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
