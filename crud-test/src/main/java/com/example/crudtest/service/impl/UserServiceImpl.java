package com.example.crudtest.service.impl;

import com.example.crudtest.dto.RedirectDto;
import com.example.crudtest.dto.UpdateUserDto;
import com.example.crudtest.dto.UserDto;
import com.example.crudtest.mapper.UserMapper;
import com.example.crudtest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UserDto> readUser() throws Exception {
        log.info("### readUser start");

        List<UserDto> userDtoList = userMapper.readUser();
        log.info("### userDtoList 값이 잘 넘어 왔는지 확인 : {}",userDtoList);

        log.info("### readUser end");
        return userDtoList;
    }
    // 유저 아이디 비밀번호 불러오기
    @Override
    public List<UserDto> readAllUser() throws Exception {
        log.info("### readAllUser start");

        List<UserDto> userDtoList = userMapper.readAllUser();
        log.info("### readAllUser() : {}",userDtoList);

        log.info("### readAllUser end");
        return userDtoList;
    }
    // 유저 비밀번호 변경
    @Override
    public RedirectDto updateUser(UpdateUserDto updateUserDto) throws Exception {
        log.info("### updateUser start");

        //리턴 타입을 RedirectDto를 맞추기 위해 전역 변수로 인스턴스 생성
        //컨트롤 알트 V
        RedirectDto redirectDto = new RedirectDto();
        //받아온 updateUserDto 객체를 List<UserDto>에 옮겨준다.
        List<UserDto> updateUserList = updateUserDto.getUpdateUserList();
        log.info("### updateUserList : {}",updateUserList);
        if(updateUserList == null){
            redirectDto.setMsg("등록된 유저가 없습니다. 유저 등록 후 이용해주세요.");
            redirectDto.setUrl("/crud/create");
            return redirectDto;
        }
        //향상된 for문
        for (UserDto userDto : updateUserList) {
            log.info("### for문 진입 : {}",userDto);
            //정상 = 1 , 그 외 비정상
            //userDto를 매처에 보내 수정된 값을 저장하고, 비정상인 경우 메시지와 함께 리턴한다.
            if(userMapper.updateUser(userDto) != 1){
                redirectDto.setMsg("아이디 [ "+userDto.getUserId()+" ] 의 비밀번호를 다시 입력해주세요.");
                redirectDto.setUrl("/crud/update");
                return redirectDto;
            }
        }

        redirectDto.setMsg("정상적으로 비밀번호가 변경되었습니다.");
        redirectDto.setUrl("/index");
        log.info("### updateUser end");
        return redirectDto;
    }
    // 유저 삭제
    @Override
    public RedirectDto deleteUser(String userId) throws Exception {
        log.info("### deleteUser start");

        RedirectDto redirectDto = new RedirectDto();

        UserDto userDto = new UserDto(userId);

        //존재하는 아이디인지 확인하는 로직
        // 1 = 존재, 그 외 오류
        //아이디가 존재하지 않거나 다수 존재하면 ..
        if(userMapper.checkUserId(userDto) !=1){
            redirectDto.setMsg("존재하지 않는 아이디입니다. 존재하는 아이디를 입력해주세요.");
            redirectDto.setUrl("/crud/read");
            return redirectDto;
        }
        //삭제에 실패했을경우 1=성공 그 외 실패
        if(userMapper.deleteUser(userDto) != 1){
            redirectDto.setMsg("삭제 실패 다시 시도해주세요.");
            redirectDto.setUrl("/crud/delete");
            return redirectDto;
        }
        //삭제에 성공했을 경우
        redirectDto.setMsg("삭제 성공");
        redirectDto.setUrl("/index");

        log.info("### deleteUser end");
        return redirectDto;
    }
}
