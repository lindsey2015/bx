package cn.edu.xmut.modules.product.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_product")
public class Product extends IdEntity{

	// 自动生成区域开始
	public static enum FieldOfProduct {
		ID, 
		NAME, 
		USEFUL,
		CATAGORY_ID,
		TB_AREA,
		TK_ADDR,
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	@NotEmpty @Size(max =25)
	private String name;
	private boolean useful;
	private String catagoryId;
	private String tbArea;
	private String tkAddr;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isUseful() {
		return useful;
	}
	public void setUseful(boolean useful) {
		this.useful = useful;
	}
	public String getCatagoryId() {
		return catagoryId;
	}
	public void setCatagoryId(String catagoryId) {
		this.catagoryId = catagoryId;
	}
	public String getTbArea() {
		return tbArea;
	}
	public void setTbArea(String tbArea) {
		this.tbArea = tbArea;
	}
	public String getTkAddr() {
		return tkAddr;
	}
	public void setTkAddr(String tkAddr) {
		this.tkAddr = tkAddr;
	}
	
}
