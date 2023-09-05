package com.smile.commerce.controller;

import com.smile.commerce.entity.Address;
import com.smile.commerce.service.IAddressService;
import com.smile.commerce.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession httpSession){
        Integer uid = getUserIdFromSession(httpSession);
        String username = getUsernameFromSession(httpSession);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<Void>(OK);
    }

    @GetMapping({"","/"})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid=getUserIdFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }
}
