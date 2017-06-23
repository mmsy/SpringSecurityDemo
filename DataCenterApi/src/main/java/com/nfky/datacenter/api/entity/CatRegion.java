package com.nfky.datacenter.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 国家区域表
 *
 * Created by lyr on 2017/6/8.
 */
@Entity
@Table(name = "CAT_REGION")
public class CatRegion {
    // 主键
    private String regionId;
    // 地区code
    private String regionCode;
    // 地区名称
    private String regionName;
    // 全路径
    private String regionPath;
    // 表示
    private String impStatus;
    // 数据状态
    private String dataStatus;
    // 父区域ID
    private String parentRegionId;
    // 是否省会城市 0否 1是
    private String isprov;
    // 是否县政府驻地 0否 1是
    private String iscounty;
    // 备注
    private String remark;
    // 创建人名称
    private String createName;
    // 创建人ID
    private String createUser;
    // 创建时间
    private Date createDate;
    // 修改人名称
    private String modifyName;
    // 修改人ID
    private String modifyUser;
    // 修改时间
    private Date modifyDate;

    @Id
    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getImpStatus() {
        return impStatus;
    }

    public void setImpStatus(String impStatus) {
        this.impStatus = impStatus;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getParentRegionId() {
        return parentRegionId;
    }

    public void setParentRegionId(String parentRegionId) {
        this.parentRegionId = parentRegionId;
    }

    public String getIsprov() {
        return isprov;
    }

    public void setIsprov(String isprov) {
        this.isprov = isprov;
    }

    public String getIscounty() {
        return iscounty;
    }

    public void setIscounty(String iscounty) {
        this.iscounty = iscounty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
