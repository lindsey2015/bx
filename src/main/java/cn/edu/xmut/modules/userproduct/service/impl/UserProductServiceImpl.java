package  cn.edu.xmut.modules.userproduct.service.impl;

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmut.modules.userproduct.bean.UserProduct;
import cn.edu.xmut.modules.userproduct.service.UserProductService;
import cn.edu.xmut.modules.userproduct.dao.UserProductDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("userProductServiceImpl")
@Transactional
public class UserProductServiceImpl implements UserProductService{

	@Autowired
	private UserProductDao userProductDao;
	
	@Override
	public int countAll() {
		return (int) userProductDao.count();
	}
	
	@Override
	public UserProduct save(UserProduct userProduct) {
		return userProductDao.save(userProduct);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			userProductDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		userProductDao.delete(id);
	}
	
	@Override
	public UserProduct findById(String id) {
		return userProductDao.findOne(id);
	}
	
	@Override
	public Page<UserProduct> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_USER_PRODUCT order by "+orderBy;
		return userProductDao.findBySql(pageable,sql,UserProduct.class);
	}
	
	@Override
	public Page<UserProduct> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_USER_PRODUCT where "+fieldName+" = ? order by "+orderBy;
		return userProductDao.findBySql(pageable,sql,UserProduct.class,fieldValue);
	}
	
	@Override
	public Page<UserProduct> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_USER_PRODUCT where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return userProductDao.findBySql(pageable,sql,UserProduct.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<UserProduct> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_USER_PRODUCT where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return userProductDao.findBySql(pageable,sql,UserProduct.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public UserProduct getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_USER_PRODUCT where "+fieldName+" = ?";
		return (UserProduct) userProductDao.getBySql(sql,UserProduct.class,fieldValue);
	}
	
	@Override
	public UserProduct getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_USER_PRODUCT where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (UserProduct) userProductDao.getBySql(sql,UserProduct.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<UserProduct> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_USER_PRODUCT where "+fieldName+" = ? order by "+orderBy;
		return userProductDao.findBySql(sql,UserProduct.class,fieldValue);
	}
	
	@Override
	public List<UserProduct> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_USER_PRODUCT where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return userProductDao.findBySql(sql,UserProduct.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_USER_PRODUCT where "+fieldName+" = ?";
		return (int) userProductDao.countBySql(sql,UserProduct.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_USER_PRODUCT where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) userProductDao.countBySql(sql,UserProduct.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}