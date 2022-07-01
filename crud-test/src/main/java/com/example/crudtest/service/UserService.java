package com.example.crudtest.service;

import com.example.crudtest.dto.RedirectDto;
import com.example.crudtest.dto.UpdateUserDto;
import com.example.crudtest.dto.UserDto;

import java.util.List;

public interface UserService {
    //유저 등록하기
    RedirectDto insertUser(UserDto userDto) throws Exception;

    //유저 아이디 불러오기
    List<UserDto> readUser() throws Exception;

    //유저 아이디, 비밀번호 불러오기
    List<UserDto> readAllUser() throws Exception;
    //유저 비밀번호 변경
    RedirectDto updateUser(UpdateUserDto updateUserDto) throws Exception;
    //유저 삭제
    RedirectDto deleteUser(String userId) throws Exception;
}
