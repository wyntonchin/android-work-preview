package com.hismart.easylink.preview;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.inspector.database.SqliteDatabaseDriver;
import com.hismart.easylink.preview.util.SharedPreferenceUtils;
import com.hismart.easylink.preview.util.Utils;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description EasyLinkApplication 集成litepad，
 * 在使用Application 时，注意多进程使用时，Application会被重复初始化，那在onCreate中初始化的代码要加判断
 */

public class EasyLinkApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化facebook调试神器
        Stetho.initializeWithDefaults(this);
        //Utils 初始化（方便全局获取ApplicationContext,RefWatcher）
        Utils.init(this);

        SharedPreferenceUtils.init("easylink3.2");
        if(SharedPreferenceUtils.get(SharedPreferenceUtils.INIT_FLAG,false)){
            SharedPreferenceUtils.put(SharedPreferenceUtils.INIT_FLAG,true);
            SQLiteDatabase sqlite = Connector.getDatabase();
            //sqlite.insert()
        }
        Connector.getDatabase();

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
