package com.smile.commerce.service;

import com.smile.commerce.entity.Address;

import java.util.List;

public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);
    List<Address> getByUid(Integer uid);
}
