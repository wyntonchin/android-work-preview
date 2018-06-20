package com.hismart.easylink.preview.ui.launch.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.databases.RoomEntity;

/**
 * @author qinwendong
 * @date 2018/6/2
 * description SampleViewHolder
 */
public class ManageRoomViewHolder extends BaseViewHolder<RoomEntity> {

    ManageRoomViewHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.adapter_manage_room);
    }

    @Override
    protected void bindData(final RoomEntity itemValue, final int position, final BaseAdapter.OnItemClickListener listener) {
        if (itemValue != null) {
            //mRoomTv.setText(itemValue);
        }
/*        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) {
                    return;
                }
                listener.onItemClick(itemValue, v.getId(), position);
            }
        });*/
    }
}
