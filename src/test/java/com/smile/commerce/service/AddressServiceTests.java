package com.smile.commerce.service;

import com.smile.commerce.entity.Address;
import com.smile.commerce.service.ex.ServiceException;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests {
    @Resource
    private IAddressService addressService;
    @Test
    public void addNewAddress(){
        try{
            Integer uid=20;
            String username="管理员";
            Address address=new Address();
            address.setName("张三");
            address.setPhone("19888992333");
            address.setAddress("雁塔区小寨华旗");
            addressService.addNewAddress(uid,username,address);
            System.out.println("ok");
        }catch (ServiceException e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
