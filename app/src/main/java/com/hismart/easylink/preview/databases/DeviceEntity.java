package com.hismart.easylink.preview.databases;

import org.litepal.crud.LitePalSupport;

import java.sql.Date;

/**
 * @author qinwendong
 * @date 2018/6/20
 * descrption:
 */
public class DeviceEntity extends LitePalSupport {
    String barCode;
    Date bindTime;
    //商标码
    String brandCode;
    String deviceCode;
    long deviceId;
    String deviceName;
    String deviceNickName;
    String deviceTypeCode;
    String deviceTypeName;
    String deviceSubTypeCode;
    String extInfo;
    String lastPhsicalInfo;
    //一拖多设备
    boolean multiDevice;
    long roomId;
    String roomName;
    String status;
    String wifiId;
    String wifiModelType;
    //设备昵称首字母
    String deviceNickNameFirstLetter;
}
