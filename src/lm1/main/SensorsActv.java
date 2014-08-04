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
import android.app.ListActivity;
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
import android.widget.Toast;

public class SensorsActv extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actv_sensors);
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
		
		this.onBackPressed();
		
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
		
		case R.id.menu_actvmain_db://---------------
			
			Methods_dlg.dlg_Db_Activity(this);
//			Methods.db_backup(this);
			
			break;// case R.id.main_opt_menu_backup_db

		case R.id.menu_settings://---------------
			
//			this._test();	// ClassCastException
			
			Methods.start_Activity_Pref(this);
//			Methods.db_backup(this);
			
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
		
		super.onStart();
		

	}//protected void onStart()

	private void 
	_Setup_Sensors() {
		// TODO Auto-generated method stub
		
    	CONS.Sensors.sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
    	
//    	CONS.Sensors.accelerometerSensors =
    	CONS.Sensors.sensor_Accelerometer =
    			CONS.Sensors.sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
    	
    	CONS.Sensors.sensor_Light =
    			CONS.Sensors.sensorManager.getSensorList(Sensor.TYPE_LIGHT);
    	
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
        // 加速度センサーオブジェクトが取得できた場合
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
		
		String[] choices = {
//				actv.getString(R.string.dlg_db_admin_item_exec_sql),
				
				this.getString(R.string.actv_sensors_photo),
				this.getString(R.string.actv_sensors_magnetic),
				this.getString(R.string.actv_sensors_temp),
				
		};
		
		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
			
			list.add(item);
			
		}
		
		////////////////////////////////

		// adapter

		////////////////////////////////
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this,
//				R.layout.dlg_db_admin,
//				android.R.layout.simple_list_item_1,
				R.layout.list_row_simple_1,
				list
				);

		ListView lv = this.getListView();
		
		lv.setAdapter(adapter);
		
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
		getMenuInflater().inflate(R.menu.actv_main, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub

		String item_Name = (String) l.getItemAtPosition(position);
		
		if (item_Name.equals(this.getString(
				R.string.actv_sensors_photo))) {
			
//			Methods.exec_Sql(actv);
			
		} else if (item_Name.equals(this.getString(
				R.string.actv_sensors_magnetic))) {
			
			
		} else {
			
		}
		
		
		
		super.onListItemClick(l, v, position, id);
	}


	
}
