package lm1.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

}//public class Methods

