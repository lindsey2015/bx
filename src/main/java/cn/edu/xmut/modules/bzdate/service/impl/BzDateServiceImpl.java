package  cn.edu.xmut.modules.bzdate.service.impl;

import java.util.List;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.xmut.modules.bzdate.bean.BzDate;
import cn.edu.xmut.modules.bzdate.service.BzDateService;
import cn.edu.xmut.modules.bzdate.dao.BzDateDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("bzDateServiceImpl")
@Transactional
public class BzDateServiceImpl implements BzDateService{

	@Autowired
	private BzDateDao bzDateDao;
	
	@Override
	public int countAll() {
		return (int) bzDateDao.count();
	}
	
	@Override
	public BzDate save(BzDate bzDate) {
		return bzDateDao.save(bzDate);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			bzDateDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		bzDateDao.delete(id);
	}
	
	@Override
	public BzDate findById(String id) {
		return bzDateDao.findOne(id);
	}
	
	@Override
	public Page<BzDate> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_BZ_DATE order by "+orderBy;
		return bzDateDao.findBySql(pageable,sql,BzDate.class);
	}
	
	@Override
	public Page<BzDate> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_BZ_DATE where "+fieldName+" = ? order by "+orderBy;
		return bzDateDao.findBySql(pageable,sql,BzDate.class,fieldValue);
	}
	
	@Override
	public Page<BzDate> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_BZ_DATE where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return bzDateDao.findBySql(pageable,sql,BzDate.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<BzDate> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_BZ_DATE where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return bzDateDao.findBySql(pageable,sql,BzDate.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public BzDate getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_BZ_DATE where "+fieldName+" = ?";
		return (BzDate) bzDateDao.getBySql(sql,BzDate.class,fieldValue);
	}
	
	@Override
	public BzDate getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_BZ_DATE where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (BzDate) bzDateDao.getBySql(sql,BzDate.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<BzDate> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_BZ_DATE where "+fieldName+" = ? order by "+orderBy;
		return bzDateDao.findBySql(sql,BzDate.class,fieldValue);
	}
	
	@Override
	public List<BzDate> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_BZ_DATE where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return bzDateDao.findBySql(sql,BzDate.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_BZ_DATE where "+fieldName+" = ?";
		return (int) bzDateDao.countBySql(sql,BzDate.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_BZ_DATE where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) bzDateDao.countBySql(sql,BzDate.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容

	@Override
	public List<BzDate> check(int min, int max,String ageGroupId) {
		String sql ="select * from TB_BZ_DATE  where ((min_day<="+min+" and "+max+" <=max_day) or (min_day>="+min+" and "+max+" >=min_day) or (max_day>="+min+" and "+max+">=max_day)  or (min_day>="+min+" and "+max+" >=max_day)) and age_group_id='"+ageGroupId+"'";
		return bzDateDao.findBySql(sql, BzDate.class);
	}

	@Override
	public int getMin(String ageGroupId) {
		return bzDateDao.getMin(ageGroupId);
	}

	@Override
	public int getMax(String ageGroupId) {
		return bzDateDao.getMax(ageGroupId);
	}

	@Override
	public double getValue(int bzDate, String ageGroupId) {
		return bzDateDao.getValue(ageGroupId, bzDate);
	}
	
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}