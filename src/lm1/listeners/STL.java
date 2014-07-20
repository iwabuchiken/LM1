package lm1.listeners;

import lm1.utils.Methods;
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

	public void onSwipeRight() {
		
		// Log
		String msg_Log = "Swipe right";
		Log.d("STL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
	}

	public void onSwipeLeft() {
		
		// Log
		String msg_Log = "Swipe left";
		Log.d("STL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		Methods.start_Activity_ShowList(actv);
		
	}

	public void onSwipeTop() {
	}

	public void onSwipeBottom() {
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		//REF http://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures answered Oct 21 '13 at 22:33
		return gestureDetector.onTouchEvent(event);
		
//		return false;
	}

		
}