package  cn.edu.xmut.modules.catagory.service.impl;

import java.util.List;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.xmut.modules.catagory.bean.Catagory;
import cn.edu.xmut.modules.catagory.service.CatagoryService;
import cn.edu.xmut.modules.catagory.dao.CatagoryDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("catagoryServiceImpl")
@Transactional
public class CatagoryServiceImpl implements CatagoryService{

	@Autowired
	private CatagoryDao catagoryDao;
	
	@Override
	public int countAll() {
		return (int) catagoryDao.count();
	}
	
	@Override
	public Catagory save(Catagory catagory) {
		return catagoryDao.save(catagory);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			catagoryDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		catagoryDao.delete(id);
	}
	
	@Override
	public Catagory findById(String id) {
		return catagoryDao.findOne(id);
	}
	
	@Override
	public Page<Catagory> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_CATAGORY order by "+orderBy;
		return catagoryDao.findBySql(pageable,sql,Catagory.class);
	}
	
	@Override
	public Page<Catagory> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_CATAGORY where "+fieldName+" = ? order by "+orderBy;
		return catagoryDao.findBySql(pageable,sql,Catagory.class,fieldValue);
	}
	
	@Override
	public Page<Catagory> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_CATAGORY where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return catagoryDao.findBySql(pageable,sql,Catagory.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<Catagory> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_CATAGORY where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return catagoryDao.findBySql(pageable,sql,Catagory.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public Catagory getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_CATAGORY where "+fieldName+" = ?";
		return (Catagory) catagoryDao.getBySql(sql,Catagory.class,fieldValue);
	}
	
	@Override
	public Catagory getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_CATAGORY where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (Catagory) catagoryDao.getBySql(sql,Catagory.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<Catagory> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_CATAGORY where "+fieldName+" = ? order by "+orderBy;
		return catagoryDao.findBySql(sql,Catagory.class,fieldValue);
	}
	
	@Override
	public List<Catagory> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_CATAGORY where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return catagoryDao.findBySql(sql,Catagory.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_CATAGORY where "+fieldName+" = ?";
		return (int) catagoryDao.countBySql(sql,Catagory.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_CATAGORY where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) catagoryDao.countBySql(sql,Catagory.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容

	@Override
	public Page<Catagory> findPageByOneFieldLike(Pageable pageable,
			String name, String searchParam) {
		String sql = "select * from TB_CATAGORY where "+name+" LIKE '%"+searchParam+"%'";
		return catagoryDao.findBySql(pageable,sql,Catagory.class);
	}
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}