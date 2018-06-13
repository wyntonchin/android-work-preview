package com.hismart.easylink.preview.ui.launch;

import android.os.Bundle;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.ToolbarBaseCompatActivity;

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description ManuaAddDeviceActivity手动添加设备界面
 */
public class ManuaAddDeviceActivity extends ToolbarBaseCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manua_add_device);
        setLeftButtonIsBack(true);
        setTitle("选择设备型号");
        hideToolbarLine();
    }
}
