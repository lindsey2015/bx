package  cn.edu.xmut.modules.catagory.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import cn.edu.xmut.core.persistence.BaseDao;
import cn.edu.xmut.core.persistence.BaseDaoImpl;
import cn.edu.xmut.modules.catagory.bean.Catagory;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
public interface CatagoryDao extends CatagoryDaoCustom, CrudRepository<Catagory, String> {
	
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
}

interface CatagoryDaoCustom extends BaseDao<Catagory> {

}

@Repository
class CatagoryDaoImpl extends BaseDaoImpl<Catagory> implements CatagoryDaoCustom {

}