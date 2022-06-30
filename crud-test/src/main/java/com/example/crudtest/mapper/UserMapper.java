package com.example.crudtest.mapper;

import com.example.crudtest.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 유저 등록하기
    int insertUser(UserDto userDto) throws Exception;

    // 유저 아이디 등록 여부 체크 : create
    int checkUserId(UserDto userDto) throws Exception;
}
