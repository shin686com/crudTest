package com.example.crudtest.service.impl;

import com.example.crudtest.dto.RedirectDto;
import com.example.crudtest.dto.UserDto;
import com.example.crudtest.mapper.UserMapper;
import com.example.crudtest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public RedirectDto insertUser(UserDto userDto) throws Exception {
        log.info("### insertUser start");
        log.info("### userDto 값이 잘 넘어 왔는지 확인 : {}",userDto);

        RedirectDto redirectDto = new RedirectDto();

        //아이디 중복 체크 로직 : 1 -> 아이디 존재, 0 -> 없음
        if(userMapper.checkUserId(userDto)==1){
            log.info("### 중복 체크에 걸림 : 중복된 아이디 : {}", userDto.getUserId());

            redirectDto.setMsg("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
            redirectDto.setUrl("/crud/create");

            log.info("### insertUser end");
            return redirectDto;
        }
        //User 등록 1 -> 정상 등록 or 0->비정상 등록
        if(userMapper.insertUser(userDto)==1){
            redirectDto.setMsg("정상 등록 되었습니다.");
            redirectDto.setUrl("/index");
        }else{
            redirectDto.setMsg("등록에 문제가 생겼습니다.");
            redirectDto.setUrl("/crud/create");
        }

        log.info("### insertUser end");
        return redirectDto;
    }
}
