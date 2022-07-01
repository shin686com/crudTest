package com.example.crudtest.mapper;

import com.example.crudtest.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 유저 등록하기
    int insertUser(UserDto userDto) throws Exception;

    // 유저 아이디 등록 여부 체크 : create
    int checkUserId(UserDto userDto) throws Exception;

    //모든 유저 ID 조회하기 : read 로직에 사용
    List<UserDto> readUser() throws Exception;

    //UserId, UserPw 조회 : update 로직에 사용
    List<UserDto> readAllUser() throws Exception;

    //유저 아이디 삭제하기
    int deleteUser(UserDto userDto) throws Exception;

    //User 수정 : 아이디 기준 비밀번호 변경 + update foreach
    int updateUser(UserDto userDto) throws Exception;
}
