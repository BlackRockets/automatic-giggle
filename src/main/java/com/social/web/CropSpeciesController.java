package com.social.web;

import com.alibaba.fastjson.JSON;
import com.social.pojo.CropCategory;
import com.social.pojo.CropSpecies;
import com.social.service.CropSpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CropSpeciesController {

    @Autowired
    private CropSpeciesService cropSpeciesService;

    @ResponseBody
    @RequestMapping(value = "selectCropList",produces = {"application/json;charset=utf-8"})
    public String selectCropList(){
        List<CropSpecies> cropSpecies = cropSpeciesService.selectCropSpecies();
        String jsonString = JSON.toJSONString(cropSpecies);
        return jsonString;
    }

    @ResponseBody
    @RequestMapping(value = "findAllCropCategory",produces = {"application/json;charset=utf-8"})
    public String findAllCropCategory(){
        List<CropCategory> cropCategories = cropSpeciesService.selectCropCategory();
        String jsonString = JSON.toJSONString(cropCategories);
        return jsonString;
    }

    //根据农产品类别查询品种
    @ResponseBody
    @RequestMapping(value = "findCropSpeciesByCropCategoryId",produces = {"application/json;charset=utf-8"})
    public String findCropSpeciesByCropCategoryId(Integer cropCategoryId){
        List<CropSpecies> cropSpecies = cropSpeciesService.selectCropSpeciesByCropCategoryId(cropCategoryId);
        return JSON.toJSONString(cropSpecies);
    }
}
