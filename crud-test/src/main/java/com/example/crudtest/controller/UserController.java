package com.example.crudtest.controller;

import com.example.crudtest.dto.RedirectDto;
import com.example.crudtest.dto.UpdateUserDto;
import com.example.crudtest.dto.UserDto;
import com.example.crudtest.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/crud")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //create(insert) : 유지 등록하기 페이지
    @GetMapping("/create")
    public String insertUserPage(){
        log.info("### insetUserPage start");
        return "/crud/create";
    }

    @PostMapping("/create")
    public String insertUser(HttpServletRequest request, Model model) throws Exception {
        log.info("### insertUser start");

        UserDto userDto = new UserDto();
        userDto.setUserId(request.getParameter("userId"));
        userDto.setUserPw(request.getParameter("userPw"));
        log.info("### userDto : {}", userDto);

        RedirectDto redirectDto = userService.insertUser(userDto);
        log.info("### redirection : {}", redirectDto);

        model.addAttribute("msg",redirectDto.getMsg());
        model.addAttribute("url",redirectDto.getUrl());

        log.info("### insertUser end");
        return "/redirect";
    }

    @GetMapping("/read")
    public String readUserPage(Model model){
        log.info("### readUserPage start");

        try{
            List<UserDto> userDtoList = userService.readUser();
            log.info("### userDtoList : {}",userDtoList);

            //유저가 없을 떄 index 조회 시 IndexOutOfBoundsException 뜨는 것 주의!
            log.info("### userDtoList : {}",userDtoList.get(0));

            model.addAttribute("userDtoList", userDtoList);
            log.info("### readUserPage end 1");
            return "/crud/read";
        }catch (IndexOutOfBoundsException e){

            model.addAttribute("msg","등록된 유저가 없습니다. 먼저 유저를 등록해주세요.");
            model.addAttribute("url","/crud/create");
            log.info("### readUserPage end 2");
            return "/redirect";

        }catch (Exception e){

            model.addAttribute("msg","오류가 발생했습니다. 다시 시도해주세요.");
            model.addAttribute("url","/crud/read");
            log.info("### readUserPage end 3");
            return "/redirect";

        }
    }
    @GetMapping("/update")
    public String updateUserPage(Model model) throws Exception{
        log.info("### updateUserPage start");

        List<UserDto> userDtoList = userService.readAllUser();
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setUpdateUserList(userDtoList);

        model.addAttribute("updateUserDto",updateUserDto);

        log.info("### updateUserPage end");
        return "/crud/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute UpdateUserDto updateUserDto, Model model)throws Exception{
        log.info("### updateUser start");

        RedirectDto redirectDto = userService.updateUser(updateUserDto);
        log.info("### redirectDto : {}",redirectDto);

        model.addAttribute("msg",redirectDto.getMsg());
        model.addAttribute("url",redirectDto.getUrl());

        log.info("### updateUser end");
        return "/redirect";
    }
    @GetMapping("/delete")
    public String deleteUserPage(){
        log.info("### deleteUserPage pass");
        return "/crud/delete";
    }
    @PostMapping("/delete")
    public String deleteUser(HttpServletRequest request, Model model) throws Exception{
        log.info("### deleteUser start");

        String userId = request.getParameter("userId");
        log.info("### userDto");

        RedirectDto redirectDto = userService.deleteUser(userId);
        log.info("### redirectDto : {}",redirectDto);

        model.addAttribute("msg",redirectDto.getMsg());
        model.addAttribute("url",redirectDto.getUrl());

        log.info("### deleteUser end");
        return "/redirect";
    }
}
