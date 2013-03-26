package lm1.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CONS {
	
	public static class Admin {
		
		public static final double INITIAL_LONGITUTDE_VALUE = 100;
		public static final double INITIAL_LATITUDE_VALUE = 200;
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
	
}//public class CONS
