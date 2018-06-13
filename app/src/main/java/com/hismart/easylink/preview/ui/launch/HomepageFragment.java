package com.hismart.easylink.preview.ui.launch;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.StatusBarUtil;
import com.hismart.easylink.preview.ui.launch.adapter.HomepageDeviceAdapter;
import com.hismart.easylink.preview.ui.launch.adapter.HomepageRoomAdapter;
import com.hismart.easylink.preview.ui.launch.dummy.DummyContent;
import com.hismart.easylink.preview.ui.launch.entity.DeviceEntity;
import com.hismart.easylink.preview.ui.launch.entity.RoomEntity;

import android.support.v7.widget.ListPopupWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author qinwendong.
 * @date 2018/5/30
 * description HomepageFragment 使用缓存机制，防止重复创建fragment
 */
public class HomepageFragment extends Fragment {
    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefresh;
    // fragment缓存视图内容，防止重复创建，耗费时间
    private View mContentView;

    public HomepageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mContentView == null) {
            initContentView(inflater, container);
        }
        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }
        return mContentView;
    }

    private void initContentView(LayoutInflater inflater, ViewGroup container) {
        mContentView = inflater.inflate(R.layout.fragment_main_homepage, container, false);
        if (getActivity() != null) {
            //动态设置statusBar的高度，适配不同手机
            View statusBar = mContentView.findViewById(R.id.fake_status_bar);
            statusBar.getLayoutParams().height = StatusBarUtil.getStatusBarHeight(getActivity().getApplicationContext());
        }

        mToolbar = mContentView.findViewById(R.id.toolbar);
        ImageButton dropdown = mContentView.findViewById(R.id.dropdown_img);
        dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListPopupWindow(mToolbar);
            }
        });
        ImageButton messageImg = mToolbar.findViewById(R.id.msg_img);
        messageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        final ImageButton moreImg = mToolbar.findViewById(R.id.more_img);
        moreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(moreImg);
            }
        });
        mSwipeRefresh = mContentView.findViewById(R.id.swipe_refresh);
        //设置滑动生效的距离
        mSwipeRefresh.setDistanceToTriggerSync(300);
        mSwipeRefresh.setColorSchemeResources(R.color.colorHomepageAccent);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    //TODO
                    Thread.sleep(1000);
                    mSwipeRefresh.setRefreshing(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ImageView roomSetting = mContentView.findViewById(R.id.room_setting);
        roomSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity().getApplicationContext(), ManageRoomActivity.class);
                startActivity(intent);
            }
        });
        initRoomRecycler(mContentView);
        initDeviceRecycler(mContentView);
        //TODO 考虑延迟加载
        //PopupWindow();
    }

    private void initRoomRecycler(View contentView) {
        RecyclerView recyclerView = contentView.findViewById(R.id.room_list);
        LinearLayoutManager linearManager = new LinearLayoutManager(contentView.getContext());
        linearManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearManager);
        HomepageRoomAdapter adapter = new HomepageRoomAdapter();
        adapter.setOnClickListener(new HomepageRoomAdapter.OnItemClickListener<RoomEntity>() {
            @Override
            public void onItemClick(RoomEntity itemValue, int viewID, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
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
    }

    private void initDeviceRecycler(View contentView) {
        RecyclerView recyclerView = contentView.findViewById(R.id.device_list);
        GridLayoutManager gridManager = new GridLayoutManager(contentView.getContext(), 2);
        gridManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridManager);
        final HomepageDeviceAdapter adapter = new HomepageDeviceAdapter();
        //recyclerView.setAdapter(new CommonSenceRecyclerViewAdapter(DummyContent.ITEMS));
        recyclerView.setAdapter(adapter);
        final List<DeviceEntity> list = new ArrayList<>();
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        list.add(new DeviceEntity());
        adapter.refreshData(list);

        setItemTouchHelper(recyclerView, adapter, list);
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
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    //viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                    //获取系统震动服务
                    if (getActivity() != null) {
                        Vibrator vib = (Vibrator) getActivity().getSystemService(Service.VIBRATOR_SERVICE);
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
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                //viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void showListPopupWindow(View view) {
        String items[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        final ListPopupWindow listPopupWindow = new ListPopupWindow(this.getActivity());
        listPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        listPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // ListView适配器
        listPopupWindow.setAdapter(new ArrayAdapter<>(this.getActivity().getApplicationContext(), R.layout.homepage_popup_list_item, items));
        // 选择item的监听事件
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Toast.makeText(HomepageFragment.this.getActivity().getApplicationContext(), "选择:" + pos, Toast.LENGTH_SHORT).show();
                listPopupWindow.dismiss();
            }
        });

        // ListPopupWindow的锚,弹出框的位置是相对当前View的位置
        listPopupWindow.setAnchorView(view);
        // ListPopupWindow 距锚view的距离
        listPopupWindow.setHorizontalOffset(0);
        listPopupWindow.setVerticalOffset(0);
        //外边界点击关闭
        listPopupWindow.setModal(true);
        listPopupWindow.show();
    }

    private void showPopupWindow(View anchor) {
        final View popupView = getLayoutInflater().inflate(R.layout.layout_homepage_more_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        //设置popup拦截touch事件，否则弹出时点击按钮区域，事件依然会传递到按钮
        popupWindow.setFocusable(true);

        View manualAddView = popupView.findViewById(R.id.manual_add);
        manualAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    popupWindow.dismiss();
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(), ManuaAddDeviceActivity.class);
                    startActivity(intent);
                }
            }
        });
        View scanCodeView = popupView.findViewById(R.id.scan_code);
        scanCodeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        popupWindow.showAsDropDown(anchor, 0, 28);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContentView = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
