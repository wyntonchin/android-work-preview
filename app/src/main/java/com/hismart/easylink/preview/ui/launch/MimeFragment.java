package com.hismart.easylink.preview.ui.launch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hismart.easylink.preview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MimeFragment extends Fragment {


    public MimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mime, container, false);
    }

}
