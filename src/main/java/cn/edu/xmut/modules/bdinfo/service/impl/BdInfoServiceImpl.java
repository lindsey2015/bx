package cn.edu.xmut.modules.bdinfo.service.impl;

import cn.edu.xmut.modules.catagory.bean.Catagory;
import cn.edu.xmut.modules.catagory.service.CatagoryService;
import cn.edu.xmut.modules.product.bean.Product;
import cn.edu.xmut.modules.product.service.ProductService;
import org.joda.time.DateTime;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.modules.agegroup.bean.AgeGroup;
import cn.edu.xmut.modules.agegroup.service.AgeGroupService;
import cn.edu.xmut.modules.bdinfo.bean.BdInfo;
import cn.edu.xmut.modules.bdinfo.dao.BdInfoDao;
import cn.edu.xmut.modules.bdinfo.service.BdInfoService;
import cn.edu.xmut.modules.insured.bean.InsuredUser;
import cn.edu.xmut.modules.insured.service.InsuredUserService;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @author yangzj
 * @version v1.0.0
 * @since v1.0.0
 */
@Service("bdInfoServiceImpl")
@Transactional
public class BdInfoServiceImpl implements BdInfoService {
    public static final String BD_NUMBER_PREFIX = "AXIMC01E0615B";
    public static final String TB_NUMBER_PREFIX = "AXIMC01A";
    public static final String SEQUENCE_FORMAT = "000000";
    public static final String ALL_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Autowired
    private BdInfoDao bdInfoDao;
    @Autowired
    private InsuredUserService insuredUserService;
    @Autowired
    private AgeGroupService ageGroupService;
    @Autowired
    public ProductService productService;
    @Autowired
    public CatagoryService catagoryService;

    @Override
    public int countAll() {
        return (int) bdInfoDao.count();
    }

    @Override
    public BdInfo save(BdInfo bdInfo) {
        bdInfo = bdInfoDao.save(bdInfo);
        List<InsuredUser> insuredUsers = bdInfo.getInsuredUserList();
        if (CollectionUtils.isNotEmpty(insuredUsers)) {
            for (InsuredUser insuredUser : insuredUsers) {
                insuredUser.setBdId(bdInfo.getId());
            }
            insuredUserService.save(insuredUsers);
        }
        return bdInfo;
    }

    @Override
    public void deleteById(String[] ids) {
        for (String id : ids) {
            bdInfoDao.delete(id);
        }
    }

    @Override
    public void deleteById(String id) {
        bdInfoDao.delete(id);
    }

    @Override
    public BdInfo findById(String id) {
        BdInfo bdInfo = bdInfoDao.findOne(id);
        List<InsuredUser> insuredUserList = insuredUserService.findByOneFieldOrderBy("bd_id", id, "name");
        bdInfo.setInsuredUserList(insuredUserList);
        return bdInfo;
    }

    @Override
    public Page<BdInfo> findPageOrderBy(Pageable pageable, String orderBy) {
        String sql = "select * from TB_BD_INFO order by " + orderBy;
        return bdInfoDao.findBySql(pageable, sql, BdInfo.class);
    }

