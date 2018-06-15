package com.hismart.easylink.preview.ui.launch;

import android.content.Intent;
import android.os.Bundle;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.BaseToolbarCompatActivity;

public class ManageRoomActivity extends BaseToolbarCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);
        setLeftButtonIsBack(true);
        setMiddleTitle("房间管理");
        setRightTextView("添加房间");
        setRightTextViewOnClickLisenter(new BaseToolbarCompatActivity.RightTextClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent();
                intent.setClass(ManageRoomActivity.this.getApplicationContext(), ManageRoomAddActivity.class);
                startActivity(intent);
            }
        });

    }
}
