package sky.kr.co.snuhlab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
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
    @Override
    @SuppressLint("NewApi")
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }else if(keyCode == KeyEvent.KEYCODE_BACK){
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this , AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            builder.setMessage("종료 하시겠습니까?");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
