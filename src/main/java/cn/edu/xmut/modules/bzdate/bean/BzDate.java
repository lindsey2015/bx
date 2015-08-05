package cn.edu.xmut.modules.bzdate.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_bz_date")
public class BzDate extends IdEntity{
	
// 自动生成区域开始
	public static enum FieldOfBzDate {
		ID, 
		AGE_GROUP_ID, 
		MIN_DAY, 
		MAX_DAY, 
		VALUE, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	private String ageGroupId;
	private int minDay;
	private int maxDay;
	private double value;
	public String getAgeGroupId() {
		return ageGroupId;
	}
	public void setAgeGroupId(String ageGroupId) {
		this.ageGroupId = ageGroupId;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getMinDay() {
		return minDay;
	}
	public void setMinDay(int minDay) {
		this.minDay = minDay;
	}
	public int getMaxDay() {
		return maxDay;
	}
	public void setMaxDay(int maxDay) {
		this.maxDay = maxDay;
	}
	
	
}
