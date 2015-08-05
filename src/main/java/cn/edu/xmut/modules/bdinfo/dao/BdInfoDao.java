package cn.edu.xmut.modules.bdinfo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.edu.xmut.core.persistence.BaseDao;
import cn.edu.xmut.core.persistence.BaseDaoImpl;
import cn.edu.xmut.modules.bdinfo.bean.BdInfo;

/**
 * @author yangzj
 * @version v1.0.0
 * @date $date
 * @since v1.0.0
 */
public interface BdInfoDao extends BdInfoDaoCustom, CrudRepository<BdInfo, String> {

    // 用户自定义开始，请不要修改以上内容
    @Query("select max(A.no) from BdInfo A")
    public Integer getMax();
    // 用户自定义结束，请不要修改以下内容
}

interface BdInfoDaoCustom extends BaseDao<BdInfo> {

}

@Repository
class BdInfoDaoImpl extends BaseDaoImpl<BdInfo> implements BdInfoDaoCustom {

}