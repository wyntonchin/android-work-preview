package com.hismart.easylink.preview.ui.launch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hismart.easylink.preview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntelligenceFragment extends Fragment {

    private View mViewContent; // 缓存视图内容
    public IntelligenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mViewContent == null) {
            mViewContent = inflater.inflate(R.layout.fragment_main_intelligence, container, false);
            //mToolbar = mViewContent.findViewById(R.id.toolbar);
            // 代替Actionbar
            //((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        }
        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mViewContent.getParent();
        if (parent != null) {
            parent.removeView(mViewContent);
        }
        return mViewContent;
    }

}
