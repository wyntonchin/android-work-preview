package com.hismart.easylink.preview.ui.launch;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntelligenceFragment extends Fragment {
    /**
     * mViewContent 缓存视图内容
     */
    private View mContentView;

    TabLayout tabLayout;
    ViewPager viewPager;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<String> mTitles = new ArrayList<>();
    private FragmentManager fragmentManager;

    public IntelligenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_main_intelligence, container, false);
            if (getActivity() != null) {
                //动态设置statusBar的高度，适配不同手机
                View statusBar = mContentView.findViewById(R.id.fake_status_bar);
                statusBar.getLayoutParams().height = StatusBarUtil.getStatusBarHeight(getActivity().getApplicationContext());
            }
            tabLayout = mContentView.findViewById(R.id.tab_layout);
            viewPager = mContentView.findViewById(R.id.view_pager);
            //指定加载的页数，不回收数据
            viewPager.setOffscreenPageLimit(2);
            initTabLayout();
        }
        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }
        return mContentView;
    }

    private void initTabLayout() {

        mTitles.add("常用");
        mTitles.add("我的");

        fragmentManager = getChildFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentList.add(new CommonSceneFragment());
        fragmentList.add(new CommonSceneFragment());



        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(fragmentManager, fragmentList, mTitles);
        tabLayout.setSelected(true);
        viewPager.setAdapter(myPagerAdapter);
        //tabLayout和viewPager建立关联关系
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(tab.getPosition());
                //设置奇幻tab时fragment的切换
                viewPager.setCurrentItem(tab.getPosition(),true);//false取消自动切换时的滚动动画
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentsList;
        List<String> titles;

        public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList, ArrayList<String> titles) {
            super(fm);
            this.fragmentsList = fragmentsList;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentsList.get(position);
        }


        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        //这里设置的是tablayout的title标题内容
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mContentView = null;
    }
}
