package com.example.ice.jsmap;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mNormal;
    private Button mDark;
    private Button mBlueNight;
    private Button mFresh;
    private Button mLight;
    private WebView webView;
    private JavaScriptObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        webView = (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        object = new JavaScriptObject(this);
        webView.addJavascriptInterface(object, "jsObj");
        webView.loadUrl("file:///android_asset/appMap.html");
    }

    private void initView() {
        mNormal = (Button) findViewById(R.id.normal);
        mDark = (Button) findViewById(R.id.dark);
        mBlueNight = (Button) findViewById(R.id.blue_night);
        mFresh = (Button) findViewById(R.id.fresh);
        mLight = (Button) findViewById(R.id.light);

        mNormal.setOnClickListener(this);
        mDark.setOnClickListener(this);
        mBlueNight.setOnClickListener(this);
        mFresh.setOnClickListener(this);
        mLight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal:
                type = "normal";
                break;
            case R.id.dark:
                type = "dark";
                break;
            case R.id.blue_night:
                type = "blue_night";
                break;
            case R.id.fresh:
                type = "fresh";
                break;
            case R.id.light:
                type = "light";
                break;
        }
        webView.loadUrl("javascript: refresh('" + type + "')");
    }

    private String type = "normal";

    //这个是让js调用安卓的方法！
    class JavaScriptObject {
         private Context mContext;
         private JavaScriptObject(Context mContext) {
            this.mContext = mContext;
        }

        @JavascriptInterface
        public void changeSuccess(String type) {
            Toast.makeText(mContext, "切换 "+type+" 主题成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
