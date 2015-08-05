package cn.edu.xmut.core.utils;

import cn.edu.xmut.modules.bdinfo.bean.BdInfo;
import cn.edu.xmut.modules.product.bean.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-mvc.xml", "file:src/main/resources/applicationContext.xml" })
@ActiveProfiles("test")
public class MailUtilityTest {
    @Autowired
    MailUtility mailUtility;

    @Test
    public void test1() throws Exception {
        MailUtility.Mail mail = new MailUtility.Mail();
        mail.setTo("943238432@qq.com");
        mail.setSubject("第一封，简单文本邮件");
        mail.setContent("我相信天上不会掉馅饼");

        mailUtility.sendEmail(mail);
    }

    @Test
    public void testSendSuccessMsg() {
        BdInfo bdInfo = new BdInfo();
        bdInfo.setCreateTime("2012-07-10 10:09:11");
        bdInfo.setBdNo("AXIMC01E0615B000014G");
        Product product = new Product();
        product.setName("太平洋户外之旅-图途专享");
        bdInfo.setProduct(product);
        mailUtility.sendSuccessNotification(bdInfo);
    }

}