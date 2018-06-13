package com.hismart.easylink.preview.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hismart.easylink.preview.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description Activity基类
 */
public abstract class ToolbarBaseCompatActivity extends BaseCompatActivity {
    private static final String TAG = ToolbarBaseCompatActivity.class.getSimpleName();
    HashMap<Integer, MenuItemData> menuItemDataHashMap = null;
    public Toolbar mToolbar = null;
    public LinearLayout rootView = null;
    private TextView titleText;
    private TextView rightText;
    private boolean isBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_activity_toolbar);

        rootView = findViewById(R.id.toolbar_root_view);
        titleText = findViewById(R.id.toolbar_title);
        rightText = findViewById(R.id.toolbar_right_text);
        //setStatusBarColor(R.color.colorPrimary);
        initToolbar();
        menuItemDataHashMap = new HashMap<Integer, MenuItemData>();
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        if (rootView == null) {
            return;
        }
        rootView.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /*********************************************************************************************************************************
     * 其他函数
     ********************************************************************************************************************************/

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        mToolbar = findViewById(R.id.tool_bar);
        mToolbar.setPopupTheme(R.style.Base_ThemeOverlay_AppCompat_Light);
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBack) {
                    pressBackKeyEvent();
                } else {

                }
            }
        });
    }

    /**
     * 设置状态栏颜色
     *
     * @param color Res
     */
    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // finally change the color
            window.setStatusBarColor(getResources().getColor(color));
        } else {
        }
    }

    /**
     * 返回按钮事件
     */
    protected void pressBackKeyEvent() {
        finish();
    }

    /*********************************************************************************************************************************
     * Toolbar 相关函数
     ********************************************************************************************************************************/

    /**
     * 自定义View设置在Toolbar
     *
     * @param view 自定义View
     * @return 自定义View
     */
    protected View addCustomToolbar(View view) {
        android.support.v7.app.ActionBar.LayoutParams layout = new android.support.v7.app.ActionBar.LayoutParams(
                android.support.v7.app.ActionBar.LayoutParams.MATCH_PARENT,
                android.support.v7.app.ActionBar.LayoutParams.MATCH_PARENT);
        if (mToolbar != null) {
            mToolbar.addView(view, layout);
            mToolbar.setContentInsetsAbsolute(0, 0);
        }
        return view;
    }

    /**
     * 自定义View设置在Toolbar
     *
     * @param layoutResId 自定义View Res
     * @return 自定义View
     */
    protected View addCustomToolbar(int layoutResId) {
        if (mToolbar == null) {
            return null;
        }
        final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(layoutResId, null);
        android.support.v7.app.ActionBar.LayoutParams layout = new android.support.v7.app.ActionBar.LayoutParams(
                android.support.v7.app.ActionBar.LayoutParams.MATCH_PARENT,
                android.support.v7.app.ActionBar.LayoutParams.MATCH_PARENT);
        mToolbar.addView(actionBarLayout, layout);
        mToolbar.setContentInsetsAbsolute(0, 0);

        return actionBarLayout;
    }

    /**
     * 设置toolbar的背景色
     *
     * @param resid
     */
    protected void setCustomToolbarColor(int resid) {
        //mToolBar.setBackgroundDrawable(getResources().getDrawable(resid));废弃的接口
        mToolbar.setBackground(getResources().getDrawable(resid));
    }

    /**
     * 获取toolbar
     *
     * @return
     */
    protected Toolbar getToolBar() {
        return mToolbar;
    }

    /**
     * 设置toolbar
     *
     * @param mToolBar
     */
    protected void setToolbar(Toolbar mToolBar) {
        this.mToolbar = mToolBar;
    }

    /**
     * 隐藏toolbar
     */
    protected void hideToolbar() {
        if (mToolbar != null) {
            mToolbar.setVisibility(View.GONE);
        }
    }

    /**
     * 隐藏toolbar的阴影
     */
    protected void hideToolbarLine() {
        LinearLayout barLine = findViewById(R.id.actionbar_line);
        if (barLine != null) {
            barLine.setVisibility(View.GONE);
        }
    }

    /**
     * 设置Toolbar标题
     *
     * @param title 标题
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (titleText != null) {
            titleText.setText(title);
        }
    }

    /**
     * 设置Toolbar标题
     *
     * @param titleId 标题Res
     */
    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        setTitle(getString(titleId));
    }

    /**
     * 设置Toolbar子标题
     *
     * @param title 标题
     */
    public void setSubTitle(CharSequence title) {
        if (mToolbar != null) {
            mToolbar.setSubtitle(title);
        }
    }

    /**
     * 设置Toolbar子标题
     *
     * @param titleId 标题Res
     */
    public void setSubTitle(int titleId) {
        if (mToolbar != null) {
            mToolbar.setSubtitle(titleId);
        }
    }

    protected View setSubContentView(int viewId) {
        //add subview
        View contentView = getViewRootActivity().getLayoutInflater().inflate(
                viewId, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        rootView.addView(contentView, lp);
        return contentView;
    }

    protected View setSubContentView(View view) {
        //add subview
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        rootView.addView(view, lp);
        return view;
    }


    /*********************************************************************************************************************************
     * Menu 相关函数
     ********************************************************************************************************************************/

    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用) Inflate the menu; this adds items to the action bar
     * if it is present.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
     * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等）
     */
    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        refreshMenu(menu, false);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象。
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                pressBackKeyEvent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 是否显示默认左上角返回按钮
     *
     * @param isBack 是否显示
     */
    protected void setLeftButtonIsBack(boolean isBack) {
        this.isBack = isBack;
        if (mToolbar != null) {
            if (isBack) {
                mToolbar.setNavigationIcon(R.drawable.base_btn_back);
            } else {
                mToolbar.setNavigationIcon(null);
            }
        }
    }

    /**
     * 设置左上返回键图标
     *
     * @param res
     */
    protected void setLeftButtonBackResId(int res) {
        isBack = false;
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(res);
        }
    }

    protected void setRightTextView(int resId) {
        if (rightText != null) {
            rightText.setText(resId);
        }
    }

    protected void setRightTextView(CharSequence text) {
        if (rightText != null) {
            rightText.setText(text);
        }
    }

    protected void setRightTextViewOnClickLisenter(final RightTextClickListener textClickListener) {
        if (rightText == null) {
            return;
        }
        if (textClickListener != null) {
            rightText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textClickListener.onClick();
                }
            });
        }
    }

    public interface RightTextClickListener {
        void onClick();
    }

    /**
     * 添加右侧按钮
     *
     * @param menuItemData 菜单项类
     *                     只能添加两个不隐藏的
     */
    protected void addRightButton(MenuItemData menuItemData) {
        if (menuItemDataHashMap == null) {
            menuItemDataHashMap = new HashMap<Integer, MenuItemData>();
        }
        menuItemDataHashMap.put(menuItemData.itemId, menuItemData);
        onMenuItemDataChanged();
    }

    /**
     * 移除MenuItem
     *
     * @param menuId 菜单ID项
     */
    protected void removeMenuItem(int menuId) {
        if (menuItemDataHashMap.containsKey(menuId)) {
            menuItemDataHashMap.remove(menuId);
            onMenuItemDataChanged();
        }
    }

    /**
     * 清空MenuItem数据
     */
    protected void clearMenu() {
        if (menuItemDataHashMap != null) {
            menuItemDataHashMap.clear();
            onMenuItemDataChanged();
        }
    }

    /**
     * 通过itemId获取MenuItemData
     *
     * @param itemId MenuItemData索引值
     * @return MenuItemData
     */
    protected MenuItemData getMenuItemData(int itemId) {
        MenuItemData menuItemData = null;
        if (menuItemDataHashMap != null) {
            menuItemData = menuItemDataHashMap.get(itemId);
        }
        return menuItemData;
    }

    /**
     * 刷新按钮
     */
    public void onMenuItemDataChanged() {
        try {
            refreshMenu(mToolbar.getMenu(), true);
        } catch (Throwable h) {

        }
    }

    /**
     * 刷新菜单
     *
     * @param menu          菜单
     * @param isToolbarMenu 是不是Toolbar菜单
     */
    private void refreshMenu(Menu menu, boolean isToolbarMenu) {
        menu.clear();
        if (menuItemDataHashMap == null || menuItemDataHashMap.isEmpty()) {
            return;
        }
        Iterator iterator = menuItemDataHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            final MenuItemData itemData = (MenuItemData) entry.getValue();
            initMenuItemData(menu, itemData, isToolbarMenu);
        }
    }

    /**
     * 初始化MenuItem
     *
     * @param menu          菜单
     * @param itemData      MenuItemData 菜单项
     * @param isToolBarMenu 是不是Toolbar菜单
     */
    private void initMenuItemData(Menu menu, final MenuItemData itemData, boolean isToolBarMenu) {
        if (!isToolBarMenu && itemData.showAction == MenuItemData.SHOW_ACTION_AT_TITLE) {
            return;
        }
        final MenuItem menuItem = menu.add(0, itemData.itemId,
                itemData.itemId, itemData.titleResId);
        if (itemData.drawableResId > 0) {
            menuItem.setIcon(itemData.drawableResId);
        }
        if (itemData.actionProvider != null) {
            MenuItemCompat.setActionProvider(menuItem, itemData.actionProvider);
        }
        if (itemData.showAction == MenuItemData.SHOW_ACTION_AT_TITLE) {
            MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (itemData.listener != null) {
                    itemData.listener.onClick();
                }
                return true;
            }
        });
    }

    /**
     * 获取菜单列表
     *
     * @return HashMap<Integer               ,                               MenuItemData> MenuList
     */
    protected HashMap<Integer, MenuItemData> getMenuList() {
        return menuItemDataHashMap;
    }

    /**
     * MenuItemData类
     * Toolbar右侧按钮类
     */
    public class MenuItemData {
        public static final int SHOW_ACTION_AT_TITLE = 0;
        public static final int SHOW_ACTION_HIDE_IN_MENU = 1;

        public MenuItemData(int itemId, int titleResId, int drawableResId,
                            RightBtnClickListener listener) {
            this.itemId = itemId;
            this.titleResId = titleResId;
            this.drawableResId = drawableResId;
            this.listener = listener;
            this.showAction = SHOW_ACTION_AT_TITLE;
        }

        public MenuItemData(int itemId, int titleResId, int drawableResId,
                            int showAction, RightBtnClickListener listener) {
            this.itemId = itemId;
            this.titleResId = titleResId;
            this.drawableResId = drawableResId;
            this.listener = listener;
            this.showAction = showAction;
        }

        public MenuItemData(int itemId, int titleResId, int drawableResId,
                            int showAction, ActionProvider actionProvider) {
            this.itemId = itemId;
            this.titleResId = titleResId;
            this.drawableResId = drawableResId;
            this.showAction = showAction;
            this.actionProvider = actionProvider;
        }

        public MenuItemData(int itemId, int titleResId, int drawableResId,
                            int showAction, boolean isExpand) {
            this.itemId = itemId;
            this.titleResId = titleResId;
            this.drawableResId = drawableResId;
            this.showAction = showAction;
            this.isExpand = isExpand;
        }

        public int itemId;//唯一ID,给actionbar的按钮排序用
        public int titleResId;//标题的ResId
        public int drawableResId;//图标的ResId
        public int showAction;//是否显示再标题栏 SHOW_ACTION_AT_TITLE 显示 , SHOW_ACTION_HIDE_IN_MENU 隐藏在列表里
        public boolean isExpand;//如果是搜索框，是否展开
        public ActionProvider actionProvider;
        RightBtnClickListener listener;//menu的点击事件
    }

    /**
     * 右侧按钮点击监听接口
     */
    public interface RightBtnClickListener {
        void onClick();
    }
}