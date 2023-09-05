package com.smile.commerce.mapper;

import com.smile.commerce.entity.District;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTests {
    @Resource
    private DistrictMapper districtMapper;
    @Test
    public void findByParent(){
        String parent="110100";
        List<District> list=districtMapper.findByParent(parent);
        System.out.println("count="+list.size());
        for(District district:list){
            System.out.println(district);
        }
    }
}
