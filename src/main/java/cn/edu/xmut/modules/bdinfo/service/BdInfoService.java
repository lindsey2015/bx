package  cn.edu.xmut.modules.bdinfo.service;

import java.util.List;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.modules.bdinfo.bean.BdInfo;
import cn.edu.xmut.modules.bdinfo.bean.SearchCriteria;
import cn.edu.xmut.web.bdinfo.BdInfoController;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
public interface BdInfoService{

	// 自动生成区域
	
	/**
	 * 计算BdInfo所有条目
	 * 
	 * @param bdInfo
	 *
	 * @return 返回BdInfo信息所有条目
	 */
	public int countAll();
	
	/**
	 * 添加BdInfo一条信息
	 * 
	 * @param bdInfo
	 *
	 * @return 返回BdInfo信息
	 */
	public BdInfo save(BdInfo bdInfo);
 
 	/**
	 * 删除BdInfo多条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String[] ids);
	
	/**
	 * 删除BdInfo一条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String id);
	
	/**
	 * 根据BdInfo编号查找BdInfo
	 * 
	 * @param id
	 *
	 * @return BdInfo，若不存在则返回null
	 */
	BdInfo findById(String id);
	
	/**
	 * 翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<BdInfo>
	 */
	public Page<BdInfo> findPageOrderBy(Pageable pageable, String orderBy);
	
	/**
	 * 根据一个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<BdInfo>
	 */
	public Page<BdInfo> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<BdInfo>
	 */
	public Page<BdInfo> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
	/**
	 * 根据三个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<BdInfo>
	 */
	public Page<BdInfo> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy);
	
	/**
	 * 根据单个字段名称查找一条记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 *
	 * @return BdInfo
	 */
	public BdInfo getByOneField(String fieldName, Object fieldValue);
	
	/**
	 * 根据两个字段名称查找一条记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 *
	 * @return BdInfo
	 */
	public BdInfo getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);
	
	/**
	 * 根据单个字段名称查找记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param orderBy
	 *
	 * @return List<BdInfo>
	 */
	public List<BdInfo> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个字段名称查找记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 * @param orderBy
	 *
	 * @return List<BdInfo>
	 */
	public List<BdInfo> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
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
	public Page<BdInfo> findPageByTwoFieldsLike(Pageable pageable,String name1, int status, String name2, String searchParam);

	public int getMaxNo();

	public Page<BdInfo> findPageByTwoFields(Pageable pageable,String name1,int status, String name2,
			String startTime, String name3, String endTime);

	public List<BdInfo> listOrderBy(String name, String startTime,
			String name2, String endTime, String name3, int status,
			String string);

    public List<BdInfo> listBySearchCriteria(SearchCriteria searchCriteria);

    public double getActualTotalFee(BdInfo bdInfo);

    String generateBdNumber(BdInfo bdInfo);

    String generateTbNumber(BdInfo bdInfo);

    int getSequence();

    Page<BdInfo> findPageBySearchCriteria(SearchCriteria searchCriteria);

    public BdInfo getByBdNoAndInsuredUserName(String bdNo, String bbxrName);

    // 用户自定义结束，请不要修改以下内容
}