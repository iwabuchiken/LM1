package lm1.listeners.buttons;

import lm1.utils.Methods;
import lm1.utils.Tags;
import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;

public class BO_LCL implements OnLongClickListener {

	Activity actv;
	
	Vibrator vib;

	public BO_LCL(Activity actv) {
		
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public ButtonOnLongClickListener(Activity actv)

	public boolean onLongClick(View v) {
		/*********************************
		 * 0. Vibrate
		 * 1. Get tag
		 *********************************/
//		vib.vibrate(Methods.vibLength_click);
		
		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
		
		// Log
		Log.d("ButtonOnLongClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tag.name()=" + tag.name());
		
		switch (tag) {
		
		default:
			break;
		
		}//switch (tag)
		
		return false;
	}//public boolean onLongClick(View v)

}//public class ButtonOnLongClickListener implements OnLongClickListener
