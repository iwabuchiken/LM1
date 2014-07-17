package lm1.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lm1.listeners.dialog.DL;
import lm1.main.R;

import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class Methods {
	
	public static void 
	create_Table
	(Activity actv, String tname) {
		// TODO Auto-generated method stub
		
//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
//		boolean res;
		
		////////////////////////////////

		// Dispatch

		////////////////////////////////
		if (tname.equals(CONS.DB.tname_Locations)) {
			
			_create_Table__Locations(actv);
//			res = dbu.createTable(actv, 
//					CONS.DB.tname_CM7, CONS.DB.col_names_CM7, 
//					CONS.DB.col_types_CM7);
//			
//			if (res == true) {
//				
//				// debug
//				String msg_Toast = "Table => created: " + CONS.DB.tname_CM7;
//				Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
//				
//				
//			} else {
//
//				// debug
//				String msg_Toast = "Table => can't create: " + CONS.DB.tname_CM7;
//				Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
//				
//			}
			
		} else {
			

		}//if (tname.equals(CONS.DB.tname_CM7))
		
	}//create_Table

//	private static void
	private static void
	_create_Table__Locations(Activity actv) {
		// TODO Auto-generated method stub
		int res = DBUtils.createTable(actv, 
//				boolean res = DBUtils.createTable(actv, 
//				boolean res = dbu.createTable(actv, 
							CONS.DB.tname_Locations, 
							CONS.DB.col_names_Locations, 
							CONS.DB.col_types_Locations);
		
		if (res == 1) {
//			if (res == true) {
			
			// debug
			String msg_Toast = "Table => created: " + CONS.DB.tname_Locations;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);
			
			
		} else if (res == -1) {
			
			// debug
			String msg_Toast = "Table => exists: " + CONS.DB.tname_Locations;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);
			
		} else {

			// debug
			String msg_Toast = "Table => can't create: " + CONS.DB.tname_Locations;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);
			
		}
		
	}//_create_Table__Locations(Activity actv)

	public static void 
	drop_Table
	(Activity actv, String tableName, 
			Dialog dlg1, Dialog dlg2) {
		// TODO Auto-generated method stub
		
		int res = DBUtils.dropTable(actv, tableName);
//		boolean res = DBUtils.dropTable(actv, tableName);
				
		if (res == 1) {
//			if (res == true) {
			
			// debug
			String msg_Toast = "Table => dropped: " + tableName;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);
			
			
		} else if (res == -1) {
			
			// debug
			String msg_Toast = "Table => Doesn't exist: " + tableName;
//						Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);
			
		} else {

			// debug
			String msg_Toast = "Table => can't be dropped: " + tableName;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);

		}
		
	}//drop_Table

	public static boolean db_Backup(Activity actv)
	{
		/****************************
		 * 1. Prep => File names
		 * 2. Prep => Files
		 * 2-2. Folder exists?
		 * 
		 * 2-3. Dst folder => Files within the limit?
		 * 3. Copy
			****************************/
		String time_label = Methods.get_TimeLabel(Methods.getMillSeconds_now());
		
		String db_Src = StringUtils.join(
					new String[]{
							actv.getDatabasePath(CONS.DB.dbName).getPath()},
//							CONS.fname_db},
					File.separator);
		
		String db_Dst_Folder = StringUtils.join(
					new String[]{
							CONS.DB.dPath_dbFile_backup,
							CONS.DB.fname_DB_Backup_Trunk},
//							CONS.dpath_db_backup,
//							CONS.fname_db_backup_trunk},
					File.separator);
		
		String db_Dst = db_Dst_Folder + "_"
				+ time_label
//				+ MainActv.fileName_db_backup_ext;
				+ CONS.DB.fname_DB_Backup_ext;
//		+ CONS.fname_db_backup_ext;
//				+ MainActv.fname_db_backup_trunk;

		// Log
		String msg_log = "db_Src = " + db_Src
						+ " / "
						+ "db_Dst = " + db_Dst;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_log);
		
		/****************************
		 * 2. Prep => Files
			****************************/
		File src = new File(db_Src);
		File dst = new File(db_Dst);
		
		/****************************
		 * 2-2. Folder exists?
			****************************/
		File db_Backup = new File(CONS.DB.dPath_dbFile_backup);
//		File db_backup = new File(CONS.dpath_db_backup);
		
		if (!db_Backup.exists()) {
			
			try {
				db_Backup.mkdir();
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Folder created: " + db_Backup.getAbsolutePath());
			} catch (Exception e) {
				
				// Log
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create folder => Failed");
				
				return false;
				
			}
			
		} else {//if (!db_backup.exists())
			
			// Log
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Folder exists: ");
			
		}//if (!db_backup.exists())
		
		/*********************************
		 * 2-3. Dst folder => Files within the limit?
		 *********************************/
		File[] files_dst_folder = new File(CONS.DB.dPath_dbFile_backup).listFiles();
