package com.hismart.easylink.preview.util;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description 描述 Utils初始化类
 */
public class RefWatcherUtils {
    //ApplicationContext
    private static Application context;
    //LeakCanary 监听对象
    private static RefWatcher refWatcher;

    private RefWatcherUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        //获取ApplicationContext
        RefWatcherUtils.context = (Application) context.getApplicationContext();

        //LeakCanary 初始化（内存泄漏检测）
        if (LeakCanary.isInAnalyzerProcess(RefWatcherUtils.context)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(RefWatcherUtils.context);
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    /**
     * 获取RefWatcher监听对象
     * LeakCanary默认监听Activity
     *
     * 使用RefWatcher监听Fragment
     * @Override
     * public void onDestroy() {
     * super.onDestroy();
     * Utils.getRefWatcher().watch(this);
     * }
     * @return RefWatcher
     */
    public static RefWatcher getRefWatcher() {
        if (context != null) return refWatcher;
        throw new NullPointerException("u should init first");
    }
}
