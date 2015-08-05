package cn.edu.xmut.modules.agegroup.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.core.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmut.modules.agegroup.bean.AgeGroup;
import cn.edu.xmut.modules.agegroup.service.AgeGroupService;
import cn.edu.xmut.modules.agegroup.dao.AgeGroupDao;

/**
 * @author yangzj
 * @version v1.0.0
 * @date $date
 * @since v1.0.0
 */
@Service("ageGroupServiceImpl")
@Transactional
public class AgeGroupServiceImpl implements AgeGroupService {

    @Autowired
    private AgeGroupDao ageGroupDao;

    @Override
    public int countAll() {
        return (int) ageGroupDao.count();
    }

    @Override
    public AgeGroup save(AgeGroup ageGroup) {
        if (StringUtils.isNotBlank(ageGroup.getAgeGroup())) {
            String[] ageGroups = ageGroup.getAgeGroup().split("-");
            if (ageGroups != null && ageGroups.length == 2) {
                ageGroup.setMinAge(Integer.parseInt(ageGroups[0]));
                ageGroup.setMaxAge(Integer.parseInt(ageGroups[1]));
            }
        }
        return ageGroupDao.save(ageGroup);
    }

    @Override
    public void deleteById(String[] ids) {
        for (String id : ids) {
            ageGroupDao.delete(id);
        }
    }

    @Override
    public void deleteById(String id) {
        ageGroupDao.delete(id);
    }

    @Override
    public AgeGroup findById(String id) {
        return ageGroupDao.findOne(id);
    }

    @Override
    public Page<AgeGroup> findPageOrderBy(Pageable pageable, String orderBy) {
        String sql = "select * from TB_AGE_GROUP order by " + orderBy;
        return ageGroupDao.findBySql(pageable, sql, AgeGroup.class);
    }

    @Override
    public Page<AgeGroup> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy) {
        String sql = "select * from TB_AGE_GROUP where " + fieldName + " = ? order by " + orderBy;
        return ageGroupDao.findBySql(pageable, sql, AgeGroup.class, fieldValue);
    }

    @Override
    public Page<AgeGroup> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy) {
        String sql = "select * from TB_AGE_GROUP where " + fieldName1 + " = ? and " + fieldName2 + " = ? order by " + orderBy;
        return ageGroupDao.findBySql(pageable, sql, AgeGroup.class, fieldValue1, fieldValue2);
    }

    @Override
    public Page<AgeGroup> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
        String sql = "select * from TB_AGE_GROUP where " + fieldName1 + " = ? and " + fieldName2 + " = ? and " + fieldName3 + " = ? order by " + orderBy;
        return ageGroupDao.findBySql(pageable, sql, AgeGroup.class, fieldValue1, fieldValue2, fieldValue3);
    }

    @Override
    public AgeGroup getByOneField(String fieldName, Object fieldValue) {
        String sql = "select * from TB_AGE_GROUP where " + fieldName + " = ?";
        return (AgeGroup) ageGroupDao.getBySql(sql, AgeGroup.class, fieldValue);
    }

    @Override
    public AgeGroup getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2) {
        String sql = "select * from TB_AGE_GROUP where " + fieldName1 + " = ? and " + fieldName2 + " = ?";
        return (AgeGroup) ageGroupDao.getBySql(sql, AgeGroup.class, fieldValue1, fieldValue2);
    }

    @Override
    public List<AgeGroup> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy) {
        String sql = "select * from TB_AGE_GROUP where " + fieldName + " = ? order by " + orderBy;
        return ageGroupDao.findBySql(sql, AgeGroup.class, fieldValue);
    }

    @Override
    public List<AgeGroup> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy) {
        String sql = "select * from TB_AGE_GROUP where " + fieldName1 + " = ? and " + fieldName2 + " = ? order by " + orderBy;
        return ageGroupDao.findBySql(sql, AgeGroup.class, fieldValue1, fieldValue2);
    }

    @Override
    public int countByOneField(String fieldName, Object fieldValue) {
        String sql = "select count(*) as count from TB_AGE_GROUP where " + fieldName + " = ?";
        return (int) ageGroupDao.countBySql(sql, AgeGroup.class, fieldValue);
    }

    @Override
    public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2) {
        String sql = "select count(*) as count from TB_AGE_GROUP where " + fieldName1 + " = ? and " + fieldName2 + " = ?";
        return (int) ageGroupDao.countBySql(sql, AgeGroup.class, fieldValue1, fieldValue2);
    }
    // 用户自定义开始，请不要修改以上内容
    public Map<AgeGroup, Double> getAgeGroupFee(String productId, int coverageDays) {
        Map<AgeGroup, Double>  ageGroupFeeMap = new HashMap<AgeGroup, Double>();
        List<Object> result = ageGroupDao.getProductFeeByDaysCoverage(coverageDays, productId);
        if (CollectionUtils.isEmpty(result)) {
            return ageGroupFeeMap;
        }

        for (Object o : result) {
            if (o instanceof Object[]) {
                Object[] oa = (Object[]) o;
                AgeGroup ageGroup = new AgeGroup();
                ageGroup.setAgeGroup((String) oa[0]);
                ageGroup.setMinAge((Integer) oa[1]);
                ageGroup.setMaxAge((Integer) oa[2]);
                Double fee = (Double) oa[3];
                ageGroupFeeMap.put(ageGroup, fee);
            }
        }
        return ageGroupFeeMap;
    }


    // 用户自定义结束，请不要修改以下内容
}