    @Override
    public Page<BdInfo> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy) {
        String sql = "select * from TB_BD_INFO where " + fieldName + " = ? order by " + orderBy;
        return bdInfoDao.findBySql(pageable, sql, BdInfo.class, fieldValue);
    }

    @Override
    public Page<BdInfo> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy) {
        String sql = "select * from TB_BD_INFO where " + fieldName1 + " = ? and " + fieldName2 + " = ? order by " + orderBy;
        return bdInfoDao.findBySql(pageable, sql, BdInfo.class, fieldValue1, fieldValue2);
    }

    @Override
    public Page<BdInfo> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
        String sql = "select * from TB_BD_INFO where " + fieldName1 + " = ? and " + fieldName2 + " = ? and " + fieldName3 + " = ? order by " + orderBy;
        return bdInfoDao.findBySql(pageable, sql, BdInfo.class, fieldValue1, fieldValue2, fieldValue3);
    }

    @Override
    public BdInfo getByOneField(String fieldName, Object fieldValue) {
        String sql = "select * from TB_BD_INFO where " + fieldName + " = ?";
        return (BdInfo) bdInfoDao.getBySql(sql, BdInfo.class, fieldValue);
    }

    @Override
    public BdInfo getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2) {
        String sql = "select * from TB_BD_INFO where " + fieldName1 + " = ? and " + fieldName2 + " = ?";
        return (BdInfo) bdInfoDao.getBySql(sql, BdInfo.class, fieldValue1, fieldValue2);
    }

    @Override
    public List<BdInfo> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy) {
        String sql = "select * from TB_BD_INFO where " + fieldName + " = ? order by " + orderBy;
        return bdInfoDao.findBySql(sql, BdInfo.class, fieldValue);
    }

    @Override
    public List<BdInfo> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy) {
        String sql = "select * from TB_BD_INFO where " + fieldName1 + " = ? and " + fieldName2 + " = ? order by " + orderBy;
        return bdInfoDao.findBySql(sql, BdInfo.class, fieldValue1, fieldValue2);
    }

    @Override
    public int countByOneField(String fieldName, Object fieldValue) {
        String sql = "select count(*) as count from TB_BD_INFO where " + fieldName + " = ?";
        return (int) bdInfoDao.countBySql(sql, BdInfo.class, fieldValue);
    }

    @Override
    public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2) {
        String sql = "select count(*) as count from TB_BD_INFO where " + fieldName1 + " = ? and " + fieldName2 + " = ?";
        return (int) bdInfoDao.countBySql(sql, BdInfo.class, fieldValue1, fieldValue2);
    }

    // 用户自定义开始，请不要修改以上内容
    @Override
    public Page<BdInfo> findPageByTwoFieldsLike(Pageable pageable, String fieldName1, int status, String fieldName2, String searchParam) {
        String sql = "select * from TB_BD_INFO where " + fieldName1 + "=" + status + " and " + fieldName2 + " LIKE '%" + searchParam + "%'";
        return bdInfoDao.findBySql(pageable, sql, BdInfo.class);
    }

    @Override
    public int getMaxNo() {
        return bdInfoDao.getMax();
    }

    @Override
    public Page<BdInfo> findPageByTwoFields(Pageable pageable, String fieldName1, int status, String fieldName2,
                                            String startTime, String fieldName3, String endTime) {
        String sql = "select * from TB_BD_INFO where " + fieldName2 + " >= ? and " + fieldName3 + " <= ? and " + fieldName1 + " =?";
        return bdInfoDao.findBySql(pageable, sql, BdInfo.class, startTime, endTime, status);
    }

    @Override
    public List<BdInfo> listOrderBy(String name, String startTime,
                                    String name2, String endTime, String name3, int status,
                                    String string) {
        String sql = "select * from TB_BD_INFO where " + name + " >= ? and " + name2 + " <= ? and " + name3 + " =? ORDER BY " + string;
        return bdInfoDao.findBySql(sql, BdInfo.class, startTime, endTime, status);
    }

    @Override
    public double getActualTotalFee(BdInfo bdInfo) {
        List<InsuredUser> insuredUserList = bdInfo.getInsuredUserList();
        if (CollectionUtils.isEmpty(insuredUserList)) {
            return 0;
        }
        Map<AgeGroup, Double> ageGroupFeeMap = ageGroupService.getAgeGroupFee(bdInfo.getProduct().getId(), bdInfo.getDays());
        DateTime startDate = DateTime.parse(bdInfo.getStartDay(), DateTimeFormat.forPattern("yyyy-MM-dd"));
        double total = 0;
        for (InsuredUser insuredUser : insuredUserList) {
            DateTime birthday = DateTime.parse(insuredUser.getBirthday(), DateTimeFormat.forPattern("yyyyMMdd"));
            int age = Years.yearsBetween(birthday, startDate).getYears();
            total += getFee(ageGroupFeeMap, age);
        }
        return total;
    }

    private double getFee(Map<AgeGroup, Double> ageGroupFeeMap, int age) {
        for (AgeGroup ageGroup : ageGroupFeeMap.keySet()) {
            if (ageGroup.getMinAge() <= age && age <= ageGroup.getMaxAge()) {
                return ageGroupFeeMap.get(ageGroup);
            }
        }
        return 0.0;
    }

    @Override
    public String generateBdNumber(BdInfo bdInfo) {
        StringBuffer tbNo = new StringBuffer();
        tbNo.append(BD_NUMBER_PREFIX);
        tbNo.append(getFormatedSequence(bdInfo.getNo()));
        tbNo.append(getRandomChar());
        return tbNo.toString();
    }

    @Override
    public String generateTbNumber(BdInfo bdInfo) {
        StringBuffer tbNo = new StringBuffer();
        tbNo.append(TB_NUMBER_PREFIX);

        Product product = productService.findById(bdInfo.getProduct().getId());
        Catagory category = catagoryService.getByOneField(Catagory.FieldOfCatagory.ID.name(), product.getCatagoryId());
        tbNo.append(category.getNameE() + "15P");

        tbNo.append(getFormatedSequence(bdInfo.getNo()));
        tbNo.append(getRandomChar());
        return tbNo.toString();
    }

    private String getFormatedSequence(int sequence) {
        DecimalFormat df = new DecimalFormat(SEQUENCE_FORMAT);
        return df.format(sequence);
    }

    @Override
    public int getSequence() {
        Integer maxNo = getMaxNo();
        int count;
        if (maxNo == null) {
            count = 1;
        } else {
            count = maxNo + 1;
        }
        return count;
    }

    private char getRandomChar() {
        String chars = ALL_CHARACTERS;
        return chars.charAt((int) (Math.random() * 26));
    }

    // 用户自定义结束，请不要修改以下内容
}