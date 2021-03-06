package lm1.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lm1.items.LogItem;
import lm1.listeners.dialog.DL;
import lm1.main.AcceleroActv;
import lm1.main.LogActv;
import lm1.main.MagneticActv;
import lm1.main.PrefActv;
import lm1.main.R;
import lm1.main.SensorsActv;
import lm1.main.ShowListActv;
import lm1.main.ShowLogActv;

import org.apache.commons.lang.StringUtils;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
							CONS.DB.dPath_dbFile_Backup,
//							CONS.DB.dPath_dbFile_backup,
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
		File db_Backup = new File(CONS.DB.dPath_dbFile_Backup);
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
		File[] files_dst_folder = new File(CONS.DB.dPath_dbFile_Backup).listFiles();
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
		
		// TODO �ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ黷ｽ�ｿｽ�ｿｽ�ｿｽ\�ｿｽb�ｿｽh�ｿｽE�ｿｽX�ｿｽ^�ｿｽu
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			
			AlertDialog.Builder dialog=new AlertDialog.Builder(actv);
			
	        dialog.setTitle(actv.getString(R.string.generic_tv_confirm));
	        dialog.setMessage("Quit app?");
	        
	        dialog.setPositiveButton(
	        				actv.getString(R.string.generic_bt_ok),
	        				new DL(actv, dialog, 0));
	        
	        dialog.setNegativeButton(
	        				actv.getString(R.string.generic_cancel),
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
	
	public static boolean
	set_Pref_Int
	(Activity actv, String pName, String pKey, int value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);
		
		/****************************
		 * 2. Get editor
		 ****************************/
		SharedPreferences.Editor editor = prefs.edit();
		
		/****************************
		 * 3. Set value
		 ****************************/
		editor.putInt(pKey, value);
		
		try {
			
			editor.commit();
			
			// Log
			String msg_Log = "Pref set => " + value;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
			
		}
		
	}//set_Pref_Int

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
	
	public static int 
	get_Pref_Int
	(Activity actv, String pref_name, 
			String pref_key, int dflt_Value) {
		
		// Log
		String msg_Log = "pref_key => " + pref_key;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
		
		/****************************
		 * Return
		 ****************************/
		return prefs.getInt(pref_key, dflt_Value);
		
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

	public static void start_Activity_Pref(Activity actv) {
		// TODO Auto-generated method stub
		
		Intent i = new Intent();
		
		i.setClass(actv, PrefActv.class);
		
//		i.putExtra(CONS.Intent.iKey_CurrentPath_MainActv, currentPath);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);

	}

	public static void start_Activity_ShowList(Activity actv) {
		// TODO Auto-generated method stub

		Intent i = new Intent();
		
		i.setClass(actv, ShowListActv.class);
		
//		i.putExtra(CONS.Intent.iKey_CurrentPath_MainActv, currentPath);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);

	}

	public static void 
	start_Activity_MagneticActv(Activity actv) {
		// TODO Auto-generated method stub
		
		Intent i = new Intent();
		
		i.setClass(actv, MagneticActv.class);
		
//		i.putExtra(CONS.Intent.iKey_CurrentPath_MainActv, currentPath);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
	}
	
	public static boolean
	restore_DB(Activity actv) {
    	
    	// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Starting: restore_DB()");

		/*********************************
		 * Get the absolute path of the latest backup file
		 *********************************/
		// Get the most recently-created db file
		String src_dir = CONS.DB.dPath_dbFile_Backup;
//		String src_dir = "/mnt/sdcard-ext/IFM9_backup";
		
		File f_dir = new File(src_dir);
		
		File[] src_dir_files = f_dir.listFiles();
		
		// If no files in the src dir, quit the method
		if (src_dir_files.length < 1) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread()
						.getStackTrace()[2].getLineNumber()
					+ "]", "No files in the dir: " + src_dir);
			
			return false;
			
		}//if (src_dir_files.length == condition)
		
		// Latest file
		File f_src_latest = src_dir_files[0];
		
		
		for (File file : src_dir_files) {
			
			if (f_src_latest.lastModified() < file.lastModified()) {
						
				f_src_latest = file;
				
			}//if (variable == condition)
			
		}//for (File file : src_dir_files)
		
		// Show the path of the latest file
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "f_src_latest=" + f_src_latest.getAbsolutePath());
		
		/*********************************
		 * Restore file
		 *********************************/
		String src = f_src_latest.getAbsolutePath();
		String dst = StringUtils.join(
				new String[]{
						//REF http://stackoverflow.com/questions/9810430/get-database-path answered Jan 23 at 11:24
						actv.getDatabasePath(CONS.DB.dbName).getPath()
				},
//						actv.getFilesDir().getPath() , 
//						CONS.DB.dbName},
				File.separator);
		
		boolean res = Methods.restore_DB(
							actv, 
							CONS.DB.dbName, 
							src, dst);
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res=" + res);
		
		////////////////////////////////

		// return

		////////////////////////////////
		return res;
		
	}//private void restore_DB()
	
	/*********************************
	 * @return true => File copied(i.e. restored)<br>
	 * 			false => Copying failed
	 *********************************/
	public static boolean
	restore_DB
	(Activity actv, String dbName, 
			String src, String dst) {
		/*********************************
		 * 1. Setup db
		 * 2. Setup: File paths
		 * 3. Setup: File objects
		 * 4. Copy file
		 * 
		 *********************************/
		// Setup db
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
	
		wdb.close();
	
		/*********************************
		 * 2. Setup: File paths
	
		/*********************************
		 * 3. Setup: File objects
		 *********************************/
	
		/*********************************
		 * 4. Copy file
		 *********************************/
		FileChannel iChannel = null;
		FileChannel oChannel = null;
		
		try {
			iChannel = new FileInputStream(src).getChannel();
			oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File copied: " + src);
			
			// debug
			Toast.makeText(actv, "DB restoration => Done", Toast.LENGTH_LONG).show();
			
			return true;
	
		} catch (FileNotFoundException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			if (iChannel != null) {
				
				try {
					
					iChannel.close();
					
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Exception: " + e.toString());
	
				}
				
			}
			
			if (iChannel != null) {
				
				try {
					
					iChannel.close();
					
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
			
			if (oChannel != null) {
				
				try {
					oChannel.close();
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
	
			return false;
			
		} catch (IOException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			if (iChannel != null) {
				
				try {
					
					iChannel.close();
					
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
			
			if (oChannel != null) {
				
				try {
					oChannel.close();
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
	
			
			return false;
			
		}//try
		
	}//restore_DB

	public static void 
	start_Activity_Sensor(Activity actv) {
		// TODO Auto-generated method stub

		Intent i = new Intent();
		
		i.setClass(actv, SensorsActv.class);
		
//		i.putExtra(CONS.Intent.iKey_CurrentPath_MainActv, currentPath);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);

	}//start_Activity_Sensor(Activity actv)

	public static void 
	start_Activity_Accelero(Activity actv) {
		// TODO Auto-generated method stub
		
		Intent i = new Intent();
		
		i.setClass(actv, AcceleroActv.class);
		
//		i.putExtra(CONS.Intent.iKey_CurrentPath_MainActv, currentPath);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
	}//start_Activity_Accelero(Activity actv)

	public static void 
	start_Activity_ShowLogActv
	(Activity actv, String itemName) {
		
		
		// Log
		String msg_Log = "itemName => " + itemName;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		Intent i = new Intent();
		
		i.setClass(actv, ShowLogActv.class);

		i.putExtra(CONS.Intent.iKey_LogActv_LogFileName, itemName);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
		
	}//start_Activity_LogActv

	public static List<String> 
	get_LogLines
	(Activity actv, String fpath_LogFile) {
		
		
		int count_Lines = 0;
		int count_Read = 0;
		
		List<String> list = new ArrayList<String>();
		
	//	File f = new File(fpath_LogFile);
		
		try {
			
	//		fis = new FileInputStream(fpath_Log);
	
			//REF BufferedReader http://stackoverflow.com/questions/7537833/filereader-for-text-file-in-android answered Sep 24 '11 at 8:29
			BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(fpath_LogFile)));
	//		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			String line = null;
			
			line = br.readLine();
					
			while(line != null) {
				
				list.add(line);
				
				count_Lines += 1;
				count_Read += 1;
				
				line = br.readLine();
				
			}
			
			////////////////////////////////
	
			// close
	
			////////////////////////////////
			br.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
			String msg = "FileNotFoundException";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return null;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			count_Lines += 1;
			
		}
	
		// Log
		String msg_Log = String.format(
							Locale.JAPAN,
							"count_Lines => %d / count_Read => %d", 
							count_Lines, count_Read);
		
		Log.d("ShowLogActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
	
		
		return list;
		
	}//get_LogLines

	public static List<LogItem> 
	conv_LogLinesList_to_LogItemList
	(Activity actv, List<String> list_RawLines) {
		
		String msg_Log;
		
		List<LogItem> list_LogItems = new ArrayList<LogItem>();
		
		String reg = "\\[(.+?)\\] \\[(.+?)\\] (.+)";
//		String reg = "\\[(.+)\\] \\[(.+)\\] (.+)";
		
		Pattern p = Pattern.compile(reg);
		
		Matcher m = null;
		
		LogItem loi = null;
		
		for (String string : list_RawLines) {
			
			m = p.matcher(string);
			
			if (m.find()) {

				loi = _build_LogItem_from_Matcher(actv, m);
				
				if (loi != null) {
					
					list_LogItems.add(loi);
					
				}
				
			}//if (m.find())
			
		}//for (String string : list_RawLines)
		
		/******************************
			validate
		 ******************************/
		if (list_LogItems.size() < 1) {
			
			// Log
			msg_Log = "list_LogItems.size() => " + list_LogItems.size();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		}
		
		return list_LogItems;
		
	}//conv_LogLinesList_to_LogItemList

	/******************************
		@return
			null => Matcher.groupCount() != 3
	 ******************************/
	private static LogItem 
	_build_LogItem_from_Matcher
	(Activity actv, Matcher m) {
		
	
		////////////////////////////////
	
		// validate
	
		////////////////////////////////
		if (m.groupCount() != 3) {
			
			return null;
			
		}
		
		////////////////////////////////
	
		// prep: data
	
		////////////////////////////////
		String[] tokens_TimeLabel = m.group(1).split(" ");
		
		String[] tokens_FileInfo = m.group(2).split(" : ");
		
		String text = m.group(3);
		
		String date = tokens_TimeLabel[0];
		
		String time = tokens_TimeLabel[1].split("\\.")[0];
		
		String fileName = tokens_FileInfo[0];
		
		String line = tokens_FileInfo[1];
		
		////////////////////////////////
	
		// LogItem
	
		////////////////////////////////
		LogItem loi = new LogItem.Builder()
					
					.setDate(date)
					.setTime(time)
					.setMethod(fileName)
					.setLine(Integer.parseInt(line))
					.setText(text)
					.build();
		
		return loi;
		
	}//_build_LogItem_from_Matcher

	public static void 
	start_Activity_LogActv
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		
		i.setClass(actv, LogActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		d1.dismiss();
		
	}//start_Activity_LogActv

	public static void 
	write_Log
	(Activity actv, String message,
			String fileName, int lineNumber) {
		
		
		////////////////////////////////

		// file

		////////////////////////////////
		File fpath_Log = new File(CONS.DB.dPath_Log, CONS.DB.fname_Log);
		
		////////////////////////////////

		// file exists?

		////////////////////////////////
		if (!fpath_Log.exists()) {
			
			try {
				
				fpath_Log.createNewFile();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				String msg = "Can't create a log file";
				Methods_dlg.dlg_ShowMessage_Duration(actv, msg, R.color.gold2);
				
				return;
				
			}
		} else {
			
			// Log
			String msg_Log = "log file => exists";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// validate: size

		////////////////////////////////
		long len = fpath_Log.length();
		
		if (len > CONS.DB.logFile_MaxSize) {
		
			fpath_Log.renameTo(new File(
						fpath_Log.getParent(), 
						CONS.DB.fname_Log_Trunk
						+ "_"
//						+ Methods.get_TimeLabel(Methods.getMillSeconds_now())
						+ Methods.get_TimeLabel(fpath_Log.lastModified())
						+ CONS.DB.fname_Log_ext
						));
			
			// new log.txt
			try {
				
				fpath_Log = new File(fpath_Log.getParent(), CONS.DB.fname_Log);
//				File f = new File(fpath_Log.getParent(), CONS.DB.fname_Log);
				
				fpath_Log.createNewFile();
				
				// Log
				String msg_Log = "new log.txt => created";
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				// Log
				String msg_Log = "log.txt => can't create!";
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				e.printStackTrace();
				
				return;
				
			}
			
		}//if (len > CONS.DB.logFile_MaxSize)
		
		////////////////////////////////

		// write

		////////////////////////////////
		try {
			
			//REF append http://stackoverflow.com/questions/8544771/how-to-write-data-with-fileoutputstream-without-losing-old-data answered Dec 17 '11 at 12:37
			FileOutputStream fos = new FileOutputStream(fpath_Log, true);
//			FileOutputStream fos = new FileOutputStream(fpath_Log);
			
			String text = String.format(Locale.JAPAN,
							"[%s] [%s : %d] %s\n", 
							Methods.conv_MillSec_to_TimeLabel(
											Methods.getMillSeconds_now()),
							fileName, lineNumber,
							message
						);
			
			//REF getBytes() http://www.adakoda.com/android/000240.html
			fos.write(text.getBytes());
//			fos.write(message.getBytes());
			
//			fos.write("\n".getBytes());
			
			fos.close();
			
			// Log
			String msg_Log = "log => written";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
//			FileChannel oChannel = new FileOutputStream(fpath_Log).getChannel();
//			
//			oChannel.wri
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}//write_Log

	public static String
	conv_MillSec_to_TimeLabel(long millSec)
	{
		//REF http://stackoverflow.com/questions/7953725/how-to-convert-milliseconds-to-date-format-in-android answered Oct 31 '11 at 12:59
		String dateFormat = CONS.Admin.format_Date;
//		String dateFormat = "yyyy/MM/dd hh:mm:ss.SSS";
		
		DateFormat formatter = new SimpleDateFormat(dateFormat, Locale.JAPAN);
//		DateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in milliseconds to date. 
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(millSec);
		
		return formatter.format(calendar.getTime());
		
	}//conv_MillSec_to_TimeLabel(long millSec)

}//public class Methods

