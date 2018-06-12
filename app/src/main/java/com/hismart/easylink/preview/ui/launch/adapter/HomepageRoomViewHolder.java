package com.hismart.easylink.preview.ui.launch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hismart.easylink.preview.R;

/**
 * @author qinwendong
 * @date 2018/6/2
 * description SampleViewHolder
 */
public class HomepageRoomViewHolder extends BaseViewHolder<String> {

    //TextView mIsTvContent;

    public HomepageRoomViewHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.homepage_room_list_item);
        //mIsTvContent = (TextView)itemView.findViewById(R.id.is_tv_content);
    }

    @Override
    protected void bindData(final String itemValue, final int position, final BaseAdapter.OnItemClickListener listener) {
        if (itemValue != null) {
            //mIsTvContent.setText(itemValue);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener == null){
                    return;
                }
                listener.onItemClick(itemValue , v.getId() , position);
            }
        });
    }
}
