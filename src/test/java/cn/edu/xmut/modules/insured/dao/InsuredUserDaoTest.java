package cn.edu.xmut.modules.insured.dao;

import cn.edu.xmut.modules.insured.bean.InsuredUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-mvc.xml", "file:src/main/resources/applicationContext.xml" })
@ActiveProfiles("test")
public class InsuredUserDaoTest {
    @Autowired
    InsuredUserDao insuredUserDao;

    @Test
    public void testSaveInsuredUser() {
        InsuredUser insuredUser = new InsuredUser();
        insuredUser.setName("战三");
        insuredUser.setIdentityType(1);
        insuredUser.setIdentity("123456198806091234");
        insuredUser.setBdId("402881e84de7dc76014de7ed7a220009");

        insuredUserDao.save(insuredUser);
    }
}