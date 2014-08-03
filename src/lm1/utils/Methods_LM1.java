package lm1.utils;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.location.LocationManager;
import android.util.Log;
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
		CONS.ShowList.adp_Loc.notifyDataSetChanged();
//		CONS.Main.adp_Loc.notifyDataSetChanged();
		
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
	
	public static void 
	set_Ref
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
				CONS.Pref.pkey_MainActv_CurrentRef, 
//				CONS.Pref.pkey_MainActv_CurrentBase, 
				loc.getId());
		
		/******************************
			validate
		 ******************************/
		if (res == false) {
			
			String msg = "Can't set ref";
			
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return;
			
		}
		
		////////////////////////////////
		
		// notify: adapter
		
		////////////////////////////////
		CONS.ShowList.adp_Loc.notifyDataSetChanged();
//		CONS.Main.adp_Loc.notifyDataSetChanged();
		
		////////////////////////////////
		
		// close: dlg
		
		////////////////////////////////
		dlg1.dismiss();
		
		////////////////////////////////
		
		// report
		
		////////////////////////////////
		String message = "Ref => set to: " + loc.getLongitude();
		
		Methods_dlg.dlg_ShowMessage(actv, message);
		
	}//set_Ref

	public static void 
	show_Distance
	(Activity actv, 
		Loc loc, AdapterView<?> parent, 
		int position_InListView, Dialog dlg1) {
		// TODO Auto-generated method stub
		
		
		
	}

	public static void 
	start_Monitor(Activity actv) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// boolean

		////////////////////////////////
		CONS.Main.monitor = true;
		
		////////////////////////////////

		// setup: locs

		////////////////////////////////
		long base_LocID = Methods.get_Pref_Long(
				actv, 
				CONS.Pref.pname_MainActv, 
				CONS.Pref.pkey_MainActv_CurrentBase, 
				CONS.Pref.dflt_LongExtra_value);

		CONS.Main.loc_Base = DBUtils.get_Loc_FromId(actv, base_LocID);
		
		////////////////////////////////

		// calc: distance Base

		////////////////////////////////
		long id_LocRef = Methods.get_Pref_Long(
				actv, 
				CONS.Pref.pname_MainActv, 
				CONS.Pref.pkey_MainActv_CurrentRef, 
//				CONS.Pref.pkey_MainActv_CurrentBase, 
				CONS.Pref.dflt_LongExtra_value);
		
		double lat = 0;
		double longi = 0;
		
		// Log
		String msg_Log = "id_LocRef => " + id_LocRef;
		Log.d("Methods_LM1.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		if (id_LocRef == CONS.Pref.dflt_LongExtra_value) {
			
			lat = CONS.Main.latitude;
			longi = CONS.Main.longitude;
			
		} else {
			
			Loc loc_Ref = DBUtils.get_Loc_FromId(actv, id_LocRef);

			lat = Double.parseDouble(loc_Ref.getLatitude());
			longi = Double.parseDouble(loc_Ref.getLongitude());
//			longi = CONS.Main.longitude;
			
		}
		
		CONS.Main.distance_Base = 
					Methods.distance_2(
							lat, 
							longi, 
//							CONS.Main.latitude, 
//							CONS.Main.longitude, 
							Double.parseDouble(CONS.Main.loc_Base.getLatitude()), 
							Double.parseDouble(CONS.Main.loc_Base.getLongitude()));

		// Log
		msg_Log = "monitor => started";
		Log.d("Methods_LM1.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
	}//start_Monitor(Activity actv)

	
	public static void 
	stop_Monitor(Activity actv) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// boolean

		////////////////////////////////
		CONS.Main.monitor = false;
		
		CONS.Main.msg_OutOfRange = false;
		
		////////////////////////////////

		// setup: locs

		////////////////////////////////
		CONS.Main.loc_Base = null;
		
		CONS.Main.distance_Base = 0;

		// Log
		String msg_Log = "monitor => stopped";
		Log.d("Methods_LM1.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
	}

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
