package  cn.edu.xmut.modules.userinfo.service.impl;

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmut.modules.userinfo.bean.UserInfo;
import cn.edu.xmut.modules.userinfo.service.UserInfoService;
import cn.edu.xmut.modules.userinfo.dao.UserInfoDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("userInfoServiceImpl")
@Transactional
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public int countAll() {
		return (int) userInfoDao.count();
	}
	
	@Override
	public UserInfo save(UserInfo userInfo) {
		return userInfoDao.save(userInfo);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			userInfoDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		userInfoDao.delete(id);
	}
	
	@Override
	public UserInfo findById(String id) {
		return userInfoDao.findOne(id);
	}
	
	@Override
	public Page<UserInfo> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_USER_INFO order by "+orderBy;
		return userInfoDao.findBySql(pageable,sql,UserInfo.class);
	}
	
	@Override
	public Page<UserInfo> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_USER_INFO where "+fieldName+" = ? order by "+orderBy;
		return userInfoDao.findBySql(pageable,sql,UserInfo.class,fieldValue);
	}
	
	@Override
	public Page<UserInfo> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_USER_INFO where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return userInfoDao.findBySql(pageable,sql,UserInfo.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<UserInfo> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_USER_INFO where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return userInfoDao.findBySql(pageable,sql,UserInfo.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public UserInfo getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_USER_INFO where "+fieldName+" = ?";
		return (UserInfo) userInfoDao.getBySql(sql,UserInfo.class,fieldValue);
	}
	
	@Override
	public UserInfo getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_USER_INFO where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (UserInfo) userInfoDao.getBySql(sql,UserInfo.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<UserInfo> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_USER_INFO where "+fieldName+" = ? order by "+orderBy;
		return userInfoDao.findBySql(sql,UserInfo.class,fieldValue);
	}
	
	@Override
	public List<UserInfo> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_USER_INFO where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return userInfoDao.findBySql(sql,UserInfo.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_USER_INFO where "+fieldName+" = ?";
		return (int) userInfoDao.countBySql(sql,UserInfo.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_USER_INFO where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) userInfoDao.countBySql(sql,UserInfo.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容

	
	// 用户自定义结束，请不要修改以下内容
 	
	
}