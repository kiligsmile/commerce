package com.smile.commerce.mapper;

import com.smile.commerce.entity.District;

import java.util.List;

public interface DistrictMapper {
    List<District> findByParent(String parent);
    String findNameByCode(String code);
}
