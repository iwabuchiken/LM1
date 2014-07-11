package lm1.main;

import lm1.listeners.buttons.BO_CL;
import lm1.listeners.buttons.BO_TL;
import lm1.utils.CONS;
import lm1.utils.Methods;
import lm1.utils.Methods_dlg;
import lm1.utils.Tags;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActv extends Activity implements LocationListener {


	// Preference instance
	SharedPreferences preference;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actv_main);
//		setContentView(R.layout.actv_main_orig);
		
		this.setTitle(this.getClass().getName());
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
//		CONS..mLocationManager.removeUpdates(this);
		
		CONS.Main.locationManager_.removeUpdates(this);
		
		// Log
		String msg_Log = "Location manager => updates removed";
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		Methods.confirm_quit(this, keyCode);
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		
		case R.id.menu_actvmain_db://---------------
			
			Methods_dlg.dlg_Db_Activity(this);
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
		super.onStart();
		
		/***************************************
		 * Set: Listeners
		 ***************************************/
		set_Listeners();
		
		/***************************************
		 * Prepare: data
		 ***************************************/
		prepareData();
		
	}//protected void onStart()
	

	private void prepareData() {
		// TODO Auto-generated method stub
		CONS.Main.locationManager_ =
				(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
		CONS.Main.locationProvider_ =
				CONS.Main.locationManager_.getProvider(LocationManager.GPS_PROVIDER);
		
		CONS.Main.locationManager_
				.requestLocationUpdates(
						CONS.Main.locationProvider_.getName(),
						0, 0, this);

	}//private void prepareData()

//	private void showLongNLat(Double longi, Double lati) {
//		// TODO Auto-generated method stub
//		/***************************************
//		 * Set: data
//		 ***************************************/
//		TextView tvLongi = (TextView) findViewById(R.id.actv_main_tv_longi_data);
//		tvLongi.setText(String.valueOf(longi));
//
//		TextView tvLati = (TextView) findViewById(R.id.actv_main_tv_lat_data);
//		tvLati.setText(String.valueOf(lati));
//		
//	}
//
	private void set_Listeners() {
		// TODO Auto-generated method stub
		/***************************************
		 * Buttons
		 ***************************************/
		/******************************
			'Get data'
		 ******************************/
		Button bt_GetData = (Button) findViewById(R.id.actv_main_bt_get_data);
		
		bt_GetData.setTag(Tags.ButtonTags.ACTVMAIN_BT_GETDATA);
//		bt_GetData.setTag(Tags.ButtonTags.actv_main_bt_go);
		
		bt_GetData.setOnTouchListener(new BO_TL(this));
		bt_GetData.setOnClickListener(new BO_CL(this));

		/******************************
			'Save data'
		 ******************************/
		Button bt_SaveData = (Button) findViewById(R.id.actv_main_bt_save_data);
		
		bt_SaveData.setTag(Tags.ButtonTags.ACTVMAIN_BT_SAVEDATA);
//		bt_SaveData.setTag(Tags.ButtonTags.actv_main_bt_go);
		
		bt_SaveData.setOnTouchListener(new BO_TL(this));
		bt_SaveData.setOnClickListener(new BO_CL(this));
		
		/******************************
			'Show map'
		 ******************************/
		Button bt_ShowMap = (Button) findViewById(R.id.actv_main_bt_show_map);
		
		bt_ShowMap.setTag(Tags.ButtonTags.ACTVMAIN_BT_SHOW_MAP);
//		bt_ShowMap.setTag(Tags.ButtonTags.actv_main_bt_go);
		
		bt_ShowMap.setOnTouchListener(new BO_TL(this));
		bt_ShowMap.setOnClickListener(new BO_CL(this));
		
	}//private void setListeners()

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actv_main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		CONS.Main.longitude = loc.getLongitude();
		
		CONS.Main.latitude = loc.getLatitude();

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	public static LocationManager getLocationManager_() {
		return CONS.Main.locationManager_;
	}

	public static LocationProvider getLocationProvider_() {
		return CONS.Main.locationProvider_;
	}

}//public class ActvMain extends Activity implements LocationListener
