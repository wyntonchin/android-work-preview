package com.hismart.easylink.preview.databases;

import org.litepal.crud.LitePalSupport;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinwendong
 * @date 2018/6/19
 * descrption:用户数据库信息
 */
public class UserInfo extends LitePalSupport {
    // java.sql.Date 实例包装的毫秒值必须通过将小时、分钟、秒和毫秒设置为与该实例相关的特定时区中的零来“规范化”
    Date birthday;
    long curHomeId;
    long customerId;
    String customerPicData;
    String email;
    int gesturePsw;
    String loginName;
    int messagepushState;
    String mobilePhone;
    String nickName;
    String refreshToken;
    String  refreshTokenExpiredTime;
    int sex;
    long subscriberId;
    int suspendbuttonState;
    String token;
    Long tokenCreateTime;
    Long tokenExpiredTime;
    private List<HouseEntity> houseList = new ArrayList<>();
    private List<MessageEntity> messageList = new ArrayList<>();
    private List<TaskInfo> taskInfoList  = new ArrayList<>();
}
