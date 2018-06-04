package com.hismart.easylink.preview.ui.launch;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;

import com.hismart.easylink.preview.R;

import java.util.HashMap;

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description LaunchActivity作为主界面的容器
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentTabHost mTabHost = null;
    //SparseArray核心是折半查找
    private static final HashMap sTabFragmentMap = new HashMap();
    private static final SparseArray sIndexTabMap = new SparseArray();

    private static void addIndexTabFragment(int index, int tabResId, int layoutId) {

    }

    static {
        addIndexTabFragment(0, R.id.tab_homepage, R.layout.fragment_main_homepage);
        addIndexTabFragment(1, R.id.tab_intelligence, R.layout.fragment_main_intelligence);
        addIndexTabFragment(2, R.id.tab_mall, R.layout.fragment_main_mall);
        addIndexTabFragment(3, R.id.tab_mine, R.layout.fragment_main_mine);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabHost();
    }

    private void initTabHost() {
        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(),
                android.R.id.tabcontent);
        LayoutInflater inflate = LayoutInflater.from(this);

        View tab0 = inflate.inflate(R.layout.item_homepage_tab_invisible, null);
        View tab1 = inflate.inflate(R.layout.item_homepage_tab_invisible, null);
        View tab2 = inflate.inflate(R.layout.item_homepage_tab_invisible, null);
        View tab3 = inflate.inflate(R.layout.item_homepage_tab_invisible, null);

        tab0.setEnabled(false);
        tab1.setEnabled(true);
        tab2.setEnabled(true);
        tab3.setEnabled(true);

        mTabHost.addTab(mTabHost.newTabSpec("tab0").setIndicator(tab0),
                HomepageFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(tab1),
                HomepageFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(tab2),
                HomepageFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator(tab3),
                HomepageFragment.class, null);

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
    }

    public void switchTab(int index) {


        if (null != mTabHost) {
            int currIndex = mTabHost.getCurrentTab();
            if (currIndex == index) {
                return;
            }
            mTabHost.setCurrentTab(index);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_homepage:
                switchTab(0);
 /*               switchTab(0);
                clearFocus();
                setFocus(v);*/
                break;
            case R.id.tab_intelligence:
/*                if (HomesData.getInstance().getRefreshFlag()) {
                    ToastUtil.makeText(ELMainActivity.this, R.string.service_getdata,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                switchTab(1);
                clearFocus();
                setFocus(v);*/
                break;
            case R.id.tab_mall:
/*                if (HomesData.getInstance().getRefreshFlag()) {
                    ToastUtil.makeText(ELMainActivity.this, R.string.service_getdata,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                switchTab(2);
                clearFocus();
                setFocus(v);*/
                break;
            case R.id.tab_mine:
/*                switchTab(4);
                clearFocus();
                setFocus(v);*/
                break;
            case R.id.tab_voice:
/*                if (HomesData.getInstance().getRefreshFlag()) {
                    ToastUtil.makeText(ELMainActivity.this, R.string.service_getdata,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                startVoiceInterface();*/
                break;
            default:
                break;
        }
    }
}
