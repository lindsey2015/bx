package  cn.edu.xmut.modules.product.service.impl;

import java.util.List;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.xmut.modules.product.bean.Product;
import cn.edu.xmut.modules.product.service.ProductService;
import cn.edu.xmut.modules.product.dao.ProductDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("productServiceImpl")
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Override
	public int countAll() {
		return (int) productDao.count();
	}
	
	@Override
	public Product save(Product product) {
		return productDao.save(product);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			productDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		productDao.delete(id);
	}
	
	@Override
	public Product findById(String id) {
		return productDao.findOne(id);
	}
	
	@Override
	public Page<Product> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_PRODUCT order by "+orderBy;
		return productDao.findBySql(pageable,sql,Product.class);
	}
	
	@Override
	public Page<Product> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_PRODUCT where "+fieldName+" = ? order by "+orderBy;
		return productDao.findBySql(pageable,sql,Product.class,fieldValue);
	}
	
	@Override
	public Page<Product> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_PRODUCT where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return productDao.findBySql(pageable,sql,Product.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<Product> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_PRODUCT where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return productDao.findBySql(pageable,sql,Product.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public Product getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_PRODUCT where "+fieldName+" = ?";
		return (Product) productDao.getBySql(sql,Product.class,fieldValue);
	}
	
	@Override
	public Product getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_PRODUCT where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (Product) productDao.getBySql(sql,Product.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<Product> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_PRODUCT where "+fieldName+" = ? order by "+orderBy;
		return productDao.findBySql(sql,Product.class,fieldValue);
	}
	
	@Override
	public List<Product> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_PRODUCT where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return productDao.findBySql(sql,Product.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_PRODUCT where "+fieldName+" = ?";
		return (int) productDao.countBySql(sql,Product.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_PRODUCT where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) productDao.countBySql(sql,Product.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容
	@Override
	public List<Product> findListByTwoFieldsLike(String name, String searchParam,String catagoryId) {
		String sql = "select * from TB_PRODUCT where "+name+" LIKE '%"+searchParam+"%' and CATAGORY_ID='"+catagoryId+"'";
		return productDao.findBySql(sql,Product.class);
	}

	@Override
	public List<Product> findAll() {
		return (List<Product>) productDao.findAll();
	}

	// 用户自定义结束，请不要修改以下内容
 	
	
}