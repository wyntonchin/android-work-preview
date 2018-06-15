package com.hismart.easylink.preview.ui.launch;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hismart.easylink.preview.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MallFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * @author qinwendong.
 * @date 2018/5/30
 * description 作为Webview容器
 */
public class MallFragment extends Fragment {
    private final String TAG = MallFragment.this.getClass().getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View mContentView;

    public MallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MallFragment newInstance(String param1, String param2) {
        MallFragment fragment = new MallFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_main_mall, container, false);
            //initWebView(mContentView);
        }
        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }

        return mContentView;
    }

    private void initWebView(View contentView) {
        WebView webView = contentView.findViewById(R.id.web_view);
        //WebView加载web资源
        WebSettings webSettings = webView.getSettings();

// 支持javascript
        webSettings.setJavaScriptEnabled(true);

// 支持使用localStorage(H5页面的支持)
        webSettings.setDomStorageEnabled(true);

// 支持数据库
        webSettings.setDatabaseEnabled(true);

// 支持缓存
        webSettings.setAppCacheEnabled(true);
        String appCaceDir = MallFragment.this.getActivity().getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);

// 设置可以支持缩放
        webSettings.setUseWideViewPort(true);

// 扩大比例的缩放
        webSettings.setSupportZoom(true);

        webSettings.setBuiltInZoomControls(true);

// 隐藏缩放按钮
        webSettings.setDisplayZoomControls(false);

// 自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);

// 隐藏滚动条
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

// 进度显示及隐藏
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
/*                if (newProgress >= 99) {
                    pv.setVisibility(View.INVISIBLE);
                } else {
                    pv.setVisibility(View.VISIBLE);
                }*/
            }
        });

// 处理网页内的连接（自身打开）
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if(url.contains("http")||url.contains("https")){
                    // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }

                return true;
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if(request.getUrl().toString().contains("http")||request.getUrl().toString().contains("https")){
                    // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }
                return true;
            }
        });

        webView.loadUrl("https://m.hisense.com");
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContentView = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
          /*  throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
