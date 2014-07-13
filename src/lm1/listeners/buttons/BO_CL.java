package lm1.listeners.buttons;

import java.io.File;

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

	//
	Vibrator vib;
	
	//
	public BO_CL(Activity actv) {
		//
		this.actv = actv;
		
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
			
		default:
			break;
		}//switch (tag)
		
	}//public void onClick(View v)

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

	