package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangweichun
 * @create 2020-04-18 0:17
 **/
@RestController
public class HelloController {

    @Value("${aaa.bbb}")
    private String flag;

    @Value("${mmm.ccc}")
    private String mainString;

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot-------" + flag + "========" + mainString;
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "2222222222";
    }

    @GetMapping("/bb")
    public String bbb() {
        return "bbbbbb";
    }

}
