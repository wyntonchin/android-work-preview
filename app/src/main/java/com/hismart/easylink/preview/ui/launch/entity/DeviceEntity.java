package com.hismart.easylink.preview.ui.launch.entity;

import org.litepal.crud.LitePalSupport;

/**
 * @author qinwendong
 * @date 2018/6/12
 * descrption:
 */
public class DeviceEntity extends LitePalSupport {
    private Long id;
    //使用deviceId
    //private int deviceIdNumber;
    private Long deviceId;
    private String ctrlUrl;
    //设备图片
    private int pictureId;
    private String picturePath;
    //设备类型
    private String deviceTypeCode;
    private String deviceSubTypeCode;
    private String deviceTypeName;
    private String deviceTypeIcon;
    //TODO
    private int deviceUseType;
    //TODO
    private String uiModulePath;

    private String deviceLibPath;

    private int onoffStatus;
    //TODO 下面三个时间貌似有点重复
    private long lastUpdateTime;
    private long cmdUpdateTime;
    private long statusUpdateTime;
    private int stateKey;
    private int cmdKey;
    //开关状态
    private int switchState;
    //开命令
    private int turnOnCmd;
    //关命令
    private int turnOffCmd;
    //开名字
    private String turnOnName;
    //关名字
    private String turnOffName;
    //private int stateOn;
    //private int stateOff;
    private long uiModuleUpdateVersion;
    private long devLibUpdateVersion;
    private int stateOnline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getCtrlUrl() {
        return ctrlUrl;
    }

    public void setCtrlUrl(String ctrlUrl) {
        this.ctrlUrl = ctrlUrl;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getDeviceSubTypeCode() {
        return deviceSubTypeCode;
    }

    public void setDeviceSubTypeCode(String deviceSubTypeCode) {
        this.deviceSubTypeCode = deviceSubTypeCode;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceTypeIcon() {
        return deviceTypeIcon;
    }

    public void setDeviceTypeIcon(String deviceTypeIcon) {
        this.deviceTypeIcon = deviceTypeIcon;
    }

    public int getDeviceUseType() {
        return deviceUseType;
    }

    public void setDeviceUseType(int deviceUseType) {
        this.deviceUseType = deviceUseType;
    }

    public String getUiModulePath() {
        return uiModulePath;
    }

    public void setUiModulePath(String uiModulePath) {
        this.uiModulePath = uiModulePath;
    }

    public String getDeviceLibPath() {
        return deviceLibPath;
    }

    public void setDeviceLibPath(String deviceLibPath) {
        this.deviceLibPath = deviceLibPath;
    }

    public int getOnoffStatus() {
        return onoffStatus;
    }

    public void setOnoffStatus(int onoffStatus) {
        this.onoffStatus = onoffStatus;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public long getCmdUpdateTime() {
        return cmdUpdateTime;
    }

    public void setCmdUpdateTime(long cmdUpdateTime) {
        this.cmdUpdateTime = cmdUpdateTime;
    }

    public long getStatusUpdateTime() {
        return statusUpdateTime;
    }

    public void setStatusUpdateTime(long statusUpdateTime) {
        this.statusUpdateTime = statusUpdateTime;
    }

    public int getStateKey() {
        return stateKey;
    }

    public void setStateKey(int stateKey) {
        this.stateKey = stateKey;
    }

    public int getCmdKey() {
        return cmdKey;
    }

    public void setCmdKey(int cmdKey) {
        this.cmdKey = cmdKey;
    }

    public int getSwitchState() {
        return switchState;
    }

    public void setSwitchState(int switchState) {
        this.switchState = switchState;
    }

    public int getTurnOnCmd() {
        return turnOnCmd;
    }

    public void setTurnOnCmd(int turnOnCmd) {
        this.turnOnCmd = turnOnCmd;
    }

    public int getTurnOffCmd() {
        return turnOffCmd;
    }

    public void setTurnOffCmd(int turnOffCmd) {
        this.turnOffCmd = turnOffCmd;
    }

    public String getTurnOnName() {
        return turnOnName;
    }

    public void setTurnOnName(String turnOnName) {
        this.turnOnName = turnOnName;
    }

    public String getTurnOffName() {
        return turnOffName;
    }

    public void setTurnOffName(String turnOffName) {
        this.turnOffName = turnOffName;
    }

    public long getUiModuleUpdateVersion() {
        return uiModuleUpdateVersion;
    }

    public void setUiModuleUpdateVersion(long uiModuleUpdateVersion) {
        this.uiModuleUpdateVersion = uiModuleUpdateVersion;
    }

    public long getDevLibUpdateVersion() {
        return devLibUpdateVersion;
    }

    public void setDevLibUpdateVersion(long devLibUpdateVersion) {
        this.devLibUpdateVersion = devLibUpdateVersion;
    }

    public int getStateOnline() {
        return stateOnline;
    }

    public void setStateOnline(int stateOnline) {
        this.stateOnline = stateOnline;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getHouseId() {
        return houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    private long roomId;
    private long houseId;

}
