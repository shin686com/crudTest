package com.example.crudtest.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateUserDto {

    private List<UserDto> updateUserList;
}
