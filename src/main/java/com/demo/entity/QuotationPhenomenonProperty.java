package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class QuotationPhenomenonProperty implements Serializable {
    private Integer id;

    private String lockSerialNo;

    private Integer lockDemandId;

    private String phenomenonValueUnits;

    private Integer createBy;

    private Date createDt;

    private Integer updateBy;

    private Date updateDt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLockSerialNo() {
        return lockSerialNo;
    }

    public void setLockSerialNo(String lockSerialNo) {
        this.lockSerialNo = lockSerialNo == null ? null : lockSerialNo.trim();
    }

    public Integer getLockDemandId() {
        return lockDemandId;
    }

    public void setLockDemandId(Integer lockDemandId) {
        this.lockDemandId = lockDemandId;
    }

    public String getPhenomenonValueUnits() {
        return phenomenonValueUnits;
    }

    public void setPhenomenonValueUnits(String phenomenonValueUnits) {
        this.phenomenonValueUnits = phenomenonValueUnits == null ? null : phenomenonValueUnits.trim();
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        QuotationPhenomenonProperty other = (QuotationPhenomenonProperty) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLockSerialNo() == null ? other.getLockSerialNo() == null : this.getLockSerialNo().equals(other.getLockSerialNo()))
            && (this.getLockDemandId() == null ? other.getLockDemandId() == null : this.getLockDemandId().equals(other.getLockDemandId()))
            && (this.getPhenomenonValueUnits() == null ? other.getPhenomenonValueUnits() == null : this.getPhenomenonValueUnits().equals(other.getPhenomenonValueUnits()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDt() == null ? other.getCreateDt() == null : this.getCreateDt().equals(other.getCreateDt()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDt() == null ? other.getUpdateDt() == null : this.getUpdateDt().equals(other.getUpdateDt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLockSerialNo() == null) ? 0 : getLockSerialNo().hashCode());
        result = prime * result + ((getLockDemandId() == null) ? 0 : getLockDemandId().hashCode());
        result = prime * result + ((getPhenomenonValueUnits() == null) ? 0 : getPhenomenonValueUnits().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDt() == null) ? 0 : getCreateDt().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDt() == null) ? 0 : getUpdateDt().hashCode());
        return result;
    }
}