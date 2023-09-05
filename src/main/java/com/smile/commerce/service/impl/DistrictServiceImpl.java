package com.smile.commerce.service.impl;

import com.smile.commerce.entity.District;
import com.smile.commerce.mapper.DistrictMapper;
import com.smile.commerce.service.IDistrictService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {
    @Resource
    private DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> list =districtMapper.findByParent(parent);
        for(District district:list){
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
