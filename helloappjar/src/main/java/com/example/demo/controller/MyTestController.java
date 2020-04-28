package com.example.demo.controller;

import com.example.demo.dao.MyTestMapper;
import com.example.demo.model.MyTest;
import com.example.demo.service.MyTestService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangweichun
 * @create 2020-04-28 21:52
 **/
@RestController
public class MyTestController {
    @Autowired
    private MyTestService myTestService;

    @Autowired
    private MyTestMapper mapper;

    @GetMapping("/getAll/{pageNum}")
    public List<MyTest> getAllPerson(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
//        PageHelper.startPage(pageNum,2);
        Page<MyTest> pageInfo = PageHelper.startPage(1, 3)
                .doSelectPage(() -> myTestService.getAll());
//        List<MyTest> list = myTestService.getAll();
//        PageInfo<MyTest> pageInfo = new PageInfo<MyTest>(list);
//        model.addAttribute("pageInfo",pageInfo);
        return pageInfo.getResult();
    }

    @GetMapping("/getAllBylimit/{curNum}/{pageSize}")
    public List<MyTest> getAllBylimit(@PathVariable Integer curNum, @PathVariable Integer pageSize) {

        Integer count = mapper.getCount();

        System.out.println("count:" + count);
        List<MyTest> bylimit = mapper.getBylimit((curNum - 1) * pageSize, pageSize);

        return bylimit;
    }

    @GetMapping("/aa")
    public String aa() {
        return "aaa";
    }

}
