package com.hismart.easylink.preview.ui.launch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.hismart.easylink.preview.ui.BaseCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description PermissionDeniedActivity强制用户启动权限，否则有持续弹出
 */
public class PermissionDeniedActivity extends BaseCompatActivity {
    private static final String TAG = "PermissionDeniedActivity";

    public static final String KEY_PERMISSIONS = "permissions";
    public static final String KEY_PERMISSION_DIALOG_MESSAGE = "dialog_message";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private AlertDialog mAlertDialog;

    public static void startWith(@NonNull Context context, String[] permissions, String dialogMessage) {
        Intent intent = new Intent(context, PermissionDeniedActivity.class);
        intent.putExtra(KEY_PERMISSION_DIALOG_MESSAGE, dialogMessage);
        Bundle b = new Bundle();
        b.putStringArray(PermissionDeniedActivity.KEY_PERMISSIONS, permissions);
        intent.putExtras(b);
        //部分手机或者版本使用ApplicationContext无法startActivity，需要添加FLAG_ACTIVITY_NEW_TASK
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String[] permissions = bundle.getStringArray(KEY_PERMISSIONS);
            checkPermissions(permissions);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onPermissionGranted(String permission) {
        super.onPermissionGranted(permission);
            finish();
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
                    .setMessage("请在-应用设置-权限中，允许当前应用使用" + getIntent().getStringExtra(KEY_PERMISSION_DIALOG_MESSAGE) + "权限")
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
/*        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkPermissions(PERMISSIONS);
            }
        }*/
    }
}
