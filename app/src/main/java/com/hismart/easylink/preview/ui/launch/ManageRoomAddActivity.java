package com.hismart.easylink.preview.ui.launch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.ToolbarBaseCompatActivity;

public class ManageRoomAddActivity extends ToolbarBaseCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room_add);
        setLeftButtonIsBack(true);
        setTitle("添加房间");
        setRightTextView("保存");
        setRightTextViewOnClickLisenter(new ToolbarBaseCompatActivity.RightTextClickListener() {
            @Override
            public void onClick() {

            }
        });
    }
}
