package lm1.main;

import java.text.NumberFormat;
import java.util.List;

import lm1.adapters.Adp_Loc;
import lm1.items.Loc;
import lm1.listeners.STL;
import lm1.listeners.buttons.BO_CL;
import lm1.listeners.buttons.BO_TL;
import lm1.listeners.list.List_ILCL;
import lm1.tasks.TaskAudioTrack;
import lm1.utils.CONS;
import lm1.utils.DBUtils;
import lm1.utils.Methods;
import lm1.utils.Methods_LM1;
import lm1.utils.Methods_dlg;
import lm1.utils.Tags;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActv 
					extends Activity 
//					extends ListActivity 
					implements LocationListener {
//	public class MainActv extends Activity implements LocationListener {


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
		super.onStart();
		
		////////////////////////////////

		// Set: list

		////////////////////////////////
//		_Setup_Set_LocationList();
		
		/***************************************
		 * Set: Listeners
		 ***************************************/
		_Setup_Listeners();
		
		/***************************************
		 * Prepare: data
		 ***************************************/
		prep_Data();
		
		////////////////////////////////

		// init: general

		////////////////////////////////
		_Setup_InitGeneral();
//		String pref_DistBuffer = Methods.get_Pref_String(
//							this, 
//							CONS.Pref.pname_MainActv, 
//							this.getString(R.string.prefs_key_distbuff), 
//							null);
//		
//		if (pref_DistBuffer == null) {
//			
//			CONS.Main.distance_BufferRange = CONS.Pref.dflt_distance_buffer;
//					
//		} else {
//
//			CONS.Main.distance_BufferRange = Integer.parseInt(pref_DistBuffer);
//			
//		}
		
		//test
//		_test();

		_Setup_UI_Base();
		
		_Setup_UI_Ref();
		
	}//protected void onStart()
	

	private void 
	_Setup_UI_Base() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// Loc: base

		////////////////////////////////
		long current_Base = Methods.get_Pref_Long(
						this, 
						CONS.Pref.pname_MainActv, 
						CONS.Pref.pkey_MainActv_CurrentBase, 
						CONS.Pref.dflt_LongExtra_value);
		
		/******************************
			validate: any pref?
		 ******************************/
		if (current_Base == CONS.Pref.dflt_LongExtra_value) {
			
//			String msg = "No base set yet";
//			Methods_dlg.dlg_ShowMessage(this, msg);
			
			return;
			
		}
		
		Loc loc_Base = DBUtils.get_Loc_FromId(this, current_Base);
				
		/******************************
			validate: null?
		 ******************************/
		if (loc_Base == null) {
			
			String msg = "No loc for the id: " + current_Base;
			Methods_dlg.dlg_ShowMessage(this, msg);
			
			return;
			
		}
		
		////////////////////////////////

		// set: values

		////////////////////////////////
		////////////////////////////////

		// UI: base, ref

		////////////////////////////////
		TextView tv_Base_Longi = 
				(TextView) findViewById(R.id.actv_main_tv_base_longi);
		
		TextView tv_Base_Lat = 
				(TextView) findViewById(R.id.actv_main_tv_base_lat);
		
		TextView tv_Base_Memo = 
				(TextView) findViewById(R.id.actv_main_tv_base_memo);

		tv_Base_Longi.setText(loc_Base.getLongitude());
		tv_Base_Lat.setText(loc_Base.getLatitude());
		tv_Base_Memo.setText(loc_Base.getMemo());
		
	}//_Setup_UI_BaseRef()
	
	private void 
	_Setup_UI_Ref() {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// Loc: base
		
		////////////////////////////////
		long current_Ref = Methods.get_Pref_Long(
				this, 
				CONS.Pref.pname_MainActv, 
				CONS.Pref.pkey_MainActv_CurrentRef, 
				CONS.Pref.dflt_LongExtra_value);
		
		/******************************
			validate: any pref?
		 ******************************/
		if (current_Ref == CONS.Pref.dflt_LongExtra_value) {
			
//			String msg = "No ref set yet";
//			Methods_dlg.dlg_ShowMessage(this, msg);
			
			return;
			
		}
		
		Loc loc_Ref = DBUtils.get_Loc_FromId(this, current_Ref);
		
		/******************************
			validate: null?
		 ******************************/
		if (loc_Ref == null) {
			
			String msg = "No loc for the id: " + current_Ref;
			Methods_dlg.dlg_ShowMessage(this, msg);
			
			return;
			
		}
		
		////////////////////////////////
		
		// set: values
		
		////////////////////////////////
		////////////////////////////////
		
		// UI: base, ref
		
		////////////////////////////////
		TextView tv_Ref_Longi = 
				(TextView) findViewById(R.id.actv_main_tv_ref_longi);
		
		TextView tv_Ref_Lat = 
				(TextView) findViewById(R.id.actv_main_tv_ref_lat);
		
		TextView tv_Ref_Memo = 
				(TextView) findViewById(R.id.actv_main_tv_ref_memo);
		
		tv_Ref_Longi.setText(loc_Ref.getLongitude());
		tv_Ref_Lat.setText(loc_Ref.getLatitude());
		tv_Ref_Memo.setText(loc_Ref.getMemo());
		
	}//_Setup_UI_Ref()
	

	private void 
	_Setup_InitGeneral() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// Buffer range

		////////////////////////////////
		String pref_DistBuffer = Methods.get_Pref_String(
				this, 
				CONS.Pref.pname_MainActv, 
				this.getString(R.string.prefs_key_distbuff), 
				null);

		if (pref_DistBuffer == null) {
		
		CONS.Main.distance_BufferRange = CONS.Pref.dflt_distance_buffer;
				
		} else {
		
		CONS.Main.distance_BufferRange = Integer.parseInt(pref_DistBuffer);
		
		}

	}//_Setup_InitGeneral()

	private void _test() {
		
		SharedPreferences prefs = 
				this.getSharedPreferences(
						CONS.Pref.pname_MainActv, 
						Context.MODE_PRIVATE);
		
//		int distBuf = prefs.getInt(
//				this.getString(R.string.prefs_key_distbuff), -1);
		
		String distBuf = prefs.getString(
							CONS.Pref.pkey_MainActv_CurrentBase, null);
		
		// Log
		String msg_Log = "buf => " + distBuf;
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// TODO Auto-generated method stub
//		int distBuffer = Methods.get_Pref_Int(
//				this, 
//				CONS.Pref.pname_MainActv, 
//				this.getString(R.string.prefs_key_distbuff), 
//				CONS.Pref.dflt_IntExtra_value);
//		
//		// Log
//		String msg_Log = "distBuffer => " + distBuffer;
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);

	}

	private void 
	_Setup_Set_LocationList() {
		
	}//_Setup_Set_LocationList()

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
	private void _Setup_Listeners() {
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
		
		/******************************
			'Monitor'
		 ******************************/
		Button bt_Monitor = (Button) findViewById(R.id.actv_main_bt_monitor);
		
		bt_Monitor.setTag(Tags.ButtonTags.ACTVMAIN_BT_MONITOR);
//		bt_Monitor.setTag(Tags.ButtonTags.actv_main_bt_go);
		
		bt_Monitor.setOnTouchListener(new BO_TL(this));
		bt_Monitor.setOnClickListener(new BO_CL(this, bt_Monitor));
		
//		////////////////////////////////
//
//		// listview
//
//		////////////////////////////////
//		ListView lv = this.getListView();
//		
////		lv.setTag(Tags.ListTags.actv_main_lv);
//		lv.setTag(Tags.ListTags.ACTV_MAIN_LV);
//		
//		lv.setOnItemLongClickListener(new List_ILCL(this));
		
		////////////////////////////////

		// swipe

		////////////////////////////////
		LinearLayout ll = (LinearLayout) this.findViewById(R.id.actv_main_main_ll);

		ll.setTag(Tags.SwipeTags.SWIPE_ACTV_MAIN);
		
		ll.setOnTouchListener(new STL(this));
//		TableLayout tl = (TableLayout) this.findViewById(R.id.actv_main_tl);
//		
//		tl.setOnTouchListener(new STL(this));
		
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
			Button bt_PostData = (Button) findViewById(R.id.actv_main_bt_monitor);
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
			
		} else {//if (CONS.Main.locationObtained == false
			
			TextView tv_Long =
					(TextView) this.findViewById(R.id.actv_main_tv_longi_str);
			TextView tv_Lat =
					(TextView) this.findViewById(R.id.actv_main_tv_lat_str);
			
			//REF http://alvinalexander.com/programming/printf-format-cheat-sheet
			String val_Longi = String.format("%3.9f", CONS.Main.longitude);
			String val_Lat = String.format("%3.9f", CONS.Main.latitude);
			
			// Set: values
			tv_Long.setText(val_Longi);
			tv_Lat.setText(val_Lat);
			
			////////////////////////////////

			// monitor distance

			////////////////////////////////
			//test
			String val = tv_Lat.getText().toString();
			
			if (CONS.Main.monitor == true) {
				
				CONS.Main.distance_Current = 
						Methods.distance_2(
							CONS.Main.latitude, 
							CONS.Main.longitude, 
							Double.parseDouble(CONS.Main.loc_Base.getLatitude()), 
							Double.parseDouble(CONS.Main.loc_Base.getLongitude()));

				CONS.Main.distance_Diff = 
						CONS.Main.distance_Current - CONS.Main.distance_Base;
				
				// Log
				String msg_Log = "CONS.Main.distance_Diff => "
									+ CONS.Main.distance_Diff;
				Log.d("MainActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				

				tv_Lat.setText(
						val + "/" + String.format("%.3f", CONS.Main.distance_Diff));
				
				////////////////////////////////

				// out of range?

				////////////////////////////////
				
//				if (CONS.Main.distance_Diff < 5
				if (CONS.Main.distance_Diff > CONS.Main.distance_BufferRange
						&& (CONS.Main.msg_OutOfRange == false)) {
					
					////////////////////////////////

					// stop: player

					////////////////////////////////
//					if(CONS.Main.audioTrack.getState())
//					// Log
//					msg_Log = "CONS.Main.audioTrack.getState() => "
//								+ CONS.Main.audioTrack.getState();
//					Log.d("MainActv.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", msg_Log);
					
					
					////////////////////////////////

					// sound

					////////////////////////////////
					int bgmResourceId = R.raw.audio_water;
					
					CONS.Main.task_Audio = new TaskAudioTrack(this);
//					TaskAudioTrack task = new TaskAudioTrack(this);
					
//					task.execute("Start");
					CONS.Main.task_Audio.execute(bgmResourceId);
					
//					msg_Log = "Audio task started; "
//							+ "CONS.Main.audioTrack.getState() => "
//							+ CONS.Main.audioTrack.getState();
//					Log.d("MainActv.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", msg_Log);
//					
//					// Log
//					msg_Log = "CONS.Main.audioTrack.getPlayState() => " 
//							+ CONS.Main.audioTrack.getPlayState();
//					Log.d("MainActv.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", msg_Log);
//					
//					// Log
//					msg_Log = "CONS.Main.audioTrack.getPlayState() => " 
//							+ CONS.Main.audioTrack.getPlayState();
//					Log.d("MainActv.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", msg_Log);
	
					////////////////////////////////

					// setup

					////////////////////////////////
					CONS.Main.msg_OutOfRange = true;
					
					Methods_dlg.dlg_ShowMessage(
							this, 
							"Out of range: " + 5, 
							Tags.DialogTags.DLG_MONITOR_OUTOFRANGE_OK);
					
				}//if (CONS.Main.distance_Diff > CONS.Main.distance_BufferRange
				
			}//if (CONS.Main.monitor == true)
			
		}//if (CONS.Main.locationObtained == false
		
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

		//Accuracy繧呈欠螳�
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
//		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		
		//PowerRequirement繧呈欠螳�
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		
		//SpeedRequired繧呈欠螳�
		criteria.setSpeedRequired(false);
		
		//AltitudeRequired繧呈欠螳�
		criteria.setAltitudeRequired(false);
		
		//BearingRequired繧呈欠螳�
		criteria.setBearingRequired(false);
		
		//CostAllowed繧呈欠螳�
		criteria.setCostAllowed(false);

		return criteria;
		
	}//private void _setup_SetCriteria()

//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		// TODO Auto-generated method stub
//		
//		// debug
//		String msg_Toast = "list item";
//		Toast.makeText(this, msg_Toast, Toast.LENGTH_SHORT).show();
//		
//		
//		super.onListItemClick(l, v, position, id);
//	}

}//public class ActvMain extends Activity implements LocationListener
