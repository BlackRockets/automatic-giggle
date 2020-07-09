package com.social.dao;

import com.social.pojo.CropSpecies;
import org.apache.ibatis.annotations.Param;
import sun.rmi.log.LogInputStream;

import java.util.List;

public interface CropSpeciesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CropSpecies record);

    int insertSelective(CropSpecies record);

    CropSpecies selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CropSpecies record);

    int updateByPrimaryKey(CropSpecies record);

    List<CropSpecies> selectCropSpecies();

    Integer selectCropCategoryIdByCropSpecies(@Param("cropSpecies") String cropSpecies);

    List<CropSpecies> selectCropSpeciesByCropCategoryId(Integer cropCategoryId);

    String selectByCropCategoryId(Integer breed);

 }