package sky.kr.co.snuhlab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;

import co.kr.sky.webview.SKYWebview;
import sky.kr.co.snuhlab.common.DEFINE;

public class MainActivity extends Activity {
    private SKYWebview mWebView;             //웹뷰
    WebView pWebView;


    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, IntroActivity.class));

        mContext = this;


        //웹뷰 셋팅
        mWebView = (SKYWebview) findViewById(R.id.webview);
        mWebView.Setting(this , mAfterAccum , mWebView , "sky.kr.co.snuhlab.common.FunNative" , null , pWebView);
        if(getIntent().getStringExtra("url") == null || getIntent().getStringExtra("url").equals("")){
            mWebView.loadUrl(DEFINE.DEFAULT_URL);
        }else{
            mWebView.loadUrl(getIntent().getStringExtra("url"));
        }
    }
    Handler mAfterAccum = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };
}
