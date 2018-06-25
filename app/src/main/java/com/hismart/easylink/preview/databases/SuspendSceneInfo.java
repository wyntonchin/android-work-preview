package com.hismart.easylink.preview.databases;

import org.litepal.crud.LitePalSupport;

import java.sql.Date;

/**
 * @author qinwendong
 * @date 2018/6/20
 * descrption:
 */
public class SuspendSceneInfo extends LitePalSupport {
    String cmdList;
    String picData;
    int picIndex;
    boolean isQuickScene;
    long orderId;
    long sceneId;
    String sceneName;
    String sceneTriggerCondition;
    String sceneType;
    String status;
    //执行周期
    String executeCycle;//TODO
    Date executeDate;
    String executeTime;
}
