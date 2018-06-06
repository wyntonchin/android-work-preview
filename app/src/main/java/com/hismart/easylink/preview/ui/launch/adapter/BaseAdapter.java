package com.hismart.easylink.preview.ui.launch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**

 * <p/>
 * Created by dean
 */
/**
 * @author qinwendong
 * @date 2018/5/30
 * description Adapter基类 适用于只有单个Item的RecyclerView
 * http://android.jobbole.com/84563/
 */
public abstract class BaseAdapter<V> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 装载了每个Item的Value的列表
     */
    private List<V> mValueList;

    /**
     * 我写的一个接口，通过回调分发点击事件
     */
    private OnItemClickListener<V> mOnItemClickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolder(parent.getContext(), parent);
    }

    @Override
    @SuppressWarnings("unchecked")//一定会是BaseViewHolder的子类，因为createViewHolder()的返回值
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).setData(mValueList.get(position), position, mOnItemClickListener);
    }

    /**
     * 设置每个Item的点击事件
     *
     * @param listener
     */
    public void setOnClickListener(OnItemClickListener<V> listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 刷新数据
     *
     * @param valueList 新的数据列表
     */
    public void refreshData(List<V> valueList) {
        this.mValueList = valueList;
        notifyDataSetChanged();
    }

    public List<V> getData() {
        return mValueList;
    }

    @Override
    public int getItemCount() {
        return mValueList == null ? 0 : mValueList.size();
    }

    /**
     * 生成ViewHolder
     *
     * @param context
     * @param parent
     * @return
     */
    protected abstract BaseViewHolder createViewHolder(Context context, ViewGroup parent);


    public interface OnItemClickListener<V> {

        /**
         * 当item被点击的时候进行事件分发
         *
         * @param itemValue 点击的item传递的值
         * @param viewID    点击控件的id
         * @param position  被点击的item的位置
         */
        void onItemClick(V itemValue, int viewID, int position);
    }
}

