package lm1.main;

import lm1.listeners.buttons.ButtonOnClickListener;
import lm1.listeners.buttons.ButtonOnTouchListener;
import lm1.utils.CONS;
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
		
		this.setTitle(this.getClass().getName());
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
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
		setListeners();
		
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
	private void setListeners() {
		// TODO Auto-generated method stub
		/***************************************
		 * Buttons
		 ***************************************/
		Button btGo = (Button) findViewById(R.id.actv_main_bt_go);
		
		btGo.setTag(Tags.ButtonTags.actv_main_bt_go);
		
		btGo.setOnTouchListener(new ButtonOnTouchListener(this));
		btGo.setOnClickListener(new ButtonOnClickListener(this));

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
