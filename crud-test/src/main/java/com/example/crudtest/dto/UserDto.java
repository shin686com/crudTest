package com.example.crudtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String userPw;

    public UserDto(String userId){
        this.userId = userId;
    }

}
