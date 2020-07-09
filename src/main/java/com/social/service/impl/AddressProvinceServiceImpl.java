package com.social.service.impl;

import com.social.dao.AddressCityMapper;
import com.social.dao.AddressProvinceMapper;
import com.social.dao.AddressTownMapper;
import com.social.pojo.AddressCity;
import com.social.pojo.AddressProvince;
import com.social.pojo.AddressTown;
import com.social.service.AddressProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressProvinceServiceImpl implements AddressProvinceService  {
    @Autowired
    private AddressProvinceMapper addressProvinceMapper;
    @Autowired
    private AddressCityMapper addressCityMapper;
    @Autowired
    private AddressTownMapper addressTownMapper;
    public List<AddressProvince> selectAddressProvince() {
        return addressProvinceMapper.selectProvince();
    }

    public List<AddressCity> selectAddressCityByCode(String code) {
        return addressCityMapper.selectByCode(code);
    }

    public List<AddressTown> selectAddressTownByCode(String code) {
        return addressTownMapper.selectByCode(code);
    }
}
