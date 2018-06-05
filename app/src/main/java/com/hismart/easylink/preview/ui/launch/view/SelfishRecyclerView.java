package com.hismart.easylink.preview.ui.launch.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author qinwendong
 * @date 2018/6/5
 * descrption: 自私的SelfishRecyclerView ，将所有的touch时间独自消费掉
 */
public class SelfishRecyclerView extends RecyclerView {
    public SelfishRecyclerView(Context context) {
        this(context, null);
    }

    public SelfishRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public SelfishRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev){
        super.onTouchEvent(ev);
        return true;
    }


}
