package lm1.listeners.buttons;

import java.io.File;

import lm1.utils.Methods;
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

public class ButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	//
	public ButtonOnClickListener(Activity actv) {
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
		
		default:
			break;
		}//switch (tag)
		
	}//public void onClick(View v)

	private void case_actv_main_bt_go() {
		// TODO Auto-generated method stub
//		Methods_LM1.showLatNLong(actv);
	}

}//public class ButtonOnClickListener implements OnClickListener

	