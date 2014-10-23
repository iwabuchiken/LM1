package lm1.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



import lm1.adapters.Adp_Loc;
import lm1.adapters.Adp_LogFileList;
import lm1.adapters.Adp_ShowLogFile_List;
import lm1.items.Loc;
import lm1.items.LogItem;
import lm1.tasks.TaskAudioTrack;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.AudioTrack;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CONS {
	
	public static class Admin {
		
		public static final double INITIAL_LONGITUTDE_VALUE = 100;
		public static final double INITIAL_LATITUDE_VALUE = 200;
		
		////////////////////////////////

		// Utilities

		////////////////////////////////
		public static Vibrator vib;
		
		public static final int vibLength_click = 35;
		
		public static final String format_Date = "yyyy/MM/dd hh:mm:ss.SSS";
//		public static final String date_Format = "yyyy/MM/dd hh:mm:ss.SSS";
		
		public static final String format_Clock = "%02d:%02d";
		
		public static final int MaximumFractionDigits	= 9;

	}
	
	public static class Main {

		////////////////////////////////

		// Location

		////////////////////////////////
		public static LocationManager locationManager_;

		// LocationProvider
		public static LocationProvider locationProvider_;
		
		public static boolean locationObtained = false; 

		public static LocationManager mLocationManager;
			
		// Longitude
		public static Double longitude;
//		public static double longitude = CONS.Admin.INITIAL_LONGITUTDE_VALUE;
		
		// Longitude
		public static Double latitude;
//		public static double latitude = CONS.Admin.INITIAL_LATITUDE_VALUE;

		// monitor
		public static boolean monitor	= false;
		
		public static boolean msg_OutOfRange	= false;
		
		public static double distance_Base;
		
		public static double distance_Current;
		
		public static double distance_Diff;
		
		public static Loc loc_Base;
		
		public static Loc loc_Ref;
		
		public static Loc loc_Current;
		
		public static TaskAudioTrack task_Audio;
		
		public static AudioTrack audioTrack;
		
		public static int distance_BufferRange	= 10;	// unit => meter
		
		////////////////////////////////

		// List

		////////////////////////////////
		// Adapter
		public static Adp_Loc adp_Loc;
		
		public static List<Loc> loc_List;
		
		// views
		public static ListView lv_Main;
		
		
		
	}//public static class Main

	public static class DB {

		////////////////////////////////

		// Paths and names

		////////////////////////////////
		public static String dbName = "lm1.db";
		
		public static String dPath_dbFile;
//		public static String dPath_dbFile = "/data/data/lm1.main/databases";
		
//		public static String dPath_dbFile_backup = "/mnt/sdcard-ext/lm1_backup";
		
//		public static String dPath_dbFile = 
//							Methods.get_DirPath(new MainActv().getFilesDir().getPath());
		
		public static String fname_DB_Backup_Trunk = "lm1_backup";
		
		public static String fname_DB_Backup_ext = ".bk";

		public static String dirName_ExternalStorage = "/mnt/sdcard-ext";
		
		public final static String dPath_Data_Root = 
									dirName_ExternalStorage + "/lm1_data";
//		public final static String dPath_Data_Root = "/mnt/sdcard-ext/ta2_data";
		
		public static String dPath_dbFile_Backup = dPath_Data_Root + "/backup";
		
		public final static String dPath_Data = dPath_Data_Root + "/data";
		
		public final static String dPath_Log = dPath_Data_Root + "/log";
		
		public final static String fname_Log = "log.txt";

//		public static String dirPath_db = "/data/data/shoppinglist.main/databases";
		public static String dirPath_db = "/data/data/sl.main/databases";
		
		public static String fileName_db_backup_trunk = "shoppinglist_backup";

		public static String fileName_db_backup_ext = ".bk";

		public static String dirPath_dbFile_Backup_SL_1 = 
						dirName_ExternalStorage + "/ShoppingList_backup";
		
		////////////////////////////////
		
		// Table: Locations
		
		////////////////////////////////
		public static final String tname_Locations = "locations";
//		public static final String tname_Locations = "cm7";

		public static final String[] col_names_Locations = {
			
			"longitude", "latitude",	// 0, 1
			"memo",						// 2
			"uploaded_at"				// 3
			
		};
		
		public static final String[] col_types_Locations = {
			
			"TEXT", "TEXT",			// 0, 1
			"TEXT",					// 2
			"TEXT",					// 3
			
		};
		
		public static final String[] col_names_Locations_full = {
			
			android.provider.BaseColumns._ID,	// 0
			"created_at", "modified_at",		// 1, 2
			"longitude", "latitude",			// 3, 4
			"memo",						// 5
			"uploaded_at",						// 6
			
		};

	}

	public static class Enums {
		
		public static enum ColorNames {
			
			RED,	GREY,	BROWN,	GREEN,
			WHITE,	YELLOW,	BLACK,
		}
		
	}
	
	public static class Pref {
		
		////////////////////////////////
		
		// Commons
		
		////////////////////////////////
		public static long dflt_LongExtra_value = -1;
		
		public static int dflt_IntExtra_value = -1;
		
		////////////////////////////////

		// Mainactv

		////////////////////////////////
		public static SharedPreferences prefs_MainActv;
		
		public static final String pname_MainActv = "pname_MainActv";
		
		public static final String pkey_MainActv_CurrentBase = 
											"pkey_MainActv_CurrentBase";
		
		public static final String pkey_MainActv_CurrentRef = 
				"pkey_MainActv_CurrentRef";
		
		public static final int dflt_distance_buffer	= 10;
		
		public static String pkey_CurrentPath = "pkey_CurrentPath";

		////////////////////////////////

		// SensorsActv

		////////////////////////////////
		public static final String pname_SensorsActv = "pname_SensorsActv";
		
		public static final String pkey_SensorsActv_GammaVal = 
											"pkey_SensorsActv_GammaVal";
		
		////////////////////////////////

		// LogActv

		////////////////////////////////
		public static String pkey_CurrentPosition_LogActv = 
									"pkey_CurrentPosition_LogActv";

	}

	public static class ShowList {
		
		// Adapter
		public static Adp_Loc adp_Loc;
		
		public static List<Loc> loc_List;
		
		// views
		public static ListView lv_ShowList;
		
		
	}
	
	public static class Sensors {
		
		public static SensorManager sensorManager;
		
		public static List<Sensor> sensor_Accelerometer;
		
		public static List<Sensor> sensor_Light;
		
		public static List<Sensor> sensor_Magnetic;
		
		public static List<Sensor> sensor_Temp;
		
		public static List<Sensor> sensor_Gyro;
		
		public static SensorEventListener sensorEventListener;

		public static int gammaVal;

		public static final int GammaVal_Dflt		= 5;
		
		public static final int PhotoSensor_MaxVal	= 28500;
		
		public static final int RGB_MaxVal	= 255;
		
	}

	public static class LogActv {
		
		public static List<String> list_LogFiles = null;
		
//		public static ArrayAdapter<String> adp_LogFile_List = null;
		
		public static Adp_LogFileList adp_LogFile_List;
		
	}

	public static class ShowLogActv {
		
		public static List<LogItem> list_ShowLog_Files = null;
		
//		public static ArrayAdapter<String> adp_LogFile_List = null;
		
		public static Adp_ShowLogFile_List adp_ShowLog_File_List;
		
		public static String fname_Target_LogFile = null;
		
		public static List<String> list_RawLines = null;
		
	}

	public static class Intent {
		
		////////////////////////////////

		// commons

		////////////////////////////////
		public static long dflt_LongExtra_value = -1;
		
		public static int dflt_IntExtra_value = -1;
		
		
		////////////////////////////////

		// MainActv

		////////////////////////////////
		public static String iKey_CurrentPath_MainActv = "current_path";

		////////////////////////////////
		
		// MemoEditActv
		
		////////////////////////////////
		public static String iKey_Memo_Id = "iKey_Memo_Id";
		
		
		/***************************************
		 * Request codes
		 ***************************************/
		public final static int REQUEST_CODE_SEE_BOOKMARKS = 0;
		
		public final static int REQUEST_CODE_HISTORY = 1;
		
		/***************************************
		 * Result code
		 ***************************************/
		public final static int RESULT_CODE_SEE_BOOKMARKS_OK = 1;
		
		public final static int RESULT_CODE_SEE_BOOKMARKS_CANCEL = 0;
		
		////////////////////////////////

		// PlayActv

		////////////////////////////////
		public final static String iKey_PlayActv_Memo_Id = "iKey_PlayActv_Memo_Id";
		
		// Used in Service_ShowProgress
		public static String iKey_PlayActv_TaskPeriod
											= "iKey_PlayActv_TaskPeriod";

		
		////////////////////////////////

		// ShowLogActv

		////////////////////////////////
		public static final String iKey_LogActv_LogFileName =
													"iKey_LogActv_LogFileName";
		

	}//public static class Intent

}//public class CONS
