package com.hismart.easylink.preview.ui.launch.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.hismart.easylink.preview.ui.launch.entity.DeviceEntity;

/**
 * @author qinwendong
 * @date 2018/6/12
 * descrption:
 */
public class HomepageDeviceAdapter extends BaseAdapter<DeviceEntity> {
    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new HomepageDeviceViewHolder(context, parent);
    }
}
