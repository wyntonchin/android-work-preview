package com.hismart.easylink.preview.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.hismart.easylink.preview.util.LogUtils;
import com.hismart.easylink.preview.eventbus.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author qinwendong
 * @date 2018/5/30
 * description Activity基类
 */
public abstract class BaseCompatActivity extends AppCompatActivity {
    private static String TAG = BaseCompatActivity.class.getSimpleName();

    private boolean isActive = true;
    private boolean isDestroy = false;

    private Handler m_handler = null;

    /*********************************************************************************************************************************
     * AppCompatActivity 重写函数
     ********************************************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_handler = new MyHandler(this);
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
        //ActivityStack.getInstance().addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isDestroy = false;
        isActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
    }

    @Override
    protected void onDestroy() {
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
        //销毁handlers
        try {
            m_handler.removeCallbacksAndMessages(null);
            m_handler = null;
        } catch (Exception e) {
        }
        isDestroy = true;
        isActive = false;
        //ActivityStack.getInstance().finishActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*********************************************************************************************************************************
     * 权限请求 相关函数
     * 权限组列表：
     * Android6.0只用申请权限组中一个权限及获得全部权限
     * Android8.0需要全部申请权限组权限，但是只会申请第一个权限时提示，后面不会提示
     *
     * // 读写日历。
     * Manifest.permission.READ_CALENDAR,
     * Manifest.permission.WRITE_CALENDAR
     * // 相机。
     * Manifest.permission.CAMERA
     * // 读写联系人。
     * Manifest.permission.READ_CONTACTS,
     * Manifest.permission.WRITE_CONTACTS,
     * Manifest.permission.GET_ACCOUNTS
     * // 读位置信息。
     * Manifest.permission.ACCESS_FINE_LOCATION,
     * Manifest.permission.ACCESS_COARSE_LOCATION
     * // 使用麦克风。
     * Manifest.permission.RECORD_AUDIO
     * // 读电话状态、打电话、读写电话记录。
     * Manifest.permission.READ_PHONE_STATE,
     * Manifest.permission.CALL_PHONE,
     * Manifest.permission.READ_CALL_LOG,
     * Manifest.permission.WRITE_CALL_LOG,
     * Manifest.permission.ADD_VOICEMAIL,
     * Manifest.permission.USE_SIP,
     * Manifest.permission.PROCESS_OUTGOING_CALLS
     * // 传感器。
     * Manifest.permission.BODY_SENSORS
     * // 读写短信、收发短信。
     * Manifest.permission.SEND_SMS,
     * Manifest.permission.RECEIVE_SMS,
     * Manifest.permission.READ_SMS,
     * Manifest.permission.RECEIVE_WAP_PUSH,
     * Manifest.permission.RECEIVE_MMS,
     * Manifest.permission.READ_CELL_BROADCASTS
     * // 读写存储卡。
     * Manifest.permission.READ_EXTERNAL_STORAGE,
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     ********************************************************************************************************************************/
    private static final int REQUEST_PERMISSIONS_CODE = 0x1000;

