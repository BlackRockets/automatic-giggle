package com.social.pojo;

import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

public class SampleInfoDTO {
        private Integer id;
        private  String sampleId;
        private  Integer cropCategoryId;
        private  Integer breed;
        private  String pollutionRate;
        private  Float pollutionRateleft;
        private  Float pollutionRateright;
        private  String toxin;
        private  String year;
        private  String month;
        private  String day;

        private String date;
        private  String crop;
        private  String province;
        private  String city;
        private  String town;
        private  String county;
        private String township;
        private String village;
        private String household;
        private Date harvestTime;
        private Date samplingTime;
        private String samplingPeople;
        private String seasonal;
        private String description;
        private List<SampleToxin> sampleToxinList;
        private List<Integer> idList;
        private List<Integer> toxinIdList;
        private List<Float> toxinCountList;
        //产毒菌株信息
        private List<String> originalNumList;
        private List<Integer> bacterialStrainidList;

        //页码
        private Integer pageNum=1;


        public String getCounty() {
                return county;
        }

        public void setCounty(String county) {
                this.county = county;
        }

        public List<Integer> getIdList() {
                return idList;
        }

        public void setIdList(List<Integer> idList) {
                this.idList = idList;
        }

        public List<Integer> getBacterialStrainidList() {
                return bacterialStrainidList;
        }

        public void setBacterialStrainidList(List<Integer> bacterialStrainidList) {
                this.bacterialStrainidList = bacterialStrainidList;
        }

        private Integer startRow;
        private Integer pageSize;



        public Integer getPageNum() {
                return pageNum;
        }

        public void setPageNum(Integer pageNum) {
                this.pageNum = pageNum;
        }

        public List<String> getOriginalNumList() {
                return originalNumList;
        }

        public void setOriginalNumList(List<String> originalNumList) {
                this.originalNumList = originalNumList;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public List<Integer> getToxinIdList() {
                return toxinIdList;
        }

        public void setToxinIdList(List<Integer> toxinIdList) {
                this.toxinIdList = toxinIdList;
        }

        public List<Float> getToxinCountList() {
                return toxinCountList;
        }

        public void setToxinCountList(List<Float> toxinCountList) {
                this.toxinCountList = toxinCountList;
        }

        public Integer getCropCategoryId() {
                return cropCategoryId;
        }

        public void setCropCategoryId(Integer cropCategoryId) {
                this.cropCategoryId = cropCategoryId;
        }

        public Integer getBreed() {
                return breed;
        }

        public void setBreed(Integer breed) {
                this.breed = breed;
        }

        public String getTownship() {
                return township;
        }

        public void setTownship(String township) {
                this.township = township;
        }

        public String getVillage() {
                return village;
        }

        public void setVillage(String village) {
                this.village = village;
        }

        public String getHousehold() {
                return household;
        }

        public void setHousehold(String household) {
                this.household = household;
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

        public String getSamplingPeople() {
                return samplingPeople;
        }

        public void setSamplingPeople(String samplingPeople) {
                this.samplingPeople = samplingPeople;
        }

        public String getSeasonal() {
                return seasonal;
        }

        public void setSeasonal(String seasonal) {
                this.seasonal = seasonal;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public List<SampleToxin> getSampleToxinList() {
                return sampleToxinList;
        }

        public void setSampleToxinList(List<SampleToxin> sampleToxinList) {
                this.sampleToxinList = sampleToxinList;
        }

        public Integer getStartRow() {
                return startRow;
        }

        public void setStartRow(Integer startRow) {
                this.startRow = startRow;
        }

        public Integer getPageSize() {
                return pageSize;
        }

        public void setPageSize(Integer pageSize) {
                this.pageSize = pageSize;
        }

        public String getSampleId() {
                return sampleId;
        }

        public void setSampleId(String sampleId) {
                this.sampleId = sampleId;
        }

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        public String getPollutionRate() {
                return pollutionRate;
        }

        public void setPollutionRate(String pollutionRate) {
                this.pollutionRate = pollutionRate;
        }

        public Float getPollutionRateleft() {
                return pollutionRateleft;
        }

        public void setPollutionRateleft(Float pollutionRateleft) {
                this.pollutionRateleft = pollutionRateleft;
        }

        public Float getPollutionRateright() {
                return pollutionRateright;
        }

        public void setPollutionRateright(Float pollutionRateright) {
                this.pollutionRateright = pollutionRateright;
        }

        public String getToxin() {
                return toxin;
        }

        public void setToxin(String toxin) {
                this.toxin = toxin;
        }

        public String getYear() {
                return year;
        }

        public void setYear(String year) {
                this.year = year;
        }

        public String getMonth() {
                return month;
        }

        public void setMonth(String month) {
                this.month = month;
        }

        public String getDay() {
                return day;
        }

        public void setDay(String day) {
                this.day = day;
        }

        public String getCrop() {
                return crop;
        }

        public void setCrop(String crop) {
                this.crop = crop;
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

        public String getTown() {
                return town;
        }

        public void setTown(String town) {
                this.town = town;
        }
}
