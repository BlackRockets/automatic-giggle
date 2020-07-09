package com.social.dao;

import com.social.pojo.AddressCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AddressCity record);

    int insertSelective(AddressCity record);

    AddressCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AddressCity record);

    int updateByPrimaryKey(AddressCity record);

    List<AddressCity> selectByCode(@Param("code") String code);
}