package com.example.crudtest.controller;

import com.example.crudtest.dto.RedirectDto;
import com.example.crudtest.dto.UserDto;
import com.example.crudtest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

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
}
