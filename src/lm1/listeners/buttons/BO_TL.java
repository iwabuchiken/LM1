package lm1.listeners.buttons;

import lm1.main.R;
import lm1.utils.CONS;
import lm1.utils.CONS.Enums.ColorNames;
import lm1.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class BO_TL implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	public BO_TL(Activity actv) {
		//
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
	}

//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			
			switch (tag) {
			case actv_main_bt_go://------------------------------------
				
				case_bcGray_tcWhite(v);
				
				break;// case actv_main_bt_go
				
			case ACTVMAIN_BT_GETDATA://------------------------------------
			case ACTVMAIN_BT_SAVEDATA://------------------------------------
			case ACTVMAIN_BT_SHOW_MAP://------------------------------------
				
				_Set_Color(v,
						Color.WHITE, 
						Color.BLACK);
				
				break;// case actv_main_bt_go
				
				
			default:
				break;
			}//switch (tag)
			
			break;//case MotionEvent.ACTION_DOWN:

			
		case MotionEvent.ACTION_UP:
			switch (tag) {

			case actv_main_bt_go://------------------------------------
				
				case_bcWhite_tcBlack(v);
				
				break;// case actv_main_bt_go

			case ACTVMAIN_BT_GETDATA://------------------------------------
				
				_Set_Color(v,
						Color.BLUE, 
//						R.color.gray1, 
						Color.WHITE);
//				Color.WHITE);
				
				break;// case actv_main_bt_go
				
			case ACTVMAIN_BT_SAVEDATA://------------------------------------				
				
				_Set_Color(v,
						Color.GREEN, 
//						R.color.gray1, 
						Color.WHITE);
//				Color.WHITE);
				
				break;// case actv_main_bt_go

			case ACTVMAIN_BT_SHOW_MAP://------------------------------------
				_Set_Color(v,
						Color.YELLOW, 
//						R.color.gray1, 
						Color.BLUE);
//				Color.WHITE);
				
				break;// case actv_main_bt_go
				
			default:
				break;

			}//switch (tag)
			
			break;//case MotionEvent.ACTION_UP:
			
		default:
			break;

		}//switch (event.getActionMasked())
		
		
		return false;
	}//public boolean onTouch(View v, MotionEvent event)

	/******************************
		@param bgcolor => Color.XXX<br>
		@param txtcolor => same
	 ******************************/
	private void 
	_Set_Color
	(View v, int bgcolor, int txtcolor) {
		// TODO Auto-generated method stub
		v.setBackgroundColor(bgcolor);
		
		TextView tv = (TextView) v;
		
		tv.setTextColor(txtcolor);
		
	}

	private void case_bcWhite_tcBlack(View v) {
		// TODO Auto-generated method stub
		v.setBackgroundColor(Color.WHITE);
		
		TextView tv = (TextView) v;
		
		tv.setTextColor(Color.BLACK);
		
	}

	private void case_bcGray_tcWhite(View v) {
		// TODO Auto-generated method stub
		v.setBackgroundColor(Color.GRAY);

		((TextView)v).setTextColor(Color.WHITE);

	}


}//public class ButtonOnTouchListener implements OnTouchListener
