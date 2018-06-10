package com.hismart.easylink.preview.ui.launch

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentTabHost
import android.view.LayoutInflater
import android.view.View
import android.widget.TabHost

import com.hismart.easylink.preview.R
import com.hismart.easylink.preview.ui.BaseCompatActivity

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description LaunchActivity作为主界面的容器
 */
class MainActivity : BaseCompatActivity(), View.OnClickListener {
    private var mTabHost: FragmentTabHost? = null
    private val mUnderTabs = arrayOfNulls<View>(4)
    private val mOnTabs = arrayOfNulls<View>(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTabHost()
        //requestPermission();
    }

    override fun onPermissionGranted(permission: String) {
        super.onPermissionGranted(permission)
    }

    override fun onPermissionFailed(permission: String) {
        super.onPermissionFailed(permission)
    }

    private fun initTabHost() {
        mTabHost = findViewById(android.R.id.tabhost)
        mTabHost!!.setup(this, supportFragmentManager,
                android.R.id.tabcontent)
        val inflate = LayoutInflater.from(this)
        val fragments = arrayOf<Class<*>>(HomepageFragment::class.java, IntelligenceFragment::class.java, MallFragment::class.java, HomepageFragment::class.java)
        for (i in fragments.indices) {
            mUnderTabs[i] = inflate.inflate(R.layout.item_homepage_cover_tabs, null)
            mOnTabs[i] = findViewById(TAB_IDS[i])
            mTabHost?.addTab(mTabHost?.newTabSpec("tab$i")!!.setIndicator(mUnderTabs[i]), fragments[i], null)
        }
        switchTab(R.id.tab_homepage)
        mTabHost!!.setOnTabChangedListener { }
    }

    private fun switchTab(tabResId: Int) {
        var index = -1
        //查找tabResId在初始化的数组中的的index
        for (i in TAB_IDS.indices) {
            if (tabResId == TAB_IDS[i]) {
                index = i
                break
            }
        }
        //不存在的resId，返回
        if (index < 0) {
            return
        }
        mTabHost?.currentTab = index
        for (i in mOnTabs.indices) {
            if (i == index) {
                //让自定义的selector颜色生效
                mOnTabs[i]?.isSelected = true
            } else if (mOnTabs[i]!!.isSelected) {
                mOnTabs[i]?.isSelected = false
            }
        }

    }


    /**
     * OnClick是在xml布局中设定的
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.tab_voice -> {
            }
            else -> switchTab(v.id)
        }/*                if (HomesData.getInstance().getRefreshFlag()) {
                    ToastUtil.makeText(ELMainActivity.this, R.string.service_getdata,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                startVoiceInterface();*/
    }

    override fun onBackPressed() {
        //返回键控制网页返回
        if (mTabHost?.currentTab == 2) {
            //TODO web back
            //doWebBack();
            return
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        /**
         * 声明全局tab list数组，减少重复代码
         */
        private val TAB_IDS = arrayOf(R.id.tab_homepage, R.id.tab_intelligence, R.id.tab_mall, R.id.tab_mine)
    }
}
