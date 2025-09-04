package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dto.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
// for removing the repetition like using the /users with all api end point it will automatically join this
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    // method: GET
    public Iterable<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        // with response entity we can customize our responses and for that
        // we will not return the result directly
        var user = userRepository.findById(id).orElse(null);

        // check user exist or not and return the http status according to result
        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            // much cleaner way by using the static factory methods
            return ResponseEntity.notFound().build();
        }
        var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userDto);

    }
}