//		File[] files_dst_folder = new File(CONS.dpath_db_backup).listFiles();
		
		int num_of_files = files_dst_folder.length;
		
		// Log
		Log.i("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "num of backup files = " + num_of_files);
		
		/****************************
		 * 3. Copy
			****************************/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.i("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "DB file copied");
			
			// debug
			Toast.makeText(actv, "DB backup => Done", Toast.LENGTH_LONG).show();

		} catch (FileNotFoundException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		} catch (IOException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		}//try

		return true;
		
	}//public static boolean db_backup(Activity actv)

	/****************************************
	 *	getMillSeconds_now()
	 * 
	 * <Caller> 
	 * 1. ButtonOnClickListener # case main_bt_start
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static long getMillSeconds_now() {
		
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime().getTime();
		
	}//private long getMillSeconds_now(int year, int month, int date)

	public static String get_TimeLabel(long millSec) {
		
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
		 
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)

	public static void confirm_quit(Activity actv, int keyCode) {
		
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			
			AlertDialog.Builder dialog=new AlertDialog.Builder(actv);
			
	        dialog.setTitle(actv.getString(R.string.generic_tv_confirm));
	        dialog.setMessage("Quit app?");
	        
	        dialog.setPositiveButton(
	        				actv.getString(R.string.generic_bt_ok),
	        				new DL(actv, dialog, 0));
	        
	        dialog.setNegativeButton(
	        				actv.getString(R.string.generic_bt_cancel),
	        				new DL(actv, dialog, 1));
	        
	        dialog.create();
	        dialog.show();
			
		}//if (keyCode==KeyEvent.KEYCODE_BACK)
		
	}//public static void confirm_quit(Activity actv, int keyCode)

	public static void playSound(Activity actv, int bgmResourceId) {
		// TODO Auto-generated method stub
		int minBufferSize = AudioTrack.getMinBufferSize(
				44100,
				AudioFormat.CHANNEL_CONFIGURATION_MONO, 
				AudioFormat.ENCODING_PCM_16BIT);

		CONS.Main.audioTrack = new AudioTrack(
//				AudioTrack audioTrack = new AudioTrack(
			AudioManager.STREAM_MUSIC, 44100,
			AudioFormat.CHANNEL_CONFIGURATION_MONO, 
			AudioFormat.ENCODING_PCM_16BIT,
			minBufferSize,
			AudioTrack.MODE_STREAM); 
		
		float vol = 0.3f;
		
		CONS.Main.audioTrack.setStereoVolume(vol, vol);
		
		CONS.Main.audioTrack.play();
		
		int i = 0;
		int bufferSize = 512;
		byte [] buffer = new byte[bufferSize];
		//InputStream inputStream = actv.getResources().openRawResource(R.raw.bgm_1);
		
//		InputStream inputStream = actv.getResources().openRawResource(R.raw.bgm_2_koto_t150_1second);
		InputStream inputStream = 
						actv.getResources().openRawResource(bgmResourceId);
		
		try {
			
			while((i = inputStream.read(buffer)) != -1)
				CONS.Main.audioTrack.write(buffer, 0, i);
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			inputStream.close();
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Stream closed");
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		CONS.Main.audioTrack.stop();
		
		// Log
		Log.d("Methods_sl.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Audio stopped");
		
	}//public static void playSound(Activity actv, int bgmResourceId)

	public static void 
	save_LocationData_Current
	(Activity actv) {
		// TODO Auto-generated method stub
		
		
		
	}//save_LocationData_Current

	public static boolean
	setPref_String
	(Activity actv, String pName, String pKey, String value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);
		
		/****************************
		 * 2. Get editor
		 ****************************/
		SharedPreferences.Editor editor = prefs.edit();
		
		/****************************
		 * 3. Set value
		 ****************************/
		editor.putString(pKey, value);
		
		try {
			
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
			
		}
		
	}//public static boolean setPref_long(Activity actv, String pref_name, String pref_key, long value)

	public static String 
	get_Pref_String
	(Activity actv, String pref_name,
			String pref_key, String defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(
						pref_name, Context.MODE_PRIVATE);

		/****************************
		 * Return
			****************************/
		return prefs.getString(pref_key, defValue);

	}//public static String get_Pref_String

	public static boolean
	set_Pref_Long
	(Activity actv, String pName, String pKey, long value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);

		/****************************
		 * 2. Get editor
			****************************/
		SharedPreferences.Editor editor = prefs.edit();

		/****************************
		 * 3. Set value
			****************************/
		editor.putLong(pKey, value);
		
		try {
			
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
			
		}

	}//public static boolean setPref_long(Activity actv, String pref_name, String pref_key, long value)

	public static long 
	get_Pref_Long
	(Activity actv, String pref_name, 
			String pref_key, long dflt_Value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);

		/****************************
		 * Return
			****************************/
		return prefs.getLong(pref_key, dflt_Value);

	}//public static boolean set_pref(String pref_name, String value)

	//REF http://www.geodatasource.com/developers/java
	public static double
	distance
	(double lat1, double lon1, 
		double lat2, double lon2, char unit) {

		double theta = lon1 - lon2;

		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) 
						+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) 
							* Math.cos(deg2rad(theta));

		dist = Math.acos(dist);

		dist = rad2deg(dist);

		dist = dist * 60 * 1.1515;

		if (unit == 'K') {

			dist = dist * 1.609344;

		} else if (unit == 'N') {

			dist = dist * 0.8684;

		}

		return (dist);

	}

	private static double 
	deg2rad(double deg) {
		
		return (deg * Math.PI / 180.0);
		
	}
		 
	private static double 
	rad2deg(double rad) {
		
		return (rad * 180 / Math.PI);
		
	}

	public static double
	distance_2
	(double lat1, double lon1, 
			double lat2, double lon2) {
		
		double R = 6378.137; // Radius of earth in KM
	    double dLat = (lat2 - lat1) * Math.PI / 180;
	    double dLon = (lon2 - lon1) * Math.PI / 180;
	    
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) 
	    		+ Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180)
	    		* Math.sin(dLon/2) * Math.sin(dLon/2);
	    
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double d = R * c;
	    
	    return d * 1000; // meters
		
//		return 0;
		
	}//distance_2
	
}//public class Methods

