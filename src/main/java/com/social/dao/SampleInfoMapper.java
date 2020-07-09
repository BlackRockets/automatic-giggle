package com.social.dao;

import com.social.pojo.SampleInfo;
import com.social.pojo.SampleInfoDTO;

import java.util.List;

public interface SampleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleInfo record);

    int insertSelective(SampleInfo record);

    SampleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleInfo record);

    int updateByPrimaryKeyWithBLOBs(SampleInfo record);

    int updateByPrimaryKey(SampleInfoDTO sampleInfoDTO);

    void insertSampleInfoDTO(SampleInfoDTO sampleInfoDTO);

    int batchDelete(String[] ids);
}