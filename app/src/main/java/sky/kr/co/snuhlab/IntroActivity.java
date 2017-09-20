package sky.kr.co.snuhlab;


import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


public class IntroActivity extends FragmentActivity {

	public static Context context;


	private static String[] PERMISSIONS_STORAGE = {
			Manifest.permission.ACCESS_FINE_LOCATION,

			Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.RECORD_AUDIO
			
	};
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);

		Log.e("SKY", "IntroActivity");
        mHandler.postDelayed(r, 2000);
	}
	Handler mHandler = new Handler();
	Runnable r= new Runnable() {
		@Override
		public void run() {
			//startActivity(new Intent(IntroActivity.this, MainActivity.class));
			finish();


		}
	};

}
