package com.hismart.easylink.preview.ui.launch.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.launch.entity.RoomEntity;

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
