package com.social.service.impl;

import com.social.dao.CropCategoryMapper;
import com.social.dao.CropSpeciesMapper;
import com.social.pojo.CropCategory;
import com.social.pojo.CropSpecies;
import com.social.service.CropSpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CropSpeciesServiceImpl implements CropSpeciesService {

    @Autowired
    private  CropSpeciesMapper cropSpeciesMapper;
    @Autowired
    private CropCategoryMapper cropCategoryMapper;

    public List<CropSpecies> selectCropSpecies() {
        List<CropSpecies> cropSpecies = cropSpeciesMapper.selectCropSpecies();
        return cropSpecies;
    }

    public List<CropCategory> selectCropCategory() {
        List<CropCategory> cropCategories = cropCategoryMapper.selectAllCropCategory();
        return cropCategories;
    }

    public List<CropSpecies> selectCropSpeciesByCropCategoryId(Integer cropCategoryId) {
        List<CropSpecies> cropSpecies = cropSpeciesMapper.selectCropSpeciesByCropCategoryId(cropCategoryId);
        return cropSpecies;
    }
}
