package com.hismart.easylink.preview.ui.launch.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.hismart.easylink.preview.databases.RoomEntity;

/**
 * @author qinwendong
 * @date 2018/6/2
 * description SampleAdapter
 */
public class ManageRoomAdapter extends BaseAdapter<RoomEntity> {
    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new ManageRoomViewHolder(context, parent);
    }
}
