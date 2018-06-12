package com.hismart.easylink.preview.ui.launch.entity;

import org.litepal.crud.LitePalSupport;

/**
 * @author qinwendong
 * @date 2018/6/12
 * descrption:
 */
public class RoomEntity extends LitePalSupport{
    //TODO 此UID来自HismartSdk,是否需要保留需要确认
    //private static final long serialVersionUID = 1125956576714367342L;

    private Long id;
    /**
     * 房间roomId,数据来自网络
     */
    private Long roomId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomAlias() {
        return roomAlias;
    }

    public void setRoomAlias(String roomAlias) {
        this.roomAlias = roomAlias;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public long getHouseId() {
        return houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public int getHouseAlias() {
        return houseAlias;
    }

    public void setHouseAlias(int houseAlias) {
        this.houseAlias = houseAlias;
    }

    public long getFloorId() {
        return floorId;
    }

    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }

    public String getRoomImgUrl() {
        return roomImgUrl;
    }

    public void setRoomImgUrl(String roomImgUrl) {
        this.roomImgUrl = roomImgUrl;
    }

    /**
     * 房间别名
     */
    private String roomAlias;
    private String roomDescription;
    /**
     * 房间对应的房子
     */
    private long houseId;
    private int houseAlias;
    /**
     * 楼层id
     *
     * @deprecated 楼层不在建议使用了，建议废弃
     */
    private long floorId;
    /**
     * 房间图片的url
     */
    private String roomImgUrl;
}
