package com.hismart.easylink.preview.ui.launch.entity;

import org.litepal.crud.LitePalSupport;

/**
 * @author qinwendong
 * @date 2018/6/12
 * descrption:
 */
public class HouseEntity extends LitePalSupport {
    //TODO 此UID来自HismartSdk,是否需要保留需要确认
    private Long id;
    private Long houseId;
    /**
     * 房子别名
     */
    private String houseAlias;
    private int roleFlag;
    private String address;
    /**
     * 省市区
     */
    private String province;
    private String city;
    private String district;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseAlias() {
        return houseAlias;
    }

    public void setHouseAlias(String houseAlias) {
        this.houseAlias = houseAlias;
    }

    public int getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(int roleFlag) {
        this.roleFlag = roleFlag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
