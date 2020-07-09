package com.social.dao;

import com.social.pojo.AddressTown;

import java.util.List;

public interface AddressTownMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AddressTown record);

    int insertSelective(AddressTown record);

    AddressTown selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AddressTown record);

    int updateByPrimaryKey(AddressTown record);

    List<AddressTown> selectByCode(String code);
}