package cn.edu.xmut.modules.agegroup.service.impl;

import cn.edu.xmut.modules.agegroup.service.AgeGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-mvc.xml", "file:src/main/resources/applicationContext.xml" })
@ActiveProfiles("test")
public class AgeGroupServiceImplTest {
    @Autowired
    AgeGroupService ageGroupService;

    @Test
    public void testGetAgeGroupFee() throws Exception {
        ageGroupService.getAgeGroupFee("f89957fc4d66e1d1014d98d30835000c", 4);
    }
}