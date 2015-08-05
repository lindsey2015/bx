package cn.edu.xmut.modules.insured.bean;

import cn.edu.xmut.core.entity.IdEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Created by lelaw on 6/13/2015.
 */
@Entity
@Table(name="tb_insured_user")
public class InsuredUser extends IdEntity {
    public static enum FieldOfInsured {
        ID,
        NAME,
        IDENTITY_TYPE,
        IDENTITY,
        OCCUPATION_TYPE,
        BIRTHDAY,
        BD_ID
    }

    @NotEmpty
    @Size(max =25)
    private String name;
    private int identityType;
    private String identity;
    private int occupationType;
    private String birthday;
    private String bdId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentityType() {
        return identityType;
    }

    public void setIdentityType(int identityType) {
        this.identityType = identityType;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(int occupationType) {
        this.occupationType = occupationType;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBdId() {
        return bdId;
    }

    public void setBdId(String bdId) {
        this.bdId = bdId;
    }
}
