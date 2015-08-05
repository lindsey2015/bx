package  cn.edu.xmut.modules.bzdate.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.edu.xmut.core.persistence.BaseDao;
import cn.edu.xmut.core.persistence.BaseDaoImpl;
import cn.edu.xmut.modules.bzdate.bean.BzDate;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
public interface BzDateDao extends BzDateDaoCustom, CrudRepository<BzDate, String> {
	
	// 用户自定义开始，请不要修改以上内容
	
	@Query("select min(A.minDay) from BzDate A where A.ageGroupId = :ageGroupId")
	public int getMin(@Param("ageGroupId") String ageGroupId);
	@Query("select max(A.maxDay) from BzDate A where A.ageGroupId = :ageGroupId")
	public int getMax(@Param("ageGroupId") String ageGroupId);
	
	@Query("select A.value from BzDate A where A.ageGroupId = :ageGroupId and A.minDay <= :bzDate and A.maxDay>=:bzDate")
	public double getValue(@Param("ageGroupId") String ageGroupId,@Param("bzDate") int bzDate);
	// 用户自定义结束，请不要修改以下内容
}

interface BzDateDaoCustom extends BaseDao<BzDate> {

}

@Repository
class BzDateDaoImpl extends BaseDaoImpl<BzDate> implements BzDateDaoCustom {

}