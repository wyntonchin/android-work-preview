package com.hismart.easylink.preview.ui.launch;

import android.os.Bundle;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.BaseToolbarCompatActivity;

public class ManageRoomAddActivity extends BaseToolbarCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room_add);
        setLeftButtonIsBack(true);
        setMiddleTitle("添加房间");
        setRightTextView("保存");
        setRightTextViewOnClickLisenter(new BaseToolbarCompatActivity.RightTextClickListener() {
            @Override
            public void onClick() {

            }
        });
    }
}
