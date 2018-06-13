package com.hismart.easylink.preview.ui.launch;

import android.content.Intent;
import android.os.Bundle;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.ToolbarBaseCompatActivity;

public class ManageRoomActivity extends ToolbarBaseCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);
        setLeftButtonIsBack(true);
        setTitle("房间管理");
        setRightTextView("添加房间");
        setRightTextViewOnClickLisenter(new ToolbarBaseCompatActivity.RightTextClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent();
                intent.setClass(ManageRoomActivity.this.getApplicationContext(), ManageRoomAddActivity.class);
                startActivity(intent);
            }
        });

    }
}
