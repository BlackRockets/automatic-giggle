package com.social.dao;

import com.social.pojo.SampleToxin;
import com.social.pojo.SampleToxinVO;

import java.util.List;

public interface SampleToxinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleToxin record);

    int insertSelective(SampleToxin record);

    SampleToxin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleToxin record);

    int updateByPrimaryKey(SampleToxin record);

    void insertAllSampleToxin(List<SampleToxin> sampleToxins);

    List<SampleToxin> selectBySampleId(Integer sampleInfoId);

    int updatebatch(SampleToxin sampleToxin);

    List<SampleToxinVO> selectGroupByToxinType();

}