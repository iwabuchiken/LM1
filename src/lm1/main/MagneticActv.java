package lm1.main;

import lm1.listeners.SEL;
import lm1.listeners.SEL_Magnetic;
import lm1.listeners.STL;
import lm1.listeners.buttons.BO_CL;
import lm1.utils.CONS;
import lm1.utils.Methods;
import lm1.utils.Tags;
import lm1.views.CanvasView_4;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MagneticActv extends Activity {

	CanvasView_4 canvas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actv_magnetic);
//		setContentView(R.layout.actv_accelero_3);
//		setContentView(R.layout.actv_accelero);
//		setContentView(R.layout.actv_main_orig);
		
		// Log
		String msg_Log = "view => set";
		Log.d("AcceleroActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		this.setTitle(this.getClass().getName());
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
//		CONS..mLocationManager.removeUpdates(this);
//		CONS.Sensors.sensor_Magnetic.clear();
		
		// Log
//		String msg_Log = "onDestroy; sensor cleared";
		String msg_Log = "onDestroy";
		Log.d("AcceleroActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
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
		
		_Setup_SetListeners();
		
//		_test_DrawLine();
	
		_Setup_Sensor();
		
		super.onStart();
		

	}//protected void onStart()

	private void 
	_Setup_Sensor() {
		// TODO Auto-generated method stub

		////////////////////////////////

		// listener

		////////////////////////////////
		CONS.Sensors.sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		
		CONS.Sensors.sensor_Magnetic =
    			CONS.Sensors.sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
		
		CONS.Sensors.sel_MagneticActv = new SEL_Magnetic(this, this.canvas);
//		CONS.Sensors.sel_MagneticActv = new SEL_Magnetic(this);
		
		////////////////////////////////

		// instance

		////////////////////////////////
        if (CONS.Sensors.sensor_Magnetic.size() > 0) {
        	// SensorManagerインスタンスにセンサーイベントリスナーを設定
//        	CONS.Sensors.sensorManager.registerListener(sensorEventListener,
        	CONS.Sensors.sensorManager.registerListener(
        			CONS.Sensors.sel_MagneticActv,
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
		
	}//_Setup_Sensor
	

	private void 
	_test_DrawLine() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// paint

		////////////////////////////////
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
//		paint.setColor(0xFF4444FF);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(30);
		
		////////////////////////////////

		// canvas view

		////////////////////////////////
		
		lm1.views.CanvasView_4 v_Canvas = 
				(lm1.views.CanvasView_4) findViewById(R.id.actv_accelero_canvas);
//		lm1.views.CanvasView_2 v_Canvas = 
//				(lm1.views.CanvasView_2) findViewById(R.id.actv_accelero_canvas);
//		lm1.views.CanvasView_2 v_Canvas = 
//				(lm1.views.CanvasView_2) findViewById(R.id.actv_accelero_canvas);
//		View v_Canvas = (View) findViewById(R.id.actv_accelero_canvas);
		
		int canvas_Height = v_Canvas.getHeight();
		
		// Log
		String msg_Log = "height => " + canvas_Height;
		Log.d("AcceleroActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		Canvas c = new Canvas();
		
		c.drawLine(10, 10, 100, 100, paint);
		
		c.save();
		
		
		
		v_Canvas.draw(c);
		
		v_Canvas.setBackgroundColor(Color.YELLOW);
		
		v_Canvas.invalidate();
		
//		c.restore();
		
		// Log
		msg_Log = "canvas => line drawn";
		Log.d("AcceleroActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//_test_DrawLine()

	/******************************
		canvas => instantiate
	 ******************************/
	private void _Setup_SetListeners() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// canvas

		////////////////////////////////
		canvas = 
					(lm1.views.CanvasView_4) this.findViewById(R.id.actv_magnetic_canvas);
		
		////////////////////////////////

		// Button: clear

		////////////////////////////////
		Button bt_Clear = (Button) findViewById(R.id.actv_magnetic_bt_clear);
		
		bt_Clear.setTag(Tags.ButtonTags.ACTV_MAGNETIC_BT_CLEAR);
		
		bt_Clear.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// Button: go
		
		////////////////////////////////
		Button bt_Go = (Button) findViewById(R.id.actv_magnetic_bt_go);
		
		bt_Go.setTag(Tags.ButtonTags.ACTV_MAGNETIC_BT_GO);
		
		bt_Go.setOnClickListener(new BO_CL(this));
		
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		// Log
		String msg_Log = "onStop";
		Log.d("AcceleroActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF http://stackoverflow.com/questions/6448946/how-to-start-and-stop-sensor-android answered Jan 23 '12 at 13:14
		if (CONS.Sensors.sensorManager != null) {
			
			CONS.Sensors.sensorManager
				.unregisterListener(CONS.Sensors.sel_MagneticActv);
//			.unregisterListener(CONS.Sensors.sensorEventListener);
		
			// Log
			String log_msg = "CONS.Sensors.sensorManager => Unregistered";
			
			Log.d("[" + "PMActv.java : "
					+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);
			
		}
		
		super.onStop();
		
	}//protected void onStop

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actv_sensors, menu);
		return true;
	}
	
}//public class AcceleroActv extends Activity
