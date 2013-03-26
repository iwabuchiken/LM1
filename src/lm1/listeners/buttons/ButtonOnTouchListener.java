package lm1.listeners.buttons;

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

public class ButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	public ButtonOnTouchListener(Activity actv) {
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
				
			default:
				break;
			}//switch (tag)
			
			break;//case MotionEvent.ACTION_DOWN:

			
		case MotionEvent.ACTION_UP:
			switch (tag) {

			case actv_main_bt_go://------------------------------------
				
				case_bcWhite_tcBlack(v);
				
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
