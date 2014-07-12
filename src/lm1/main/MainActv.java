package lm1.main;

import java.text.NumberFormat;

import lm1.listeners.buttons.BO_CL;
import lm1.listeners.buttons.BO_TL;
import lm1.tasks.TaskAudioTrack;
import lm1.utils.CONS;
import lm1.utils.Methods;
import lm1.utils.Methods_dlg;
import lm1.utils.Tags;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
		
		CONS.Main.locationObtained = false;
		
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
		prep_Data();
		
	}//protected void onStart()
	

	private void prep_Data() {
		// TODO Auto-generated method stub
		CONS.Main.locationManager_ =
				(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = _setup_SetCriteria();
		
		String provider = CONS.Main.locationManager_.getBestProvider(criteria, true);
		
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
	public void 
	onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		CONS.Main.longitude = loc.getLongitude();
		
		CONS.Main.latitude = loc.getLatitude();
		
		
		if (CONS.Main.locationObtained == false
				&& CONS.Main.longitude != null
				&& CONS.Main.latitude != null) {
			
			CONS.Main.locationObtained = true;
			
			////////////////////////////////

			// UIs

			////////////////////////////////
			// Button "Get data" => bg --> blue
			Button bt_GetData = (Button) findViewById(R.id.actv_main_bt_get_data);
			
			bt_GetData.setBackgroundColor(
					Color.BLUE);
//			this.getResources().getColor(R.color.blue1));
			
//			bt_GetData.setEnabled(true);
			
			//REF http://stackoverflow.com/questions/3465841/how-to-change-visibility-of-layout-programaticly answered Aug 12 '10 at 8:18
			bt_GetData.setVisibility(View.VISIBLE);

			// "Save data"
			Button bt_SaveData = (Button) findViewById(R.id.actv_main_bt_save_data);
			bt_SaveData.setVisibility(View.VISIBLE);
			
			// "Post data"
			Button bt_PostData = (Button) findViewById(R.id.actv_main_bt_post_data);
			bt_PostData.setVisibility(View.VISIBLE);
			
			////////////////////////////////

			// bgm

			////////////////////////////////
//			int bgmResourceId = R.raw.audio_nature;
			int bgmResourceId = R.raw.audio_nature_mp3;
			
			TaskAudioTrack task = new TaskAudioTrack(this);
			
//			task.execute("Start");
			task.execute(bgmResourceId);
			
//			// debug
//			String toa_msg = "Location obtained";
//			Toast.makeText(this, toa_msg, Toast.LENGTH_SHORT).show();
			
		} else {
			
			TextView tv_Long =
					(TextView) this.findViewById(R.id.actv_main_tv_longi_str);
			TextView tv_Lat =
					(TextView) this.findViewById(R.id.actv_main_tv_lat_str);
			
//			NumberFormat format = NumberFormat.getInstance();
//			// Set the number of floating digits => 10
//			format.setMaximumFractionDigits(CONS.Admin.MaximumFractionDigits);
////			format.setMaximumFractionDigits(10);
//			
//			String val_Longi = String.valueOf(format.format(CONS.Main.longitude));
//			String val_Lat = String.valueOf(format.format(CONS.Main.latitude));
			
			//REF http://alvinalexander.com/programming/printf-format-cheat-sheet
			String val_Longi = String.format("%3.9f", CONS.Main.longitude);
			String val_Lat = String.format("%3.9f", CONS.Main.latitude);
//			String val_Longi = String.format("%f", CONS.Main.longitude));
//			String val_Lat = String.valueOf(format.format(CONS.Main.latitude));
			
//			// Log
//			String log_msg = String.valueOf(CONS.LocData.LONGITUDE);
//
//			Log.d("["
//					+ "BOCL.java : "
//					+ +Thread.currentThread().getStackTrace()[2]
//							.getLineNumber() + " : "
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", log_msg);
			
			// Set: values
			tv_Long.setText(val_Longi);
			tv_Lat.setText(val_Lat);
			
		}
		
	}//onLocationChanged(Location loc)

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

	private Criteria _setup_SetCriteria() {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria();

		//Accuracyを指定
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
//		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		
		//PowerRequirementを指定
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		
		//SpeedRequiredを指定
		criteria.setSpeedRequired(false);
		
		//AltitudeRequiredを指定
		criteria.setAltitudeRequired(false);
		
		//BearingRequiredを指定
		criteria.setBearingRequired(false);
		
		//CostAllowedを指定
		criteria.setCostAllowed(false);

		return criteria;
		
	}//private void _setup_SetCriteria()

}//public class ActvMain extends Activity implements LocationListener
