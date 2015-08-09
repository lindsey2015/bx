package cn.edu.xmut.modules.insured.service;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.modules.insured.bean.InsuredUser;

import java.util.List;

public interface InsuredUserService {
    // 自动生成区域

    /**
     * 添加InsuredUser一条信息
     *
     * @param insuredUser
     *
     * @return 返回InsuredUser信息
     */
    public InsuredUser save(InsuredUser insuredUser);

    /**
     * 添加InsuredUser一条信息
     *
     * @param insuredUser
     *
     * @return 返回InsuredUser信息
     */
    public Iterable<InsuredUser> save(List<InsuredUser> insuredUsers);

    /**
     * 删除InsuredUser多条记录
     *
     * @param ids
     *
     * @return void
     */
    public void deleteById(String[] ids);

    /**
     * 删除InsuredUser一条记录
     *
     * @param ids
     *
     * @return void
     */
    public void deleteById(String id);

    /**
     * 根据InsuredUser编号查找InsuredUser
     *
     * @param id
     *
     * @return InsuredUser，若不存在则返回null
     */
    InsuredUser findById(String id);

    /**
     * 翻页方式找出所有记录
     *
     * @param pageable
     *
     * @return Page<InsuredUser>
     */
    public Page<InsuredUser> findPageOrderBy(Pageable pageable, String orderBy);

    /**
     * 根据一个参数翻页方式找出所有记录
     *
     * @param pageable
     *
     * @return Page<InsuredUser>
     */
    public Page<InsuredUser> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy);

    /**
     * 根据两个参数翻页方式找出所有记录
     *
     * @param pageable
     *
     * @return Page<InsuredUser>
     */
    public Page<InsuredUser> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);

    /**
     * 根据三个参数翻页方式找出所有记录
     *
     * @param pageable
     *
     * @return Page<InsuredUser>
     */
    public Page<InsuredUser> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy);

    /**
     * 根据单个字段名称查找一条记录
     *
     * @param fieldName
     * @param fieldValue
     *
     * @return InsuredUser
     */
    public InsuredUser getByOneField(String fieldName, Object fieldValue);

    /**
     * 根据两个字段名称查找一条记录
     *
     * @param fieldName1
     * @param fieldValue1
     * @param fieldName2
     * @param fieldValue2
     *
     * @return InsuredUser
     */
    public InsuredUser getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);

    /**
     * 根据单个字段名称查找记录
     *
     * @param fieldName
     * @param fieldValue
     * @param orderBy
     *
     * @return List<InsuredUser>
     */
    public List<InsuredUser> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy);

    /**
     * 根据两个字段名称查找记录
     *
     * @param fieldName1
     * @param fieldValue1
     * @param fieldName2
     * @param fieldValue2
     * @param orderBy
     *
     * @return List<InsuredUser>
     */
    public List<InsuredUser> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);

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
}
