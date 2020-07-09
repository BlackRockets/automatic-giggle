package com.social.service;

import com.social.pojo.AddressCity;
import com.social.pojo.AddressProvince;
import com.social.pojo.AddressTown;

import java.util.List;

public interface AddressProvinceService {
    List<AddressProvince> selectAddressProvince();

    List<AddressCity> selectAddressCityByCode(String code);

    List<AddressTown> selectAddressTownByCode(String code);
}
