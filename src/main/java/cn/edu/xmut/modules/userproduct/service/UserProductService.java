package  cn.edu.xmut.modules.userproduct.service;

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.modules.userproduct.bean.UserProduct;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
public interface UserProductService{

	// 自动生成区域
	
	/**
	 * 计算UserProduct所有条目
	 * 
	 * @param userProduct
	 *
	 * @return 返回UserProduct信息所有条目
	 */
	public int countAll();
	
	/**
	 * 添加UserProduct一条信息
	 * 
	 * @param userProduct
	 *
	 * @return 返回UserProduct信息
	 */
	public UserProduct save(UserProduct userProduct);
 
 	/**
	 * 删除UserProduct多条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String[] ids);
	
	/**
	 * 删除UserProduct一条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String id);
	
	/**
	 * 根据UserProduct编号查找UserProduct
	 * 
	 * @param id
	 *
	 * @return UserProduct，若不存在则返回null
	 */
	UserProduct findById(String id);
	
	/**
	 * 翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<UserProduct>
	 */
	public Page<UserProduct> findPageOrderBy(Pageable pageable, String orderBy);
	
	/**
	 * 根据一个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<UserProduct>
	 */
	public Page<UserProduct> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<UserProduct>
	 */
	public Page<UserProduct> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
	/**
	 * 根据三个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<UserProduct>
	 */
	public Page<UserProduct> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy);
	
	/**
	 * 根据单个字段名称查找一条记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 *
	 * @return UserProduct
	 */
	public UserProduct getByOneField(String fieldName, Object fieldValue);
	
	/**
	 * 根据两个字段名称查找一条记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 *
	 * @return UserProduct
	 */
	public UserProduct getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);
	
	/**
	 * 根据单个字段名称查找记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param orderBy
	 *
	 * @return List<UserProduct>
	 */
	public List<UserProduct> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个字段名称查找记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 * @param orderBy
	 *
	 * @return List<UserProduct>
	 */
	public List<UserProduct> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
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
	
	
	// 用户自定义结束，请不要修改以下内容
}