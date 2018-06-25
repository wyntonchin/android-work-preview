package com.hismart.easylink.preview.databases;

import org.litepal.crud.LitePalSupport;

/**
 * @author qinwendong
 * @date 2018/6/20
 * descrption:
 */
public class TaskInfo extends LitePalSupport{
    String address;
    //预定日期
    String bookingDate;
    String bookingDateRange;
    Long cityId;
    String cityName;
    String customerName;
    Long deviceId;
    String afterSaleTelephone;
    //保养类型
    String maintainType;
    long provinceId;
    String provinceName;
    String questionDescription;
    String repairType;
    String serviceType;
    String telephone;
    //区域id
    long districtId;
    //区域名字
    String districtName;
    //街道
    long roadId;
    String roadName;
}
