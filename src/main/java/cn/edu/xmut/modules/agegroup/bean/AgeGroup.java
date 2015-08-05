package cn.edu.xmut.modules.agegroup.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cn.edu.xmut.core.entity.IdEntity;

@Entity
@Table(name = "tb_age_group")
public class AgeGroup extends IdEntity {

    // 自动生成区域开始
    public static enum FieldOfAgeGroup {
        ID,
        PRODUCT_ID,
        AGE_GROUP,
        MIN_AGE,
        MAX_AGE
    }
    // 自动生成区域结束

    private static final long serialVersionUID = 1L;
    private String productId;
    @NotEmpty
    @Size(max = 25)
    private String ageGroup;
    private int minAge;
    private int maxAge;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
