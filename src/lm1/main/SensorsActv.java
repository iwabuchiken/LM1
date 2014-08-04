package lm1.main;

import java.util.ArrayList;
import java.util.List;

import lm1.adapters.Adp_Loc;
import lm1.listeners.SEL;
import lm1.listeners.STL;
import lm1.listeners.buttons.BO_CL;
import lm1.listeners.list.List_ILCL;
import lm1.utils.CONS;
import lm1.utils.DBUtils;
import lm1.utils.Methods;
import lm1.utils.Methods_dlg;
import lm1.utils.Tags;
import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SensorsActv extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actv_sensors_2);
//		setContentView(R.layout.actv_main_orig);
		
		this.setTitle(this.getClass().getName());
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
//		CONS..mLocationManager.removeUpdates(this);
		
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
//		Methods.confirm_quit(this, keyCode);
		
//		this.onBackPressed();
		
//		this.finish();
//		
//		overridePendingTransition(0, 0);
//
//		
		// Log
		String msg_Log = "onKeyDown";
		Log.d("SensorsActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		////////////////////////////////

		// Sensor: Unregister

		////////////////////////////////
//		if (CONS.Sensors.sensorManager != null) {
//			
//			CONS.Sensors.sensorManager
//				.unregisterListener(CONS.Sensors.sensorEventListener);
//		
//			// Log
//			String log_msg = "CONS.Sensors.sensorManager => Unregistered";
//			
//			Log.d("[" + "PMActv.java : "
//					+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ " : "
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", log_msg);
//			
//		}
		
		////////////////////////////////

		// Finish actv

		////////////////////////////////
		this.finish();
		
		overridePendingTransition(0, 0);

		////////////////////////////////

		// onStop

		////////////////////////////////
		super.onStop();
		
	}//public void onBackPressed()

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		
		case R.id.menu_sensors_accelero://---------------
			
			String msg_Toa = "Accelero";
			Toast.makeText(this, msg_Toa, Toast.LENGTH_SHORT).show();
			
			break;// case R.id.main_opt_menu_backup_db

		case R.id.menu_sensors_compass://---------------
			
			msg_Toa = "Compass";
			Toast.makeText(this, msg_Toa, Toast.LENGTH_SHORT).show();
			
			break;// case R.id.main_opt_menu_backup_db
			
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub

		_Setup_SetList();
		
		_Setup_SetListeners();
		
		_Setup_Sensors();
		
		_Setup_Prefs();
		
		super.onStart();
		

	}//protected void onStart()

	private void _Setup_Prefs() {
		// TODO Auto-generated method stub
		
		int pref_GammaVal = Methods.get_Pref_Int(
					this, 
					CONS.Pref.pname_SensorsActv, 
					CONS.Pref.pkey_SensorsActv_GammaVal, 
					CONS.Pref.dflt_IntExtra_value);
		
		if (pref_GammaVal == CONS.Pref.dflt_IntExtra_value) {
			
			CONS.Sensors.gammaVal = CONS.Sensors.GammaVal_Dflt;
			
		} else {

			CONS.Sensors.gammaVal = pref_GammaVal;
			
		}
		
		// Log
		String msg_Log = "Pref: GammaVal set => " + CONS.Sensors.gammaVal;
		Log.d("SensorsActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//private void _Setup_Prefs()

	private void 
	_Setup_Sensors() {

    	CONS.Sensors.sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
    	
    	// Sensor: Accelerometer
    	CONS.Sensors.sensor_Accelerometer =
    			CONS.Sensors.sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
    	
    	// Sensor: Light
    	CONS.Sensors.sensor_Light =
    			CONS.Sensors.sensorManager.getSensorList(Sensor.TYPE_LIGHT);
    	
    	// Sensor: Magnetic
    	CONS.Sensors.sensor_Magnetic =
    			CONS.Sensors.sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
    	
    	// Sensor: Gyroscope
    	CONS.Sensors.sensor_Gyro =
    			CONS.Sensors.sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
    	
//    	// Sensor: Light
//    	CONS.Sensors.sensor_Temp =
//    			CONS.Sensors.sensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);
    	
    	// Listener
    	CONS.Sensors.sensorEventListener = new SEL(this);
    	
    	////////////////////////////////

		// Sensor: accelero

		////////////////////////////////
        // 加速度センサーオブジェクトが取得できた場合
        if (CONS.Sensors.sensor_Accelerometer.size() > 0) {
            // SensorManagerインスタンスにセンサーイベントリスナーを設定
//        	CONS.Sensors.sensorManager.registerListener(sensorEventListener,
			CONS.Sensors.sensorManager.registerListener(
							CONS.Sensors.sensorEventListener,
							CONS.Sensors.sensor_Accelerometer.get(0),
							SensorManager.SENSOR_DELAY_GAME);
			
			// Log
			String log_msg = "Listener set => Accelero";

			Log.d("["
					+ "SensorsActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);
			
        } else {
        	
        	// Log
			String log_msg = "Listener not set => Accelero";

			Log.d("["
					+ "SensorsActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);
        				
        }
        
        ////////////////////////////////
        
        // Sensor: light
        
        ////////////////////////////////
        if (CONS.Sensors.sensor_Light.size() > 0) {
        	// SensorManagerインスタンスにセンサーイベントリスナーを設定
//        	CONS.Sensors.sensorManager.registerListener(sensorEventListener,
        	CONS.Sensors.sensorManager.registerListener(
        			CONS.Sensors.sensorEventListener,
        			CONS.Sensors.sensor_Light.get(0),
//        			CONS.Sensors.sensor_Accelerometer.get(0),
        			SensorManager.SENSOR_DELAY_GAME);
        	
        	// Log
        	String log_msg = "Listener set => Light";
        	
        	Log.d("["
        			+ "SensorsActv.java : "
        			+ +Thread.currentThread().getStackTrace()[2]
        					.getLineNumber() + " : "
        					+ Thread.currentThread().getStackTrace()[2].getMethodName()
        					+ "]", log_msg);
        	
        } else {
        	
        	// Log
			String log_msg = "Listener not set => Light";

			Log.d("["
					+ "SensorsActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);

        }
        
        ////////////////////////////////
        
        // Sensor: light
        
        ////////////////////////////////
        if (CONS.Sensors.sensor_Gyro.size() > 0) {
        	// SensorManagerインスタンスにセンサーイベントリスナーを設定
//        	CONS.Sensors.sensorManager.registerListener(sensorEventListener,
        	CONS.Sensors.sensorManager.registerListener(
        			CONS.Sensors.sensorEventListener,
        			CONS.Sensors.sensor_Gyro.get(0),
//        			CONS.Sensors.sensor_Accelerometer.get(0),
        			SensorManager.SENSOR_DELAY_GAME);
        	
        	// Log
        	String log_msg = "Listener set => Gyro";
        	
        	Log.d("["
        			+ "SensorsActv.java : "
        			+ +Thread.currentThread().getStackTrace()[2]
        					.getLineNumber() + " : "
        					+ Thread.currentThread().getStackTrace()[2].getMethodName()
        					+ "]", log_msg);
        	
        } else {
        	
        	// Log
        	String log_msg = "Listener not set => Gyro";
        	
        	Log.d("["
        			+ "SensorsActv.java : "
        			+ +Thread.currentThread().getStackTrace()[2]
        					.getLineNumber() + " : "
        					+ Thread.currentThread().getStackTrace()[2].getMethodName()
        					+ "]", log_msg);
        	
        }
        
        ////////////////////////////////
        
        // Sensor: magnetic
        
        ////////////////////////////////
        if (CONS.Sensors.sensor_Magnetic.size() > 0) {
        	// SensorManagerインスタンスにセンサーイベントリスナーを設定
//        	CONS.Sensors.sensorManager.registerListener(sensorEventListener,
        	CONS.Sensors.sensorManager.registerListener(
        			CONS.Sensors.sensorEventListener,
        			CONS.Sensors.sensor_Magnetic.get(0),
//        			CONS.Sensors.sensor_Accelerometer.get(0),
        			SensorManager.SENSOR_DELAY_GAME);
        	
        	// Log
        	String log_msg = "Listener set => Magnetic";
        	
        	Log.d("["
        			+ "SensorsActv.java : "
        			+ +Thread.currentThread().getStackTrace()[2]
        					.getLineNumber() + " : "
        					+ Thread.currentThread().getStackTrace()[2].getMethodName()
        					+ "]", log_msg);
        	
        } else {
        	
        	// Log
        	String log_msg = "Listener not set => Magnetic";
        	
        	Log.d("["
        			+ "SensorsActv.java : "
        			+ +Thread.currentThread().getStackTrace()[2]
        					.getLineNumber() + " : "
        					+ Thread.currentThread().getStackTrace()[2].getMethodName()
        					+ "]", log_msg);
        	
        }
        
//        ////////////////////////////////
//        
//        // Sensor: temperature
//        
//        ////////////////////////////////
//        if (CONS.Sensors.sensor_Temp.size() > 0) {
//        	// SensorManagerインスタンスにセンサーイベントリスナーを設定
////        	CONS.Sensors.sensorManager.registerListener(sensorEventListener,
//        	CONS.Sensors.sensorManager.registerListener(
//        			CONS.Sensors.sensorEventListener,
//        			CONS.Sensors.sensor_Temp.get(0),
////        			CONS.Sensors.sensor_Accelerometer.get(0),
//        			SensorManager.SENSOR_DELAY_GAME);
//        	
//        	// Log
//        	String log_msg = "Listener set => Temperature";
//        	
//        	Log.d("["
//        			+ "SensorsActv.java : "
//        			+ +Thread.currentThread().getStackTrace()[2]
//        					.getLineNumber() + " : "
//        					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//        					+ "]", log_msg);
//        	
//        } else {
//        	
//        	// Log
//        	String log_msg = "Listener not set => Temperature";
//        	
//        	Log.d("["
//        			+ "SensorsActv.java : "
//        			+ +Thread.currentThread().getStackTrace()[2]
//        					.getLineNumber() + " : "
//        					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//        					+ "]", log_msg);
//        	
//        }
        
	}//_Setup_Sensors()

	private void _Setup_SetListeners() {
		// TODO Auto-generated method stub
		
		LinearLayout ll_Swipe = (LinearLayout) findViewById(R.id.actv_sensors_LL_swipe_area);
		
		ll_Swipe.setTag(Tags.SwipeTags.ACTV_SENSORS);
		
		ll_Swipe.setOnTouchListener(new STL(this));
		
	}

	private void 
	_Setup_SetList() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// photo

		////////////////////////////////
		TextView tv_Color = 
				(TextView) this.findViewById(R.id.actv_sensors_2_tv_photo_color);
		
		tv_Color.setBackgroundColor(Color.rgb(0, 0, 255));
		
//		TextView tv_Photo = (TextView) findViewById(R.id.actv_sensors_2_tv_photo_val);
		
		
//		String[] choices = {
////				actv.getString(R.string.dlg_db_admin_item_exec_sql),
//				
//				this.getString(R.string.actv_sensors_photo),
//				this.getString(R.string.actv_sensors_magnetic),
//				this.getString(R.string.actv_sensors_temp),
//				
//		};
//		
//		List<String> list = new ArrayList<String>();
//		
//		for (String item : choices) {
//			
//			list.add(item);
//			
//		}
//		
//		////////////////////////////////
//
//		// adapter
//
//		////////////////////////////////
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//				this,
////				R.layout.dlg_db_admin,
////				android.R.layout.simple_list_item_1,
//				R.layout.list_row_simple_1,
//				list
//				);
//
//		ListView lv = this.getListView();
//		
//		lv.setAdapter(adapter);
		
	}//_Setup_SetList()

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		
		if (CONS.Sensors.sensorManager != null) {
			
			CONS.Sensors.sensorManager
				.unregisterListener(CONS.Sensors.sensorEventListener);
		
			// Log
			String log_msg = "CONS.Sensors.sensorManager => Unregistered";
			
			Log.d("[" + "PMActv.java : "
					+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);
			
		}
		
		super.onStop();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actv_sensors, menu);
		return true;
	}


	
}
