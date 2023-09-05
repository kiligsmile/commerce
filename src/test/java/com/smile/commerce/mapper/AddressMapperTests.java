package com.smile.commerce.mapper;

import com.smile.commerce.entity.Address;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTests {
    @Resource
   private AddressMapper addressMapper;

   @Test
    public void insert(){
       Address address=new Address();
       address.setUid(18);
       address.setName("admin");
       address.setPhone("17858802974");
       address.setAddress("雁塔区小寨赛格");
       Integer rows=addressMapper.insert(address);
       System.out.println("rows="+rows);
   }

   @Test
    public void countByUid(){
       Integer uid=18;
       Integer count=addressMapper.countByUid(uid);
       System.out.println("count="+count);
   }

}
