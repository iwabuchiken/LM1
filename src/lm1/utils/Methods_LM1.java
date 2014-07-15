package lm1.utils;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.location.LocationManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import lm1.items.Loc;
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

	public static void 
	set_Base
	(Activity actv, 
		Loc loc, AdapterView<?> parent,
		int position_InListView, Dialog dlg1) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// set: pref

		////////////////////////////////
		boolean res = Methods.set_Pref_Long(
							actv, 
							CONS.Pref.pname_MainActv, 
							CONS.Pref.pkey_MainActv_CurrentBase, 
							loc.getId());
		
		/******************************
			validate
		 ******************************/
		if (res == false) {
			
			String msg = "Can't set preference";
			
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return;
			
		}
		
		////////////////////////////////

		// notify: adapter

		////////////////////////////////
		CONS.Main.adp_Loc.notifyDataSetChanged();
		
		////////////////////////////////

		// close: dlg

		////////////////////////////////
		dlg1.dismiss();
		
		////////////////////////////////

		// report

		////////////////////////////////
		String message = "Base => set to: " + loc.getLongitude();
		
		Methods_dlg.dlg_ShowMessage(actv, message);
		
	}//set_Base

//	public static List<Loc>
//	get_LocList(Activity actv) {
//		// TODO Auto-generated method stub
//		
//		
//		
//		return null;
//		
//	}//get_LocList(Activity actv)


}//public class Methods_LM1
