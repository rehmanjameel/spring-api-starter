package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dto.UserDto;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
// for removing the repetition like using the /users with all api end point it will automatically join this
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    @GetMapping
//    // method: GET
//    public Iterable<UserDto> getUsers() {
//        return userRepository.findAll()
//                .stream()
//                .map(userMapper::userToUserDto) // replaces the lambda method to method reference
//                .toList();
//    }

    // for query parameters example
    @GetMapping
    // method: GET
    public Iterable<UserDto> getUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sortBy
    ) {
        // check for only valid values
        if (!Set.of("name","email").contains(sortBy)) {
            // by default
            sortBy =  "name";
        }

        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::userToUserDto) // replaces the lambda method to method reference
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
//        var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userMapper.userToUserDto(user));

    }
}
