package lm1.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lm1.adapters.Adp_Loc;
import lm1.items.Loc;
import lm1.tasks.TaskAudioTrack;
import android.content.SharedPreferences;
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
		
		public static String dPath_dbFile_backup = "/mnt/sdcard-ext/lm1_backup";
		
//		public static String dPath_dbFile = 
//							Methods.get_DirPath(new MainActv().getFilesDir().getPath());
		
		public static String fname_DB_Backup_Trunk = "lm1_backup";
		
		public static String fname_DB_Backup_ext = ".bk";

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
		
		public static final int dflt_distance_buffer	= 10;
		
	}

	public static class ShowList {
		
		// Adapter
		public static Adp_Loc adp_Loc;
		
		public static List<Loc> loc_List;
		
		// views
		public static ListView lv_ShowList;
		
		
	}
}//public class CONS
