package cn.edu.xmut.modules.bdinfo.service.impl;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.modules.bdinfo.bean.BdInfo;
import cn.edu.xmut.modules.bdinfo.bean.SearchCriteria;
import cn.edu.xmut.modules.bdinfo.service.BdInfoService;
import cn.edu.xmut.modules.insured.bean.InsuredUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-mvc.xml", "file:src/main/resources/applicationContext.xml" })
@ActiveProfiles("test")
public class BdInfoServiceImplTest {
    @Autowired
    BdInfoService bdInfoService;

    @Test
    public void testSave() {
        BdInfo bdInfo = new BdInfo();
        bdInfo.setAgeGroup("1-80");
        bdInfo.setBdNo("AXIMC01E0615B000038I");
        bdInfo.setCreateTime(new Date().toString());
        bdInfo.setDays(3);
        bdInfo.setExcelAddr("/bx/upload/excels/20150612091931735705.xls");
        bdInfo.setNo(5);
        bdInfo.setNums(1);
        bdInfo.setPdfAddr("/bx/upload/pdfs/20150612092254973597.pdf");
//        bdInfo.setProductId("f89957fc4d66e1d1014d98d30835000c");
        bdInfo.setStartDay("2015-06-18");
        bdInfo.setStatus(3);
        bdInfo.setTbNo("AXIMC01AZJ15P000005N");
//        bdInfo.setUserId("402881e84dd856b3014dd8592c3e0000");
        bdInfo.setTotal(12);

        List<InsuredUser> insuredUsers = new ArrayList<InsuredUser>();
        InsuredUser insuredUser = new InsuredUser();
        insuredUser.setName("张三");
        insuredUser.setIdentityType(1);
        insuredUser.setIdentity("345678198104052345");
        insuredUser.setBirthday("19810405");
        insuredUser.setOccupationType(1);
        insuredUsers.add(insuredUser);
        bdInfo.setInsuredUserList(insuredUsers);

        bdInfoService.save(bdInfo);
    }

    @Test
    public void testFindById() {
        BdInfo bdInfo = bdInfoService.findById("402881e84e204283014e209d879c0000");
        System.out.println(bdInfo.getInsuredUserList().size());
    }

    @Test
    public void testFindPageBySearchCriteria4CarrierUser() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setStatus(3);
        searchCriteria.setStartDate("2015-05-01");
        searchCriteria.setEndDate("2015-08-28");
        String[] productIds = {"f89957fc4d66e1d1014d98d30835000c", "f89957fc4d66e1d1014d6b3f138a0002"};
        searchCriteria.setProductIds(Arrays.asList(productIds));
        Pageable pageable = new Pageable();
        pageable.setPageNumber(1);
        pageable.setPageSize(10);
        searchCriteria.setPageable(pageable);

        Page<BdInfo> result = bdInfoService.findPageBySearchCriteria(searchCriteria);
        Assert.assertNotNull(result);
        Assert.assertEquals(156, result.getTotal());
    }

    @Test
    public void testFindPageBySearchCriteria4NormalUser() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setStatus(3);
        searchCriteria.setStartDate("2015-05-01");
        searchCriteria.setEndDate("2015-08-28");
        searchCriteria.setUserId("5746353");
        Pageable pageable = new Pageable();
        pageable.setPageNumber(1);
        pageable.setPageSize(10);
        searchCriteria.setPageable(pageable);

        Page<BdInfo> result = bdInfoService.findPageBySearchCriteria(searchCriteria);
        Assert.assertNotNull(result);
    }

    @Test
    public void testStringFormat() {
        String s = String.format("%%%s%%", "test");
        Assert.assertEquals("%test%", s);
    }

    @Test
    public void testGetByBdNoAndInsuredUserName() {
        BdInfo bdInfo = bdInfoService.getByBdNoAndInsuredUserName("AXIMC01E0615B000156E", "程海桐");
        Assert.assertNotNull(bdInfo);
    }
}