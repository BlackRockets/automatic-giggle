package com.social.dao;

import com.social.pojo.BacterialStrainInfo;

import java.util.List;

public interface BacterialStrainInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BacterialStrainInfo record);

    int insertSelective(BacterialStrainInfo record);

    BacterialStrainInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BacterialStrainInfo record);

    int updateByPrimaryKey(BacterialStrainInfo record);

    void insertBacterialStrainInfo(List<BacterialStrainInfo> list);

    List<BacterialStrainInfo> selectBacterialStrainInfoBySampleIds(Integer sampleInfoId);

    int updateBatch(BacterialStrainInfo bacterialStrainInfo);
}