package com.example.crudtest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

//    @GetMapping -> 페이지를 보여줄 때
//    @PostMapping -> 보여진 페이지에서 뭔가 로직을 필요로할 때
    //인덱스 페이지
    @GetMapping("/index")
    public String index(){
        log.info("index start");
        return "/index";
    }
    //리다이렉트 페이지
    @GetMapping("/redirect")
    public String redirect(){
        return "redirect";
    }
}
