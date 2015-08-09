package cn.edu.xmut.modules.bdinfo.dao;

import cn.edu.xmut.modules.bdinfo.bean.BdInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-mvc.xml", "file:src/main/resources/applicationContext.xml" })
@ActiveProfiles("test")
public class BdInfoDaoImplTest {
    @Autowired
    BdInfoDao bdInfoDao;

    @Test
    public void testGetBdInfo() {
        BdInfo bdInfo = bdInfoDao.findOne("402880eb4e0fceec014e1007d49a0000");
        assertNotNull(bdInfo.getUser());
        assertEquals("402881e84dd856b3014dd8592c3e0000", bdInfo.getUser().getId());
        assertNotNull(bdInfo.getProduct());
        assertEquals("f89957fc4d66e1d1014d98d30835000c", bdInfo.getProduct().getId());
        assertNotNull(bdInfo.getUserInfo());
        assertEquals("402881e84dd856b3014dd85967200001", bdInfo.getUserInfo().getId());
    }

    @Test
    public void getMaxNo() {
        Integer max = bdInfoDao.getMax();
        assertNull(max);
    }

    @Test
    public void testDecimalFormat() {
        DecimalFormat df = new DecimalFormat("000000");
        assertEquals("000015", df.format(15));
    }

    @Test
    public void randomChar() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println(chars.charAt((int)(Math.random() * 26)));
    }

    @Test
    public void testGetByBdNoBbxrName() {
        String bdId = bdInfoDao.getIdByBdNoAndInsuredUserName("AXIMC01E0615B000156E", "程海桐");
        assertNotNull(bdId);
    }
}