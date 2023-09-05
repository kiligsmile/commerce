package com.smile.commerce.service;

import com.smile.commerce.entity.District;

import java.util.List;

public interface IDistrictService {
    List<District> getByParent(String parent);
    String getNameByCode(String code);
}
