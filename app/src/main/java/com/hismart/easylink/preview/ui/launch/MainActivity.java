package com.hismart.easylink.preview.ui.launch;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.BaseCompatActivity;
import com.hismart.easylink.preview.ui.StatusBarUtil;

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description LaunchActivity作为主界面的容器
 */
public class MainActivity extends BaseCompatActivity implements View.OnClickListener {
    /**
     * 声明全局tab list数组，减少重复代码
     */
    private static final Integer[] TAB_IDS = {R.id.tab_homepage, R.id.tab_intelligence, R.id.tab_mall, R.id.tab_mine};
    private FragmentTabHost mTabHost = null;
    //View[] underTabs = new View[4];
    View[] mOnTabs =  new View[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        //在activity上面add了一个statusbar高度的一个view
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
        initTabHost();
    }

    private void initTabHost() {
        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(),
                android.R.id.tabcontent);
        LayoutInflater inflate = LayoutInflater.from(this);
        Class<?>[] fragments = new Class[]{HomepageFragment.class, IntelligenceFragment.class, MallFragment.class, MimeFragment.class};
        View[]  underTabs = new View[4];
        for (int i = 0; i < fragments.length; i++) {
            underTabs[i] = inflate.inflate(R.layout.layout_main_fake_tabs_item, null);
            mOnTabs[i] = findViewById(TAB_IDS[i]);
            mTabHost.addTab(mTabHost.newTabSpec("tab" + i).setIndicator(underTabs[i]), fragments[i], null);
        }
        switchTab(R.id.tab_homepage);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
    }

    public void switchTab(int tabResId) {
        int index = -1;
        //查找tabResId在初始化的数组中的的index
        for (int i = 0; i < TAB_IDS.length; i++) {
            if (tabResId == TAB_IDS[i]) {
                index = i;
                break;
            }
        }
        //不存在的resId，返回
        if (index < 0) {
            return;
        }
        mTabHost.setCurrentTab(index);
        for (int i = 0; i < mOnTabs.length; i++) {
            if (i == index) {
                //让自定义的selector颜色生效
                mOnTabs[i].setSelected(true);
            } else if ( mOnTabs[i].isSelected()) {
                mOnTabs[i].setSelected(false);
            }
        }

    }


    /**
     * OnClick是在xml布局中设定的
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_voice:
/*                if (HomesData.getInstance().getRefreshFlag()) {
                    ToastUtil.makeText(ELMainActivity.this, R.string.service_getdata,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                startVoiceInterface();*/
                break;
            default:
                switchTab(v.getId());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //返回键控制网页返回
        if(mTabHost.getCurrentTab() == 2){
            //TODO web back
            //doWebBack();
            return;
        }else{
            super.onBackPressed();
        }
    }
}
