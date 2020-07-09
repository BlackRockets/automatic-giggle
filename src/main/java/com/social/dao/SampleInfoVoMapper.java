package com.social.dao;

import com.social.pojo.SampleInfoDTO;
import com.social.pojo.SampleInfoVO;
import com.social.pojo.SampleToxinInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SampleInfoVoMapper {
   List<SampleInfoVO> selectSampleInfoVO();

   List<SampleInfoVO> selectSampleToxinByLike(SampleInfoDTO sampleInfoDTO);

   Integer selectCount();

   List<SampleInfoVO> selectSampleInfoByIds(String[] ids);

   SampleInfoVO selectSampleInfoById(String id);

}