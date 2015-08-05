package cn.edu.xmut.modules.agegroup.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.modules.agegroup.bean.AgeGroup;

/**
 * @author yangzj
 * @version v1.0.0
 * @date $date
 * @since v1.0.0
 */
public interface AgeGroupService {

    // 自动生成区域

    /**
     * 计算AgeGroup所有条目
     *
     * @param ageGroup
     * @return 返回AgeGroup信息所有条目
     */
    public int countAll();

    /**
     * 添加AgeGroup一条信息
     *
     * @param ageGroup
     * @return 返回AgeGroup信息
     */
    public AgeGroup save(AgeGroup ageGroup);

    /**
     * 删除AgeGroup多条记录
     *
     * @param ids
     * @return void
     */
    public void deleteById(String[] ids);

    /**
     * 删除AgeGroup一条记录
     *
     * @param ids
     * @return void
     */
    public void deleteById(String id);

    /**
     * 根据AgeGroup编号查找AgeGroup
     *
     * @param id
     * @return AgeGroup，若不存在则返回null
     */
    AgeGroup findById(String id);

    /**
     * 翻页方式找出所有记录
     *
     * @param pageable
     * @return Page<AgeGroup>
     */
    public Page<AgeGroup> findPageOrderBy(Pageable pageable, String orderBy);

    /**
     * 根据一个参数翻页方式找出所有记录
     *
     * @param pageable
     * @return Page<AgeGroup>
     */
    public Page<AgeGroup> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy);

    /**
     * 根据两个参数翻页方式找出所有记录
     *
     * @param pageable
     * @return Page<AgeGroup>
     */
    public Page<AgeGroup> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);

    /**
     * 根据三个参数翻页方式找出所有记录
     *
     * @param pageable
     * @return Page<AgeGroup>
     */
    public Page<AgeGroup> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy);

    /**
     * 根据单个字段名称查找一条记录
     *
     * @param fieldName
     * @param fieldValue
     * @return AgeGroup
     */
    public AgeGroup getByOneField(String fieldName, Object fieldValue);

    /**
     * 根据两个字段名称查找一条记录
     *
     * @param fieldName1
     * @param fieldValue1
     * @param fieldName2
     * @param fieldValue2
     * @return AgeGroup
     */
    public AgeGroup getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);

    /**
     * 根据单个字段名称查找记录
     *
     * @param fieldName
     * @param fieldValue
     * @param orderBy
     * @return List<AgeGroup>
     */
    public List<AgeGroup> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy);

    /**
     * 根据两个字段名称查找记录
     *
     * @param fieldName1
     * @param fieldValue1
     * @param fieldName2
     * @param fieldValue2
     * @param orderBy
     * @return List<AgeGroup>
     */
    public List<AgeGroup> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);

    /**
     * 根据单个字段名称计算记录数目
     *
     * @param fieldName
     * @param fieldValue
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
     * @return int
     */
    public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);


    // 用户自定义开始，请不要修改以上内容
    public Map<AgeGroup, Double> getAgeGroupFee(String productId, int coverageDays);


    // 用户自定义结束，请不要修改以下内容
}