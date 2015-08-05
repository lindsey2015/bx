package  cn.edu.xmut.modules.catagory.service;

import java.util.List;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.modules.catagory.bean.Catagory;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
public interface CatagoryService{

	// 自动生成区域
	
	/**
	 * 计算Catagory所有条目
	 * 
	 * @param catagory
	 *
	 * @return 返回Catagory信息所有条目
	 */
	public int countAll();
	
	/**
	 * 添加Catagory一条信息
	 * 
	 * @param catagory
	 *
	 * @return 返回Catagory信息
	 */
	public Catagory save(Catagory catagory);
 
 	/**
	 * 删除Catagory多条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String[] ids);
	
	/**
	 * 删除Catagory一条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String id);
	
	/**
	 * 根据Catagory编号查找Catagory
	 * 
	 * @param id
	 *
	 * @return Catagory，若不存在则返回null
	 */
	Catagory findById(String id);
	
	/**
	 * 翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<Catagory>
	 */
	public Page<Catagory> findPageOrderBy(Pageable pageable, String orderBy);
	
	/**
	 * 根据一个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<Catagory>
	 */
	public Page<Catagory> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<Catagory>
	 */
	public Page<Catagory> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
	/**
	 * 根据三个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<Catagory>
	 */
	public Page<Catagory> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy);
	
	/**
	 * 根据单个字段名称查找一条记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 *
	 * @return Catagory
	 */
	public Catagory getByOneField(String fieldName, Object fieldValue);
	
	/**
	 * 根据两个字段名称查找一条记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 *
	 * @return Catagory
	 */
	public Catagory getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);
	
	/**
	 * 根据单个字段名称查找记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param orderBy
	 *
	 * @return List<Catagory>
	 */
	public List<Catagory> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个字段名称查找记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 * @param orderBy
	 *
	 * @return List<Catagory>
	 */
	public List<Catagory> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
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
	public Page<Catagory> findPageByOneFieldLike(Pageable pageable,
			String name, String searchParam);
	
	
	// 用户自定义结束，请不要修改以下内容
}