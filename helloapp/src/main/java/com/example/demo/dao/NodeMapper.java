package com.example.demo.dao;

import com.example.demo.model.Node;

public interface NodeMapper {
    int deleteByPrimaryKey(String nodeId);

    int insert(Node record);

    int insertSelective(Node record);

    Node selectByPrimaryKey(String nodeId);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKeyWithBLOBs(Node record);

    int updateByPrimaryKey(Node record);
}