    /**
     * 权限请求相关函数
     *
     * @param permissions String[] 所有请求
     */
    protected void checkPermissions(String[] permissions) {
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(this, deniedPermissions, REQUEST_PERMISSIONS_CODE);
        }
    }

    @Override
    public final void onRequestPermissionsResult(int requestCode
            , @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSIONS_CODE:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            onPermissionGranted(permissions[i]);
                        } else {
                            onPermissionFailed(permissions[i]);
                        }
                    }
                }
                break;
                default:
        }
    }

    /**
     * 权限允许
     *
     * @param permission permission String
     */
    protected void onPermissionGranted(String permission) {
        if (isActive) {
            LogUtils.d(TAG, String.format(Locale.getDefault(), "%s %s", permission, "request_success"));
        }
    }

    /**
     * 权限拒绝
     *
     * @param permission permission String
     */
    protected void onPermissionFailed(String permission) {
        if (isActive) {
            LogUtils.d(TAG, String.format(Locale.getDefault(), "%s %s", permission, "request_failed"));
        }
    }

    protected static final int REQUEST_OPEN_BT_CODE = 0x1001;
    protected static final int REQUEST_OPEN_GPS_CODE = 0x1002;

    /**
     * 打开蓝牙
     */
    public void openBluetooth() {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent, REQUEST_OPEN_BT_CODE);
    }


    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @return true 表示开启
     */
    public boolean isGPSOpen() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    /**
     * 强制帮用户打开GPS
     */
    public void openGPS() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, REQUEST_OPEN_GPS_CODE);
    }

    /*********************************************************************************************************************************
     * Handler 相关函数
     ********************************************************************************************************************************/

    /**
     * 移除handler消息
     *
     * @param what Value to assign to the returned Message.what field.
     */
    public final void removeMessages(int what) {
        Handler handler = getHandler();
        if (handler == null)
            return;
        handler.removeMessages(what);
    }

    /**
     * 发送消息给handler
     *
     * @param what Value to assign to the returned Message.what field.
     * @param obj  An arbitrary object to send to the recipient.
     */
    public final void sendMessage(int what, Object obj) {
        Handler handler = getHandler();
        if (handler == null)
            return;
        Message msg = handler.obtainMessage(what);
        if (obj != null) {
            msg.obj = obj;
        }
        handler.sendMessage(msg);
    }

    /**
     * 发送消息给handler
     *
     * @param what Value to assign to the returned Message.what field.
     */
    public final void sendMessage(int what) {
        if (what == -1)
            return;
        sendMessage(what, null);
    }

    /**
     * 发送消息给handler
     *
     * @param what        Value to assign to the returned Message.what field.
     * @param obj         An arbitrary object to send to the recipient.
     * @param delayMillis 延时
     */
    public final void sendMessage(int what, Object obj, int delayMillis) {
        Handler handler = getHandler();
        if (handler == null)
            return;
        Message msg = handler.obtainMessage(what);
        if (obj != null) {
            msg.obj = obj;
        }
        handler.sendMessageDelayed(msg, delayMillis);
    }

    /**
     * 延迟发送消息给handler
     *
     * @param what        Value to assign to the returned Message.what field.
     * @param delayMillis 延时
     */
    public final void sendDelayMessage(int what, int delayMillis) {
        sendMessage(what, null, delayMillis);
    }

    /**
     * handler的Post
     *
     * @param r Runnable
     * @return Returns true if the Runnable was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     */
    public final boolean post(Runnable r) {
        Handler h = getHandler();
        if (h == null) {
            return false;
        }
        return h.post(r);
    }

    /**
     * handler的postDelay
     *
     * @param r     Runnable
     * @param delay 延时
     * @return true if the Runnable was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.  Note that a
     * result of true does not mean the Runnable will be processed --
     * if the looper is quit before the delivery time of the message
     * occurs then the message will be dropped.
     */
    public final boolean postDelayed(Runnable r, long delay) {
        Handler h = getHandler();
        if (h == null) {
            return false;
        }
        return h.postDelayed(r, delay);
    }

    /**
     * handler重新postDelay
     * 先去除Runnable，后重新postDelayed
     *
     * @param r     Runnable
     * @param delay 延时
     * @return true if the Runnable was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.  Note that a
     * result of true does not mean the Runnable will be processed --
     * if the looper is quit before the delivery time of the message
     * occurs then the message will be dropped.
     */
    public final boolean postDelayedOnce(Runnable r, long delay) {
        Handler h = getHandler();
        if (h == null)
            return false;
        h.removeCallbacks(r);
        return h.postDelayed(r, delay);
    }

    /**
     * 获取handler
     *
     * @return
     */
    public Handler getHandler() {
        return m_handler;
    }

    /**
     * 子类重写处理Handler方法
     *
     * @param msg 接收到的Message
     */
    protected void processMessage(Message msg) {

    }

    private static class MyHandler extends Handler {
        private final WeakReference<BaseCompatActivity> m_activity;

        MyHandler(BaseCompatActivity ac) {
            m_activity = new WeakReference<>(ac);
        }

        @Override
        public void handleMessage(Message msg) {
            if (m_activity.get() != null) {
                m_activity.get().processMessage(msg);
            }
        }
    }

    /*********************************************************************************************************************************
     * 其他函数
     ********************************************************************************************************************************/

    /**
     * 获取状态
     *
     * @return
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * 获取是否销毁的状态
     *
     * @return
     */
    public boolean isDestroy() {
        return isDestroy;
    }

    /**
     * 获取上一级Activity
     *
     * @return
     */
    public final Activity getViewRootActivity() {
        Activity ret = this;
        if (ret.getParent() != null) {
            ret = ret.getParent();
        }
        return ret;
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    /**
     * 监听EventBus消息
     *
     * @param event 接收到的event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    //注意Event是本地定义的
    public void onEvent(Event event) {
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(Event event) {
    }
}
