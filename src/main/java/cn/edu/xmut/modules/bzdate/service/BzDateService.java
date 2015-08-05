package  cn.edu.xmut.modules.bzdate.service;

import java.util.List;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.modules.bzdate.bean.BzDate;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
public interface BzDateService{

	// 自动生成区域
	
	/**
	 * 计算BzDate所有条目
	 * 
	 * @param bzDate
	 *
	 * @return 返回BzDate信息所有条目
	 */
	public int countAll();
	
	/**
	 * 添加BzDate一条信息
	 * 
	 * @param bzDate
	 *
	 * @return 返回BzDate信息
	 */
	public BzDate save(BzDate bzDate);
 
 	/**
	 * 删除BzDate多条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String[] ids);
	
	/**
	 * 删除BzDate一条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String id);
	
	/**
	 * 根据BzDate编号查找BzDate
	 * 
	 * @param id
	 *
	 * @return BzDate，若不存在则返回null
	 */
	BzDate findById(String id);
	
	/**
	 * 翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<BzDate>
	 */
	public Page<BzDate> findPageOrderBy(Pageable pageable, String orderBy);
	
	/**
	 * 根据一个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<BzDate>
	 */
	public Page<BzDate> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<BzDate>
	 */
	public Page<BzDate> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
	/**
	 * 根据三个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<BzDate>
	 */
	public Page<BzDate> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy);
	
	/**
	 * 根据单个字段名称查找一条记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 *
	 * @return BzDate
	 */
	public BzDate getByOneField(String fieldName, Object fieldValue);
	
	/**
	 * 根据两个字段名称查找一条记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 *
	 * @return BzDate
	 */
	public BzDate getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);
	
	/**
	 * 根据单个字段名称查找记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param orderBy
	 *
	 * @return List<BzDate>
	 */
	public List<BzDate> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个字段名称查找记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 * @param orderBy
	 *
	 * @return List<BzDate>
	 */
	public List<BzDate> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
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
	public List<BzDate> check(int min, int max,String ageGroup);
	public int getMin(String ageGroupId);
	public int getMax(String ageGroupId);
	public double getValue(int bzDate, String ageGroupId);
	
	// 用户自定义结束，请不要修改以下内容
}