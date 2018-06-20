package com.hismart.easylink.preview.databases;

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
    /**
     * 房间别名
     */
    private String roomName;
    private String roomDescription;
    /**
     * 房间对应的房子
     */
    private long houseId;
    private int houseAlias;
    /**
     * 房间图片的url
     */
    private String roomPic;
    private String roomPicUrl;
    /**
     * 楼层id
     *
     * @deprecated 楼层不在建议使用了，建议废弃
     */
    private long floorId;
}
