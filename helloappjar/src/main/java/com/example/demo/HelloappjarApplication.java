package com.example.demo;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
public class HelloappjarApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloappjarApplication.class, args);
    }

}
