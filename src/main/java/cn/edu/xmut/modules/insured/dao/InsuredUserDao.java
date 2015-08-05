package cn.edu.xmut.modules.insured.dao;

import cn.edu.xmut.core.persistence.BaseDao;
import cn.edu.xmut.core.persistence.BaseDaoImpl;
import cn.edu.xmut.modules.insured.bean.InsuredUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yangzj
 * @version v1.0.0
 * @date $date
 * @since v1.0.0
 */
public interface InsuredUserDao extends InsuredUserDaoCustom, CrudRepository<InsuredUser, String> {

    // 用户自定义开始，请不要修改以上内容


    // 用户自定义结束，请不要修改以下内容
}

interface InsuredUserDaoCustom extends BaseDao<InsuredUser> {

}

@Repository
class InsuredUserDaoImpl extends BaseDaoImpl<InsuredUser> implements InsuredUserDaoCustom {

}