package com.example.demo.dao;

import com.example.demo.model.MyTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyTestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MyTest record);

    int insertSelective(MyTest record);

    MyTest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MyTest record);

    int updateByPrimaryKey(MyTest record);

    List<MyTest> getAll();

    Integer getCount();

    List<MyTest> getBylimit(@Param("pageNumber") Integer pageNumber,@Param("pageSize") Integer pageSize);
}