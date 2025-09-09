package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dto.RegisterUserRequest;
import com.codewithmosh.store.dto.UpdateUserRequest;
import com.codewithmosh.store.dto.UserDto;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

//@Mapper(componentModel = "spring", config = CentralMapperConfig.class)
@Mapper(componentModel = "spring") // to create beans of this type at runtime
public interface UserMapper {

//    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto userToUserDto(User user);

    User toEntity(RegisterUserRequest request);

    void updateUser(UpdateUserRequest request, @MappingTarget User user);
}
