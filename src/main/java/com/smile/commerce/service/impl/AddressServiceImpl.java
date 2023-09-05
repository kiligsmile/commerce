package com.smile.commerce.service.impl;

import com.smile.commerce.entity.Address;
import com.smile.commerce.mapper.AddressMapper;
import com.smile.commerce.service.IAddressService;
import com.smile.commerce.service.IDistrictService;
import com.smile.commerce.service.ex.AddressCountLimitException;
import com.smile.commerce.service.ex.InsertException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Resource
    private AddressMapper addressMapper;
    @Value("${user.address.max-count}")
    private int maxCount;
    @Resource
    private IDistrictService districtService;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count=addressMapper.countByUid(uid);
        if(count>maxCount){
            throw new AddressCountLimitException("收货地址数量已经到达上限（"+maxCount+")!");
        }
        address.setUid(uid);
        Integer isDefault=count==0?1:0;
        address.setIsDefault(isDefault);
        Date now = new Date();
        address.setCreatedUser(username);
        address.setCreatedTime(now);
        address.setModifiedUser(username);
        address.setModifiedTime(now);
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceCode(provinceName);
        address.setCityName(cityName);
        address.setAddress(areaName);
        Integer rows = addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("插入收获地址时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list=addressMapper.findByUid(uid);
        for (Address address : list) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return list;
    }
}
