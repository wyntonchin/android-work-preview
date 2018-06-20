package com.hismart.easylink.preview.ui.launch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.databases.RoomEntity;

/**
 * @author qinwendong
 * @date 2018/6/2
 * description SampleViewHolder
 */
public class HomepageRoomViewHolder extends BaseViewHolder<RoomEntity> {

    TextView mRoomTv;
    ImageView mRoomImg;

    HomepageRoomViewHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.adapter_homepage_room);
        mRoomTv = itemView.findViewById(R.id.room_name);
        mRoomImg = itemView.findViewById(R.id.room_img);
    }

    @Override
    protected void bindData(final RoomEntity itemValue, final int position, final BaseAdapter.OnItemClickListener listener) {
        if (itemValue != null) {
            //mRoomTv.setText(itemValue);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) {
                    return;
                }
                listener.onItemClick(itemValue, v.getId(), position);
            }
        });
    }
}
