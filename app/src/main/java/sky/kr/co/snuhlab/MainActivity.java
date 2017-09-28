package sky.kr.co.snuhlab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import co.kr.sky.webview.SKYWebview;
import sky.kr.co.snuhlab.common.DEFINE;

public class MainActivity extends Activity {
    private SKYWebview mWebView;             //웹뷰
    WebView pWebView;
    public RelativeLayout mainBody;
    boolean popup = false;


    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, IntroActivity.class));

        mContext = this;

        mainBody = (RelativeLayout)findViewById(R.id.mainBody);

        //웹뷰 셋팅
        mWebView = (SKYWebview) findViewById(R.id.webview);
        mWebView.Setting(this , mAfterAccum , mWebView , "sky.kr.co.snuhlab.common.FunNative" , mainBody , pWebView);
        if(getIntent().getStringExtra("url") == null || getIntent().getStringExtra("url").equals("")){
            mWebView.loadUrl(DEFINE.DEFAULT_URL);
        }else{
            mWebView.loadUrl(getIntent().getStringExtra("url"));
        }
    }
    Handler mAfterAccum = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1 == 1000){
                popup = (Boolean) msg.obj;
                Log.e("SKY" , "pupup : " + popup);
            }else if(msg.arg1 == 902){
                pWebView = (WebView) msg.obj;
            }
        }
    };
    @Override
    @SuppressLint("NewApi")
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(popup&&keyCode == KeyEvent.KEYCODE_BACK&&pWebView.canGoBack()){
            pWebView.goBack();
            return true;
        }else if(popup&&keyCode == KeyEvent.KEYCODE_BACK){

            Log.e("SKY" , "팝업창 닫기");
            if( Build.VERSION.SDK_INT < 19 ){
                pWebView.loadUrl("javascript:self.close();");
            }else{
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        ValueCallback<String> resultCallback = null;

                        pWebView.evaluateJavascript("self.close()",resultCallback);
                    }
                });
            }

            return true;
        }else if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
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
