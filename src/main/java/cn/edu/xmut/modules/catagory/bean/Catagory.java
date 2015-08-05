package cn.edu.xmut.modules.catagory.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_catagory")
public class Catagory extends IdEntity{

	// 自动生成区域开始
	public static enum FieldOfCatagory {
		ID, 
		NAME, 
		NAME_E, 
		USEFUL, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	@NotEmpty @Size(max =25)
	private String name;
	@NotEmpty @Size(max =25)
	private String nameE;
	private boolean useful;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameE() {
		return nameE;
	}
	public void setNameE(String nameE) {
		this.nameE = nameE;
	}
	public boolean isUseful() {
		return useful;
	}
	public void setUseful(boolean useful) {
		this.useful = useful;
	}
	
	
}
