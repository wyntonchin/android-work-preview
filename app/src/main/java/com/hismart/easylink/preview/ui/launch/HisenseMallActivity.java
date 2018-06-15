package com.hismart.easylink.preview.ui.launch;

import android.os.Bundle;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.BaseToolbarCompatActivity;

public class HisenseMallActivity extends BaseToolbarCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hisense_mall);
        setLeftButtonIsBack(true);
        setLeftTitle("关闭");
        //setMiddleTitle("关闭");
    }
    @Override
    protected void pressBackKeyEvent() {
        //TODO
    }
}
