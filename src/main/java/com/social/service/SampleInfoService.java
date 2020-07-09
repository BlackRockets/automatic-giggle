package com.social.service;

import com.social.common.PageUtil;
import com.social.pojo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface SampleInfoService {

    List<SampleToxinInfo> findallToxin();

    PageUtil<SampleInfoVO> findAllSampleInfoVO(SampleInfoDTO sampleInfoDTO, Integer page);

    List<SampleInfoVO> findSampleInfoByIds(String[] ids);

    void doImport(File file) throws IOException, ParseException;

    //easyExcel读取导入
    void doImport1(MultipartFile file) throws IOException;

    //新增采样信息
    void insertSampleInfo(SampleInfoDTO sampleInfoDTO, MultipartFile[] wordFile, MultipartFile[] txtFile, MultipartFile[] pictureFile, HttpServletRequest request);

    //批量删除
    void batchDeleteById(String[] ids);

    //编辑前查询
    SampleInfo editBeforeFind(String id);

    //更新数据
    void updateBatch(SampleInfoDTO sampleInfoDTO);

    //图表展示毒素信息
    List<SampleToxinVO> queryGroupByToxinType();
}
