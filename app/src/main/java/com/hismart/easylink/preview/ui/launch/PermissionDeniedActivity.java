package com.hismart.easylink.preview.ui.launch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.hismart.easylink.preview.ui.BaseCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名 PermissionDeniedActivity.java
 * 描述 强制用户打开权限的activity
 * 创建人 jochen.zhang
 * 创建时间 2018/2/7 13:45
 */
public class PermissionDeniedActivity extends BaseCompatActivity {
    private static final String TAG = "PermissionDeniedActivity";

    public static final String KEY_PERMISSIONS = "permissions";
    public static final String KEY_PERMISSIONS_STRING = "permissionsString";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static Runnable mFinishRunnable;
    private String[] PERMISSIONS;
    private List<String> passedPermissions = new ArrayList<>();
    private AlertDialog mAlertDialog;

    public static void startWith(Context context, String[] permissions, String permissionsString, @Nullable Runnable finishRunnable) {
        Intent intent = new Intent(context, PermissionDeniedActivity.class);
        intent.putExtra(KEY_PERMISSIONS_STRING, permissionsString);
        Bundle b = new Bundle();
        b.putStringArray(PermissionDeniedActivity.KEY_PERMISSIONS, permissions);
        intent.putExtras(b);
        //部分手机或者版本使用ApplicationContext无法startActivity，需要添加FLAG_ACTIVITY_NEW_TASK
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mFinishRunnable = finishRunnable;
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            PERMISSIONS = bundle.getStringArray(KEY_PERMISSIONS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions(PERMISSIONS);
        }
    }

    @Override
    protected void onPermissionGranted(String permission) {
        super.onPermissionGranted(permission);
        if (!passedPermissions.contains(permission)) {
            passedPermissions.add(permission);
        }
        if (passedPermissions.size() == PERMISSIONS.length) {
            if (null != mFinishRunnable) {
                mFinishRunnable.run();
                mFinishRunnable = null;
            }
            finish();
        }
    }

    @Override
    protected void onPermissionFailed(String permission) {
        super.onPermissionFailed(permission);
        showDialogTipUserGoToAppSetting();
    }


    // 提示用户去应用设置界面手动开启权限
    private void showDialogTipUserGoToAppSetting() {
        if (null == mAlertDialog) {
            mAlertDialog = new AlertDialog.Builder(this)
                    .setTitle("权限不可用")
                    .setMessage("请在-应用设置-权限中，允许当前应用使用" + getIntent().getStringExtra(KEY_PERMISSIONS_STRING) + "权限")
                    .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 跳转到应用设置界面
                            goToAppSetting();
                        }
                    })
                    .setCancelable(false)
                    .show();
        } else if (!mAlertDialog.isShowing()) {
            mAlertDialog.show();
        }
    }

    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_EXTERNAL_STORAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkPermissions(PERMISSIONS);
            }
        }
    }
}
