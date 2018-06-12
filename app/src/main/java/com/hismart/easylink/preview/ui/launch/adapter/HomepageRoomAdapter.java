package com.hismart.easylink.preview.ui.launch.adapter;

import android.content.Context;
import android.view.ViewGroup;

/**
 * @author qinwendong
 * @date 2018/6/2
 * description SampleAdapter
 */
public class HomepageRoomAdapter extends BaseAdapter<Integer> {
    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new HomepageRoomViewHolder(context, parent);
    }
}
