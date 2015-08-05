package cn.edu.xmut.modules.insured.service.impl;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.modules.insured.bean.InsuredUser;
import cn.edu.xmut.modules.insured.dao.InsuredUserDao;
import cn.edu.xmut.modules.insured.service.InsuredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lelaw on 6/13/2015.
 */
@Service("insuredUserServiceImpl")
@Transactional
public class InsuredUserServiceImpl implements InsuredUserService {
    @Autowired
    private InsuredUserDao insuredUserDao;

    @Override
    public InsuredUser save(InsuredUser insuredUser) {
        return insuredUserDao.save(insuredUser);
    }

    @Override
    public Iterable<InsuredUser> save(List<InsuredUser> insuredUsers) {
        return insuredUserDao.save(insuredUsers);
    }

    @Override
    public void deleteById(String[] ids) {
        for (String id : ids) {
            insuredUserDao.delete(id);
        }
    }

    @Override
    public void deleteById(String id) {
        insuredUserDao.delete(id);
    }

    @Override
    public InsuredUser findById(String id) {
        return insuredUserDao.findOne(id);
    }

    @Override
    public Page<InsuredUser> findPageOrderBy(Pageable pageable, String orderBy) {
        String sql = "select * from TB_INSURED_USER order by "+ orderBy;
        return insuredUserDao.findBySql(pageable,sql,InsuredUser.class);
    }

    @Override
    public Page<InsuredUser> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
        String sql = "select * from TB_INSURED_USER where "+fieldName+" = ? order by "+orderBy;
        return insuredUserDao.findBySql(pageable,sql,InsuredUser.class,fieldValue);
    }

    @Override
    public Page<InsuredUser> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
        String sql = "select * from TB_INSURED_USER where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
        return insuredUserDao.findBySql(pageable,sql,InsuredUser.class,fieldValue1,fieldValue2);
    }

    @Override
    public Page<InsuredUser> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
        String sql = "select * from TB_INSURED_USER where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
        return insuredUserDao.findBySql(pageable,sql,InsuredUser.class,fieldValue1,fieldValue2, fieldValue3);
    }

    @Override
    public InsuredUser getByOneField(String fieldName, Object fieldValue) {
        String sql = "select * from TB_INSURED_USER where "+fieldName+" = ?";
        return (InsuredUser) insuredUserDao.getBySql(sql,InsuredUser.class,fieldValue);
    }

    @Override
    public InsuredUser getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
        String sql = "select * from TB_INSURED_USER where "+fieldName1+" = ? and "+fieldName2+" = ?";
        return (InsuredUser) insuredUserDao.getBySql(sql,InsuredUser.class,fieldValue1,fieldValue2);
    }

    @Override
    public List<InsuredUser> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
        String sql = "select * from TB_INSURED_USER where "+fieldName+" = ? order by "+orderBy;
        return insuredUserDao.findBySql(sql,InsuredUser.class,fieldValue);
    }

    @Override
    public List<InsuredUser> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
        String sql = "select * from TB_INSURED_USER where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
        return insuredUserDao.findBySql(sql,InsuredUser.class,fieldValue1,fieldValue2);
    }

    @Override
    public int countByOneField(String fieldName, Object fieldValue) {
        String sql = "select count(*) as count from TB_INSURED_USER where "+fieldName+" = ?";
        return (int) insuredUserDao.countBySql(sql,InsuredUser.class,fieldValue);
    }

    @Override
    public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
        String sql = "select count(*) as count from TB_INSURED_USER where "+fieldName1+" = ? and "+fieldName2+" = ?";
        return (int) insuredUserDao.countBySql(sql,InsuredUser.class,fieldValue1,fieldValue2);
    }
}
