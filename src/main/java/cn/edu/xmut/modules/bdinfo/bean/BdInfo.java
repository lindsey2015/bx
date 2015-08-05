package cn.edu.xmut.modules.bdinfo.bean;

import javax.persistence.*;
import javax.validation.constraints.Size;

import cn.edu.xmut.modules.insured.bean.InsuredUser;
import cn.edu.xmut.modules.product.bean.Product;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.userinfo.bean.UserInfo;
import org.hibernate.validator.constraints.NotEmpty;

import cn.edu.xmut.core.entity.IdEntity;

import java.util.List;

@Entity
@Table(name = "tb_bd_info")
public class BdInfo extends IdEntity {

    // 自动生成区域开始
    public static enum FieldOfBdInfo {
        ID,
        TB_USER_INFO_ID,
        TB_PRODUCT_ID,
        START_DAY,
        EXCEL_ADDR,
        TOTAL,
        STATUS,
        DAYS,
        NUMS,
        AGE_GROUP,
        CREATE_TIME,
        TB_NO,
        BD_NO,
        PDF_ADDR,
        NO,
    }
    // 自动生成区域结束

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @NotEmpty
    @Size(max = 25)
    private String startDay;
    private String excelAddr;
    private double total;
    private int status;
    private int no;
    private int days;
    private int nums;
    @NotEmpty
    @Size(max = 25)
    private String ageGroup;
    private String createTime;
    private String tbNo;
    private String bdNo;
    private String pdfAddr;

    @Transient
    private List<InsuredUser> insuredUserList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getExcelAddr() {
        return excelAddr;
    }

    public void setExcelAddr(String excelAddr) {
        this.excelAddr = excelAddr;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTbNo() {
        return tbNo;
    }

    public void setTbNo(String tbNo) {
        this.tbNo = tbNo;
    }

    public String getBdNo() {
        return bdNo;
    }

    public void setBdNo(String bdNo) {
        this.bdNo = bdNo;
    }

    public String getPdfAddr() {
        return pdfAddr;
    }

    public void setPdfAddr(String pdfAddr) {
        this.pdfAddr = pdfAddr;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Transient
    public List<InsuredUser> getInsuredUserList() {
        return insuredUserList;
    }

    @Transient
    public void setInsuredUserList(List<InsuredUser> insuredUserList) {
        this.insuredUserList = insuredUserList;
    }
}
