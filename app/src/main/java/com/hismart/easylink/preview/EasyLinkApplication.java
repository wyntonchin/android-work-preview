package com.hismart.easylink.preview;

import android.app.Application;
import android.content.Intent;

import com.hismart.easylink.preview.util.Utils;

import org.greenrobot.eventbus.EventBus;

/**
 * 文件名：MyApplication
 * 描述：重写Application
 * 创建人：jochen.zhang
 * 创建时间：2018/1/16.
 */

public class EasyLinkApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Utils 初始化（方便全局获取ApplicationContext,RefWatcher）
        Utils.init(this);

        //ToastUtils 初始化（新Toast显示时若旧Toast还在显示，直接将旧Toast Cancel掉）
        //ToastUtils.init(true);

        //CrashHandler 初始化（保存崩溃日志到本地,以及是否崩溃重启）
        //保存CPU数据时若不关闭进程，就算退出了所有Activity也会继续保存CPU数据，因此必须在退出后结束进程
        //CrashHandler.getInstance().init(this, true, false);

        //EventBus 初始化（在debug模式下打印信息）
        EventBus.builder().logSubscriberExceptions(BuildConfig.DEBUG).logNoSubscriberMessages(BuildConfig.DEBUG).installDefaultEventBus();

        //开启测试服务
        //startService(new Intent(this, MyService.class));
    }
}
