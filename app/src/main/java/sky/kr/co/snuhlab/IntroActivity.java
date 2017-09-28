package sky.kr.co.snuhlab;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;


public class IntroActivity extends FragmentActivity {

	public static Context context;


	private static String[] PERMISSIONS_STORAGE = {

            Manifest.permission.WRITE_EXTERNAL_STORAGE

	};
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);

		Log.e("SKY", "IntroActivity");
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);


        if(permissionCheck== PackageManager.PERMISSION_DENIED){
            // 권한 없음
            Log.e("SKY", "권한 없음");
            ActivityCompat.requestPermissions(this,
                    PERMISSIONS_STORAGE,
                    1);
        }else{
            // 권한 있음
            Log.e("SKY", "권한 있음");
            mHandler.postDelayed(r, 2000);
        }
	}
	Handler mHandler = new Handler();
	Runnable r= new Runnable() {
		@Override
		public void run() {
			//startActivity(new Intent(IntroActivity.this, MainActivity.class));
			finish();


		}
	};
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                mHandler.postDelayed(r, 2000);
                return;
            }
        }
    }
}
