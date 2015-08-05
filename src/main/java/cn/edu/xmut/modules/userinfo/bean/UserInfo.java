package cn.edu.xmut.modules.userinfo.bean;

import javax.persistence.Entity;
import javax.persistence.Table;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cn.edu.xmut.core.entity.IdEntity;

@Entity
@Table(name="tb_user_info")
public class UserInfo extends IdEntity{

	// 自动生成区域开始
	public static enum FieldOfUserInfo {
		ID, 
		USER_ID, 
		NAME, 
		IDENTITY, 
		BIRTHDAY, 
		SEX, 
		TELEPHONE, 
		EMAIL, 
		E_CONTACT, 
		E_TELEPHONE, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	private String userId;
	@NotEmpty @Size(max =25)
	private String name;
	@NotEmpty @Size(max =25)
	private String identity;
	@NotEmpty @Size(max =25)
	private String birthday;
	private boolean sex;
	@NotEmpty @Size(max =25)
	private String telephone;
	@NotEmpty @Size(max =25)
	private String email;
	@NotEmpty @Size(max =25)
	private String eContact;
	@NotEmpty @Size(max =25)
	private String eTelephone;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String geteContact() {
		return eContact;
	}
	public void seteContact(String eContact) {
		this.eContact = eContact;
	}
	public String geteTelephone() {
		return eTelephone;
	}
	public void seteTelephone(String eTelephone) {
		this.eTelephone = eTelephone;
	}
	
}
