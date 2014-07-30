package lm1.listeners.buttons;

import java.io.File;

import lm1.main.R;
import lm1.utils.CONS;
import lm1.utils.DBUtils;
import lm1.utils.Methods;
import lm1.utils.Methods_LM1;
import lm1.utils.Methods_dlg;
import lm1.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class BO_CL implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	Button bt_Monitor;	// ACTVMAIN_BT_MONITOR
	
	//
	Vibrator vib;
	
	//
	public BO_CL(Activity actv) {
		//
		this.actv = actv;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public BO_CL(Activity actv, Button bt_Monitor) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		this.bt_Monitor	= bt_Monitor;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

	}

	public void onClick(View v) {
//		//
		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
//
//		vib.vibrate(Methods.vibLength_click);
		
		/*********************************
		 * 1. actv_play.xml
		 * 
		 * 2. main.xml
		 *********************************/
		//
		switch (tag) {
		
		case actv_main_bt_go://------------------------------------
			
			case_actv_main_bt_go();
		
			break;
		
		case ACTVMAIN_BT_GETDATA://------------------------------------
			
			case_ACTVMAIN_BT_GETDATA();
			
			break;
			
		case ACTVMAIN_BT_SAVEDATA://------------------------------------
			
			case_ACTVMAIN_BT_SAVEDATA();
			
			break;
			
		case ACTVMAIN_BT_SHOW_MAP://------------------------------------
			
			case_ACTVMAIN_BT_SHOW_MAP();
			
			break;
			
		case ACTVMAIN_BT_MONITOR://------------------------------------
			
			case_ACTVMAIN_BT_MONITOR();
			
			break;
			
		case ACTVMAIN_BT_STOP_MONITOR://------------------------------------
			
			case_ACTVMAIN_BT_STOP_MONITOR();
			
			break;
			
		case ACTV_SHOWLIST_IB_BACK://------------------------------------
			
			case_ACTV_SHOWLIST_IB_BACK();
			
			break;
			
		default:
			break;
		}//switch (tag)
		
	}//public void onClick(View v)

	private void case_ACTV_SHOWLIST_IB_BACK() {
		// TODO Auto-generated method stub
		actv.finish();
		
		actv.overridePendingTransition(0, 0);

	}

	private void 
	case_ACTVMAIN_BT_STOP_MONITOR() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// view

		////////////////////////////////
		bt_Monitor.setTag(Tags.ButtonTags.ACTVMAIN_BT_MONITOR);
		
		bt_Monitor.setText(actv.getString(R.string.actv_main_tv_monitor));

		////////////////////////////////

		// stop

		////////////////////////////////
		Methods_LM1.stop_Monitor(actv);
		
	}

	private void 
	case_ACTVMAIN_BT_MONITOR() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// validate: pref set?

		////////////////////////////////
		long base_LocID = Methods.get_Pref_Long(
						actv, 
						CONS.Pref.pname_MainActv, 
						CONS.Pref.pkey_MainActv_CurrentBase, 
						CONS.Pref.dflt_LongExtra_value);
		
		if (base_LocID == CONS.Pref.dflt_LongExtra_value) {
			
			String message = "Base loc => not set";
			Methods_dlg.dlg_ShowMessage(actv, message);
			
			return;
			
		}
		
		
		////////////////////////////////

		// view

		////////////////////////////////
		bt_Monitor.setTag(Tags.ButtonTags.ACTVMAIN_BT_STOP_MONITOR);
		
		bt_Monitor.setText(actv.getString(R.string.actv_main_tv_stop_monitor));

		////////////////////////////////

		// setup

		////////////////////////////////
		Methods_LM1.start_Monitor(actv);
	}

	private void case_ACTVMAIN_BT_SHOW_MAP() {
		// TODO Auto-generated method stub
		
		
		
	}

	private void 
	case_ACTVMAIN_BT_SAVEDATA() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// validate

		////////////////////////////////
		if (CONS.Main.locationObtained == false
				&& CONS.Main.longitude != null
				&& CONS.Main.latitude != null) {
			
			String message = "Sorry. Can't get the location data";
			Methods_dlg.dlg_ShowMessage_Duration(actv, message, 3000);
			
			return;
			
		}
		
		////////////////////////////////

		// save: data

		////////////////////////////////
//		String message = "Saving the location data";
//		Methods_dlg.dlg_ShowMessage_Duration(actv, message, 3000);
		
		boolean res = DBUtils.save_LocationData(
						actv, 
						CONS.DB.tname_Locations,
						CONS.DB.col_names_Locations,
						new String[]{
							
							String.format(
									"%3.9f", CONS.Main.longitude.doubleValue()),
							String.format(
									"%3.9f", CONS.Main.latitude.doubleValue()),
							null, null
//							String.valueOf(CONS.Main.longitude.longValue()),
//							String.valueOf(CONS.Main.latitude.longValue())
						}
		);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String message = null;
		
		if (res == true) {
		
			message = "Location => saved";
			
		} else {
			
			message = "Location => couldn't be saved";
			
		}
		
		Methods_dlg.dlg_ShowMessage(actv, message);
		
		////////////////////////////////

		// update: list

		////////////////////////////////
		if (CONS.ShowList.loc_List != null) {
			
			CONS.ShowList.loc_List.clear();
			
			CONS.ShowList.loc_List.addAll(DBUtils.get_LocList(actv));
			
			CONS.ShowList.adp_Loc.notifyDataSetChanged();
			
		}
		
//		CONS.Main.loc_List.clear();
//		
//		CONS.Main.loc_List.addAll(DBUtils.get_LocList(actv));
//		
//		CONS.Main.adp_Loc.notifyDataSetChanged();
//		
		
//		Methods.save_LocationData_Current(actv);
		
	}//case_ACTVMAIN_BT_SAVEDATA()

	private void case_ACTVMAIN_BT_GETDATA() {
		// TODO Auto-generated method stub
		
	}

	private void case_actv_main_bt_go() {
		// TODO Auto-generated method stub
		Methods_LM1.showLongNLat(actv, CONS.Main.longitude, CONS.Main.latitude);
	}

}//public class ButtonOnClickListener implements OnClickListener

	