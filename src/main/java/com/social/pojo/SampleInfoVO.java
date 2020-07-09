package com.social.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;
import java.util.List;

//view Object 视图对象
public class SampleInfoVO extends BaseRowModel {

    @ExcelProperty(index = 0,value = "id")
    private Integer id;
    @ExcelProperty(index = 1,value = "样品编号")
    private String sampleId;
    @ExcelProperty(index = 6,value = "录入时间")
    private Date harvestTime;
    @ExcelProperty(index = 7,value = "取样时间")
    private Date samplingTime;
    @ExcelProperty(index = 8,value = "真菌污染率")
    private Float pollutionRate;
    @ExcelProperty(index = 2,value = "省")
    private String province;
    @ExcelProperty(index = 3,value = "市")
    private String city;
    @ExcelProperty(index = 4,value = "区")
    private String county;
    @ExcelProperty(index = 5,value = "农产品加工类型")
    private String cropSpecies;
    @ExcelProperty(index = 9,value = "主要毒素")
    private String toxinType;

    private List<SampleToxinVO> sampleToxinVOList;
    private List<BacterialStrainInfo> bacterialStrainInfoList;

    public List<SampleToxinVO> getSampleToxinVOList() {
        return sampleToxinVOList;
    }

    public void setSampleToxinVOList(List<SampleToxinVO> sampleToxinVOList) {
        this.sampleToxinVOList = sampleToxinVOList;
    }

    public List<BacterialStrainInfo> getBacterialStrainInfoList() {
        return bacterialStrainInfoList;
    }

    public void setBacterialStrainInfoList(List<BacterialStrainInfo> bacterialStrainInfoList) {
        this.bacterialStrainInfoList = bacterialStrainInfoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public Date getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(Date harvestTime) {
        this.harvestTime = harvestTime;
    }

    public Date getSamplingTime() {
        return samplingTime;
    }

    public void setSamplingTime(Date samplingTime) {
        this.samplingTime = samplingTime;
    }

    public Float getPollutionRate() {
        return pollutionRate;
    }

    public void setPollutionRate(Float pollutionRate) {
        this.pollutionRate = pollutionRate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCropSpecies() {
        return cropSpecies;
    }

    public void setCropSpecies(String cropSpecies) {
        this.cropSpecies = cropSpecies;
    }

    public String getToxinType() {
        return toxinType;
    }

    public void setToxinType(String toxinType) {
        this.toxinType = toxinType;
    }
}
