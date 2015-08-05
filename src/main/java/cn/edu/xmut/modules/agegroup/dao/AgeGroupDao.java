package cn.edu.xmut.modules.agegroup.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import cn.edu.xmut.core.persistence.BaseDao;
import cn.edu.xmut.core.persistence.BaseDaoImpl;
import cn.edu.xmut.modules.agegroup.bean.AgeGroup;

import java.util.List;

/**
 * @author yangzj
 * @version v1.0.0
 * @date $date
 * @since v1.0.0
 */
public interface AgeGroupDao extends AgeGroupDaoCustom, CrudRepository<AgeGroup, String> {

    // 用户自定义开始，请不要修改以上内容
    @Query("select A.ageGroup, A.minAge, A.maxAge, B.value from AgeGroup A, BzDate B \n" +
            "where A.id = B.ageGroupId \n" +
            "and B.minDay <= :coverageDays \n" +
            "and B.maxDay >= :coverageDays \n" +
            "and A.productId = :productId")
    public List<Object> getProductFeeByDaysCoverage(@Param("coverageDays") int coverageDays, @Param("productId") String productId);


    // 用户自定义结束，请不要修改以下内容
}

interface AgeGroupDaoCustom extends BaseDao<AgeGroup> {

}

@Repository
class AgeGroupDaoImpl extends BaseDaoImpl<AgeGroup> implements AgeGroupDaoCustom {

}