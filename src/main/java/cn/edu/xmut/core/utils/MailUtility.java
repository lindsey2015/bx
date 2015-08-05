package cn.edu.xmut.core.utils;

import cn.edu.xmut.modules.bdinfo.bean.BdInfo;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

@Component
public class MailUtility {
    @Value("${mail.smtp.host}")
    private String emailHost;

    @Value("${mail.smtp.port}")
    private int port;

    @Value("${mail.username}")
    private String from;

    @Value("${mail.username}")
    private String userName;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.to}")
    private String to;

    public void sendSuccessNotification(BdInfo bdInfo) {
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setSubject(String.format("您投保的%s产品已投保成功，保险单号：%s", bdInfo.getProduct().getName(), bdInfo.getBdNo()));
        String mailContent = "<p>尊敬的客户：<br/>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;您在%s投保的%s产品，已经于%s投保成功。保险单号%s。保单详细信息请登录保险云平台查询。" +
                "感谢您对太平洋保险云平台的支持！欢迎拨打我司售后服务热线0592-2689522.</p>";
        DateTime createdDate = DateTime.parse(bdInfo.getCreateTime(), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));

        mail.setContent(String.format(mailContent,
                createdDate.toString("yyyy年MM月dd日", Locale.CHINESE),
                bdInfo.getProduct().getName(),
                new DateTime().toString("yyyy年MM月dd日", Locale.CHINESE),
                bdInfo.getBdNo()));

        sendEmail(mail);
    }

    public void sendEmail(Mail mail) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        senderImpl.setHost(emailHost);
        senderImpl.setPort(port);

        MimeMessage mailMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
            messageHelper.setFrom(from);
            String[] toEmailArray = mail.getTo().split(";");
            if (toEmailArray == null || toEmailArray.length <= 0) {
                throw new RuntimeException("收件人邮箱不得为空！");
            }
            messageHelper.setTo(toEmailArray);
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getContent(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.timeout", "25000");
        prop.put("mail.smtp.ssl.enable", true);
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        senderImpl.setSession(session);

        // 发送邮件
        senderImpl.send(mailMessage);
    }

    public static class Mail {
        private String to;
        private String subject;
        private String content;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
