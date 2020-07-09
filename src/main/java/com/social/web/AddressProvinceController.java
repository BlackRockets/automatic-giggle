package com.social.web;

import com.alibaba.fastjson.JSON;
import com.social.pojo.AddressCity;
import com.social.pojo.AddressProvince;
import com.social.pojo.AddressTown;
import com.social.service.AddressProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AddressProvinceController {
    @Autowired
    private AddressProvinceService addressProvinceService;

    @RequestMapping(value = "getAddress",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getAddress(){
        List<AddressProvince> addressProvinces = addressProvinceService.selectAddressProvince();
        return JSON.toJSONString(addressProvinces);
    }

    @RequestMapping(value = "getCity",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getCity(String code){
        List<AddressCity> addressCities = addressProvinceService.selectAddressCityByCode(code);
        return JSON.toJSONString(addressCities);
    }

    @RequestMapping(value = "getTown",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getTown(String code){
        List<AddressTown> addressTowns = addressProvinceService.selectAddressTownByCode(code);
        return JSON.toJSONString(addressTowns);
    }
}
