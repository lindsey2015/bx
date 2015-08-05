package cn.edu.xmut.modules.user.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_user")
public class User extends IdEntity{

	// 自动生成区域开始
	public static enum FieldOfUser {
		ID, 
		USERNAME, 
		PASSWORD, 
		TYPE, 
		USEFUL,
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	@NotEmpty @Size(max =25)
	private String username;
	@NotEmpty @Size(max =25)
	private String password;
	private int type;
	private boolean useful;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isUseful() {
		return useful;
	}
	public void setUseful(boolean useful) {
		this.useful = useful;
	}
	
}
