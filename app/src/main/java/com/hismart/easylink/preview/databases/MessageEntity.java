package com.hismart.easylink.preview.databases;

import org.litepal.crud.LitePalSupport;

/**
 * @author qinwendong
 * @date 2018/6/20
 * descrption:
 */
public class MessageEntity  extends LitePalSupport {
    String content;
    Long customerMsgId;
    //失效时间
    Long expireTime;
    Long formatId;
    Long msgId;
    //消息重要等级
    int msgLevel;
    Long msgOwnerId;
    String msgOwnerType;
    String msgType;
    String msgTypeCode;
    Long startTime;
}
