package com.social.dao;

import com.social.pojo.SampleToxinInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SampleToxinInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleToxinInfo record);

    int insertSelective(SampleToxinInfo record);

    SampleToxinInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleToxinInfo record);

    int updateByPrimaryKey(SampleToxinInfo record);

    List<SampleToxinInfo> selectToxinAll();

    List<SampleToxinInfo>  getsampleToxinInfobySampleInfoId(@Param("sampleInfoId") Integer sampleInfoId, @Param("toxinType") String toxinType);

    int getToxinId(String toxinType);


}