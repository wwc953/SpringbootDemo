package com.example.demo.dao;

import com.example.demo.model.MyTest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyTestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MyTest record);

    int insertSelective(MyTest record);

    MyTest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MyTest record);

    int updateByPrimaryKey(MyTest record);
}