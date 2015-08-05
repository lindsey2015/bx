package cn.edu.xmut.modules.userproduct.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_user_product")
public class UserProduct extends IdEntity{

	// 自动生成区域开始
	public static enum FieldOfUserProduct {
		ID, 
		USER_ID, 
		PRODUCT_ID, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	private String userId;
	private String productId;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
