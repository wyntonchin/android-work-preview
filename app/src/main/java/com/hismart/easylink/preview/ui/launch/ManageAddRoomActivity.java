package com.hismart.easylink.preview.ui.launch;

import android.os.Bundle;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.BaseToolbarCompatActivity;

public class ManageAddRoomActivity extends BaseToolbarCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_add_room);
        setLeftButtonIsBack(true);
        setMiddleTitle("添加房间");
        setRightText("保存");
        setRightTextOnClickListener(new ToolBarTextClickListener() {
            @Override
            public void onClick() {

            }
        });
    }
}
