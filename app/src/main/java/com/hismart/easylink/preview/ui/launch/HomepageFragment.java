package com.hismart.easylink.preview.ui.launch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.PopupWindow;
import android.widget.Toast;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.StatusBarUtil;
import com.hismart.easylink.preview.ui.launch.adapter.HomepageRoomAdapter;
import com.hismart.easylink.preview.ui.launch.dummy.DummyContent;
import com.hismart.easylink.preview.ui.launch.entity.RoomEntity;

import android.support.v7.widget.ListPopupWindow;


/**
 * @author qinwendong.
 * @date 2018/5/30
 * description HomepageFragment 使用缓存机制，防止重复创建fragment
 */
public class HomepageFragment extends Fragment {
    private Toolbar mToolbar;
   // private PopupWindow popupWindow;
    private SwipeRefreshLayout mSwipeRefresh;
    private View mContentView; // 缓存视图内容
    private ImageButton mDropdown;
    private ImageButton mMsgImg;
    private ImageButton mMoreImg;
/*    private View mManualAddView;
    private View mScanCodeView;*/

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
        View statusBar = mContentView.findViewById(R.id.fake_status_bar);
        if (getActivity() != null) {
            //动态设置statusBar的高度，适配不同手机
            statusBar.getLayoutParams().height = StatusBarUtil.getStatusBarHeight(getActivity().getApplicationContext());
        }

        mToolbar = mContentView.findViewById(R.id.toolbar);
        mDropdown = mContentView.findViewById(R.id.dropdown_img);
        mDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListPopupWindow(mToolbar);
            }
        });
        mMsgImg = mToolbar.findViewById(R.id.msg_img);
        mMoreImg = mToolbar.findViewById(R.id.more_img);
        mMoreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
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
    }

    private void initDeviceRecycler(View contentView) {
        RecyclerView recyclerView = contentView.findViewById(R.id.device_list);
        GridLayoutManager gridManager = new GridLayoutManager(contentView.getContext(), 2);
        gridManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridManager);
        recyclerView.setAdapter(new CommonSenceRecyclerViewAdapter(DummyContent.ITEMS));
    }

    public void showListPopupWindow(View view) {
        String items[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9","10","11","12","13","14","15" };
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

    private void showPopupWindow() {
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
                    intent.setClass(getActivity().getApplicationContext(),ManuaAddDeviceActivity.class);
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
        popupWindow.showAsDropDown(mMoreImg, 0, 28);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
