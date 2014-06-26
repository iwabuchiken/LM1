package lm1.utils;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.widget.TextView;
import android.widget.Toast;
import lm1.main.MainActv;
import lm1.main.R;

public class Methods_LM1 {

	public static void
	showLongNLat(Activity actv, Double longi, Double lati) {
		// TODO Auto-generated method stub
		/***************************************
		 * Data ready?
		 ***************************************/
		if (CONS.Main.longitude == CONS.Admin.INITIAL_LONGITUTDE_VALUE
				|| CONS.Main.latitude == CONS.Admin.INITIAL_LATITUDE_VALUE) {
			
			// debug
			Toast.makeText(actv, "Data not ready", Toast.LENGTH_LONG).show();
			
			return;
			
		}//if (CONS.Main.longitude == 0 || CONS.Main.latitude == 0)
		
		/***************************************
		 * Set: data
		 ***************************************/
		TextView tvLongi = (TextView) actv.findViewById(R.id.actv_main_tv_longi_data);
		tvLongi.setText(String.valueOf(longi));

		TextView tvLati = (TextView) actv.findViewById(R.id.actv_main_tv_lat_data);
		tvLati.setText(String.valueOf(lati));
		
	}//showLongNLat(Activity actv, Double longi, Double lati)


}//public class Methods_LM1
