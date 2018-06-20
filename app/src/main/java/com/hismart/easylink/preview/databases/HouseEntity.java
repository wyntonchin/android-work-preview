package com.hismart.easylink.preview.databases;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinwendong
 * @date 2018/6/12
 * descrption:
 */
public class HouseEntity extends LitePalSupport {
    //TODO 此UID来自HismartSdk,是否需要保留需要确认
    private String areaName;
    private String homeAddress;
    private Long houseId;
    private String houseName;
    private String homeNameFirstLetter;
    private int roleFlag;
    private String roomsInfoStr;
    private int timezone;
    /**
     * 省市区
     */
    private String province;
    private String city;
    private String district;

    private List<RoomEntity> roomList = new ArrayList<>();
    private List<HouseMember> memberList = new ArrayList<>();
    private List<SuspendSceneInfo> supendScenes = new ArrayList<>();
    private List<DeviceEntity> deviceEntityList = new ArrayList<>();
}
