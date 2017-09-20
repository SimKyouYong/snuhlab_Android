package sky.kr.co.snuhlab.common;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class CommonUtil {
	private static CommonUtil _instance;

    private static final String sTagAlarms = ":alarms";

	public String PHONE;
	public String PHONE_ID;
	public String REG_ID;
	public double latitude = 0;
	public double longitude=0;



	
	static {
		_instance = new CommonUtil();
		try {
			_instance.PHONE = 	   		"";
			_instance.REG_ID = 	   		"";
			_instance.PHONE_ID = 	   		"";
			_instance.latitude = 	   		0;
			_instance.longitude = 	   		0;

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static CommonUtil getInstance() {
		return _instance;
	}

	
	public ArrayList<String> Token_String(String url , String token){
		ArrayList<String> Obj = new ArrayList<String>();

		StringTokenizer st1 = new StringTokenizer( url , token);
		while(st1.hasMoreTokens()){
			Obj.add(st1.nextToken());
		}
		return Obj;
	}

}
