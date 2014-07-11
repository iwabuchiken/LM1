package lm1.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
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

	}
	
	public static class Main {

		public static LocationManager locationManager_;

		// LocationProvider
		public static LocationProvider locationProvider_;
			
		// Longitude
		public static double longitude = CONS.Admin.INITIAL_LONGITUTDE_VALUE;
		
		// Longitude
		public static double latitude = CONS.Admin.INITIAL_LATITUDE_VALUE;

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
			
		};
		
		public static final String[] col_types_Locations = {
			
			"TEXT", "TEXT",			// 0, 1
			
		};
		
		public static final String[] col_names_Locations_full = {
			
			android.provider.BaseColumns._ID,	// 0
			"created_at", "modified_at",		// 1, 2
			"longitude", "latitude",			// 3, 4
			
		};

	}
}//public class CONS
