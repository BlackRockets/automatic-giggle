package com.social.service;

import com.social.pojo.CropCategory;
import com.social.pojo.CropSpecies;

import java.util.List;

public interface CropSpeciesService {
    List<CropSpecies> selectCropSpecies();

    List<CropCategory> selectCropCategory();

    List<CropSpecies> selectCropSpeciesByCropCategoryId(Integer cropCategoryId);

}
