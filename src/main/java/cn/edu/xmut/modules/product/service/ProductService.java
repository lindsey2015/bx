package  cn.edu.xmut.modules.product.service;

import java.util.List;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.modules.product.bean.Product;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
public interface ProductService{

	// 自动生成区域
	
	/**
	 * 计算Product所有条目
	 * 
	 * @param product
	 *
	 * @return 返回Product信息所有条目
	 */
	public int countAll();
	
	/**
	 * 添加Product一条信息
	 * 
	 * @param product
	 *
	 * @return 返回Product信息
	 */
	public Product save(Product product);
 
 	/**
	 * 删除Product多条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String[] ids);
	
	/**
	 * 删除Product一条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String id);
	
	/**
	 * 根据Product编号查找Product
	 * 
	 * @param id
	 *
	 * @return Product，若不存在则返回null
	 */
	Product findById(String id);
	
	/**
	 * 翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<Product>
	 */
	public Page<Product> findPageOrderBy(Pageable pageable, String orderBy);
	
	/**
	 * 根据一个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<Product>
	 */
	public Page<Product> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<Product>
	 */
	public Page<Product> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
	/**
	 * 根据三个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<Product>
	 */
	public Page<Product> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy);
	
	/**
	 * 根据单个字段名称查找一条记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 *
	 * @return Product
	 */
	public Product getByOneField(String fieldName, Object fieldValue);
	
	/**
	 * 根据两个字段名称查找一条记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 *
	 * @return Product
	 */
	public Product getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);
	
	/**
	 * 根据单个字段名称查找记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param orderBy
	 *
	 * @return List<Product>
	 */
	public List<Product> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个字段名称查找记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 * @param orderBy
	 *
	 * @return List<Product>
	 */
	public List<Product> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
	/**
	 * 根据单个字段名称计算记录数目
	 * 
	 * @param fieldName
	 * @param fieldValue
	 *
	 * @return int
	 */
	public int countByOneField(String fieldName, Object fieldValue);
	
	/**
	 * 根据两个字段名称计算记录数目
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 *
	 * @return int
	 */
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);
	
	// 用户自定义开始，请不要修改以上内容
	
	public List<Product> findListByTwoFieldsLike(
			String name, String searchParam,String catagoryId);

	public List<Product> findAll();


	
	
	// 用户自定义结束，请不要修改以下内容
}