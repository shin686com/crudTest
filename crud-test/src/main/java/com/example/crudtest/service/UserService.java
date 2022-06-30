package com.example.crudtest.service;

import com.example.crudtest.dto.RedirectDto;
import com.example.crudtest.dto.UserDto;

public interface UserService {
    //유저 등록하기
    RedirectDto insertUser(UserDto userDto) throws Exception;
}
