package com.hismart.easylink.preview.ui.launch;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hismart.easylink.preview.R;

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description HomepageFragment 使用缓存机制，防止重复创建fragment
 */
public class HomepageFragment extends Fragment {
    private Toolbar mToolbar;
    private View mViewContent; // 缓存视图内容
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
        if (mViewContent == null) {
            mViewContent = inflater.inflate(R.layout.fragment_main_homepage, container, false);
            mToolbar = mViewContent.findViewById(R.id.toolbar);
            // 代替Actionbar
            ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        }
        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mViewContent.getParent();
        if (parent != null) {
            parent.removeView(mViewContent);
        }
        return mViewContent;
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
