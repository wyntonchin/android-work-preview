package com.hismart.easylink.preview.ui.launch;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.StatusBarUtil;
import com.hismart.easylink.preview.ui.launch.dummy.DummyContent;


/**
 * @author qinwendong.
 * @date 2018/5/30
 * description HomepageFragment 使用缓存机制，防止重复创建fragment
 */
public class HomepageFragment extends Fragment {
    private Toolbar mToolbar;
    private PopupWindow mPopupWindow;
    private SwipeRefreshLayout mSwipeRefresh;
    private View mContentView; // 缓存视图内容
    private ImageView mMsgImg;
    private ImageView mMoreImg;
    private View mManualAddView;
    private View mScanCodeView;

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
        if(getActivity() != null){
            //动态设置statusBar的高度，适配不同手机
            statusBar.getLayoutParams().height = StatusBarUtil.getStatusBarHeight(getActivity().getApplicationContext());
        }

        mToolbar = mContentView.findViewById(R.id.toolbar);
        mMsgImg = mToolbar.findViewById(R.id.msg_img);
        mMoreImg = mToolbar.findViewById(R.id.more_img);
        mMoreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mPopupWindow.isShowing()) {
                    mPopupWindow.showAsDropDown(mMoreImg, 0, 28);
                    //mPopupWindow.showAtLocation(mToolbar, Gravity.TOP|Gravity.END, 0,0);
   /*                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        mPopupWindow.showAsDropDown(mMoreImg,-10,20);
                    }else{
                        mPopupWindow.showAsDropDown(mMoreImg);
                    }*/
                } else {
                    //mPopupWindow.dismiss();
                }
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
        // 代替Actionbar
        //((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);


        initRoomRecycler(mContentView);
        initDeviceRecycler(mContentView);
        //考虑延迟加载
        initPopupWindow();
    }

    private void initPopupWindow(){
        View popupView = getLayoutInflater().inflate(R.layout.layout_homepage_more_popup, null);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        //设置popup拦截touch事件，否则弹出时点击按钮区域，事件依然会传递到按钮
        mPopupWindow.setFocusable(true);

        mManualAddView = popupView.findViewById(R.id.manual_add);
        mManualAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mScanCodeView = popupView.findViewById(R.id.scan_code);
        mScanCodeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initRoomRecycler(View contentView) {
        RecyclerView recyclerView = contentView.findViewById(R.id.room_list);
        LinearLayoutManager linearManager = new LinearLayoutManager(contentView.getContext());
        linearManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(new CommonSenceRecyclerViewAdapter(DummyContent.ITEMS));
    }

    private void initDeviceRecycler(View contentView) {
        RecyclerView recyclerView = contentView.findViewById(R.id.device_list);
        GridLayoutManager gridManager = new GridLayoutManager(contentView.getContext(), 2);
        gridManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridManager);
        recyclerView.setAdapter(new CommonSenceRecyclerViewAdapter(DummyContent.ITEMS));
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
