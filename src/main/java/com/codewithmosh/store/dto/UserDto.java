package com.codewithmosh.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// to shorten the user's object to get only necessary or public data in response
public class UserDto {
    //let's say we don't need the id object but somewhere it is necessary so we will use
//    @JsonIgnore //to exclude from result/json response
    @JsonProperty("use_id") //for renaming the id
    private Long id;
    private String name;
    private String email;

    // to format the date time
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // we also need to add the mapping annotation in user mapper interface
//    private LocalDateTime createdAt;

    // we don't have phone number in fields so it will return the null
    // what if we exclude the null we will use
//    @JsonInclude(JsonInclude.Include.NON_NULL) // so it will remove this phone number field from json response result
//    private String phoneNumber;
}
