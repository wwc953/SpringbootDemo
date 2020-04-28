package com.example.demo.service;

import com.example.demo.dao.MyTestMapper;
import com.example.demo.model.MyTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangweichun
 * @create 2020-04-28 21:53
 **/
@Service
public class MyTestService {

    @Autowired
    MyTestMapper myTestMapper;

    public List<MyTest> getAll(){
        List<MyTest> all = myTestMapper.getAll();
        return all;
    }

}
