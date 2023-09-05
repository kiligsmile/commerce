package com.smile.commerce.service;

import com.smile.commerce.entity.District;
import com.smile.commerce.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTests {
    @Autowired
    private IDistrictService districtService;
    @Test
    public void getByParent(){
        try{
            String parent="86";
            List<District> list=districtService.getByParent(parent);
            System.out.println("count="+list.size());
            for(District item:list){
                System.out.println(list);
            }
        }catch (ServiceException e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
