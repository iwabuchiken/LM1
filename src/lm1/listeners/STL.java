package lm1.listeners;

import lm1.utils.Methods;
import lm1.utils.Tags;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

//REF http://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures answered Oct 17 '12 at 16:19
public class STL implements OnTouchListener {

	Activity actv;
	
	Tags.SwipeTags tag;

	private final GestureDetector gestureDetector;

	public STL (Context ctx){
		gestureDetector = new GestureDetector(ctx, new GestureListener());
		
		this.actv	= (Activity) ctx;
		
		// Log
		String msg_Log = "detector => initialized";
		Log.d("STL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	private final class 
	GestureListener 
	extends SimpleOnGestureListener {

		private static final int SWIPE_THRESHOLD = 100;
		private static final int SWIPE_VELOCITY_THRESHOLD = 100;

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

		@Override
		public boolean 
		onFling
		(MotionEvent e1, MotionEvent e2, 
				float velocityX, float velocityY) {
			
			// Log
			String msg_Log = "onFling";
			Log.d("STL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			
			boolean result = false;
			try {
				float diffY = e2.getY() - e1.getY();
				float diffX = e2.getX() - e1.getX();
				if (Math.abs(diffX) > Math.abs(diffY)) {
					if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
						if (diffX > 0) {
							
							onSwipeRight();
							
						} else {
							
							onSwipeLeft();
							
						}
					}
				} else {
					if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
						if (diffY > 0) {
							onSwipeBottom();
						} else {
							onSwipeTop();
						}
					}
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			
			return true;
//			return result;
			
		}//onFling
		
	}//GestureListener

	public void 
	onSwipeRight() {
		
		// Log
		String msg_Log = "Swipe right";
		Log.d("STL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		switch(this.tag) {
		
		case SWIPE_ACTV_SHOWLIST:
		
			case_Right_SWIPE_ACTV_SHOWLIST();
			
			break;
			
		default:
			
			// Log
			msg_Log = "unknown tag => " + this.tag.toString();
			Log.d("STL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			break;
			
		}//switch(this.tag)

	}//onSwipeRight()

	private void 
	case_Right_SWIPE_ACTV_SHOWLIST() {
		// TODO Auto-generated method stub
		actv.finish();
		
		actv.overridePendingTransition(0, 0);

	}

	public void 
	onSwipeLeft() {
		
		// Log
		String msg_Log = "Swipe left";
		Log.d("STL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		switch(this.tag) {
		
		case SWIPE_ACTV_MAIN:
			
			Methods.start_Activity_ShowList(actv);
			
			break;
			
		case ACTV_SENSORS:

			Methods.start_Activity_MagneticActv(actv);
			
//			// Log
//			msg_Log = "ACTV_SENSORS";
//			Log.d("STL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
			
			break;
			
		default:
			
			// Log
			msg_Log = "unknown tag => " + this.tag.toString();
			Log.d("STL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			break;
			
		}//switch(this.tag)
		
		
	}//onSwipeLeft()

	public void 
	onSwipeTop() {
		
		// Log
		String msg_Log = "onSwipeTop()";
		Log.d("STL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		switch(this.tag) {
		
		case ACTV_SENSORS:
			
			actv.finish();
			
			actv.overridePendingTransition(0, 0);
			
			break;
			
		default:
			
			// Log
			msg_Log = "unknown tag => " + this.tag.toString();
			Log.d("STL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			break;
			
		}//switch(this.tag)
		
	}//onSwipeTop()

	public void 
	onSwipeBottom() {
		
		// Log
		String msg_Log = "onSwipeBottom()";
		Log.d("STL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		switch(this.tag) {
		
		case SWIPE_ACTV_MAIN:
			
			Methods.start_Activity_Sensor(actv);
			
			break;
			
		case ACTV_SENSORS:
			
			Methods.start_Activity_Accelero(actv);
			
			break;

		default:
			
			// Log
			msg_Log = "unknown tag => " + this.tag.toString();
			Log.d("STL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			break;
			
		}//switch(this.tag)

	}//onSwipeBottom()

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		Tags.SwipeTags tag = (Tags.SwipeTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			
			// Log
			String msg_Log = "tag => " + tag.toString();
			Log.d("STL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			this.tag = tag;
			
			break;
			
		default:
			
//			// Log
//			msg_Log = "(default) tag => " + tag.toString();
//			Log.d("STL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
			break;
			
		}
		
		
		//REF http://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures answered Oct 21 '13 at 22:33
		return gestureDetector.onTouchEvent(event);
		
//		return false;
	}

		
}