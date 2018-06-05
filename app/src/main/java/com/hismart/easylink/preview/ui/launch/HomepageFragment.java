package com.hismart.easylink.preview.ui.launch;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.launch.dummy.DummyContent;

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description HomepageFragment 使用缓存机制，防止重复创建fragment
 */
public class HomepageFragment extends Fragment {
    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefresh;
    private View mContentView; // 缓存视图内容

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
            mContentView = inflater.inflate(R.layout.fragment_main_homepage, container, false);
            mToolbar = mContentView.findViewById(R.id.toolbar);
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
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            initRoomRecycler(mContentView);
            initDeviceRecycler(mContentView);
        }
        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }
        return mContentView;
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
        GridLayoutManager gridManager = new GridLayoutManager(contentView.getContext(),2);
        gridManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridManager);
        recyclerView.setAdapter(new CommonSenceRecyclerViewAdapter(DummyContent.ITEMS));
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
