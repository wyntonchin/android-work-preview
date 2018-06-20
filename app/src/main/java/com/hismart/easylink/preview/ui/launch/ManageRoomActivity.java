package com.hismart.easylink.preview.ui.launch;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.BaseToolbarCompatActivity;
import com.hismart.easylink.preview.ui.launch.adapter.ManageRoomAdapter;
import com.hismart.easylink.preview.ui.launch.adapter.RecyclerItemClickListener;
import com.hismart.easylink.preview.databases.RoomEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageRoomActivity extends BaseToolbarCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);
        setLeftButtonIsBack(true);
        setMiddleTitle("房间管理");
        setRightText("添加房间");
        setRightTextOnClickListener(new ToolBarTextClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent();
                intent.setClass(ManageRoomActivity.this.getApplicationContext(), ManageAddRoomActivity.class);
                startActivity(intent);
            }
        });
        initRoomRecycler();
    }

    private void initRoomRecycler() {
        RecyclerView recyclerView = findViewById(R.id.room_list);
        LinearLayoutManager linearManager = new LinearLayoutManager(ManageRoomActivity.this.getApplicationContext());
        linearManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearManager);
        ManageRoomAdapter adapter = new ManageRoomAdapter();
        adapter.setOnClickListener(new ManageRoomAdapter.OnItemClickListener<RoomEntity>() {
            @Override
            public void onItemClick(RoomEntity itemValue, int viewID, int position) {

            }
        });
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {

            }

            @Override
            public void onItemLOngClick(RecyclerView.ViewHolder viewHolder) {
                viewHolder.itemView.findViewById(R.id.right_delete).setVisibility(View.VISIBLE);
            }
        });
        List<RoomEntity> list = new ArrayList<>();
        list.add(new RoomEntity());
        list.add(new RoomEntity());
        list.add(new RoomEntity());
        list.add(new RoomEntity());
        list.add(new RoomEntity());
        list.add(new RoomEntity());
        list.add(new RoomEntity());
        list.add(new RoomEntity());
        list.add(new RoomEntity());
        adapter.refreshData(list);
        setItemTouchHelper(recyclerView, adapter, list);;
    }


    /**
     * 拖动排序功能
     */
    private void setItemTouchHelper(final RecyclerView recyclerView, final RecyclerView.Adapter adapter, final List list) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                int dragFlags = 0;
                int swipeFlags = 0;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    swipeFlags = 0;
                } else {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipeFlags = 0;
                }

                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到拖动ViewHolder的position
                int fromPosition = viewHolder.getAdapterPosition();
                //得到目标ViewHolder的position
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(list, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(list, i, i - 1);
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition);

                viewHolder.itemView.findViewById(R.id.right_delete).setVisibility(View.INVISIBLE);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    //viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                    Vibrator vib = (Vibrator) ManageRoomActivity.this.getSystemService(Service.VIBRATOR_SERVICE);
                    //震动70毫秒
                    if (vib != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            VibrationEffect effect = VibrationEffect.createOneShot(70, VibrationEffect.DEFAULT_AMPLITUDE);
                            vib.vibrate(effect);
                        } else {
                            vib.vibrate(80);
                        }
                    }
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                //viewHolder.itemView.setBackgroundColor(Color.WHITE);
                viewHolder.itemView.findViewById(R.id.right_delete).setVisibility(View.INVISIBLE);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        //为RecyclerView注册ContextMenu需设置longclickable属性
        //registerForContextMenu(recyclerView);
    }
}
