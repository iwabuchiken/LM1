package lm1.listeners;

import java.util.Locale;

import lm1.main.R;
import lm1.utils.CONS;
import lm1.views.CanvasView_4;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

// SEL=SensorEventListener
public class SEL_Magnetic implements SensorEventListener {

	Activity actv;
	
	boolean bl_Tmp = false;

	int val_Blue;		// photo sensor
	
    // ローパスフィルタ用変数
    private float lowX;
    private float lowY;
    private float lowZ;

	private CanvasView_4 canvas;

 // ローパスフィルタ対象範囲
    private static final float FILTERING_VALUE = 0.2f;
    
	public SEL_Magnetic(Activity actv) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		
	}

	
	public SEL_Magnetic
	(Activity actv, CanvasView_4 canvas) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		
		this.canvas = canvas;
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
		// Log
		String msg_Log = "onAccuracyChanged()";
		Log.d("SEL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

        switch (event.sensor.getType()) {

        case Sensor.TYPE_MAGNETIC_FIELD:
        	
        	case_TYPE_MAGNETIC_FIELD(event);
        	
        	break;
        	
        // 加速度センサー以外は無視
        default:
            break;
        }

	}//public void onSensorChanged(SensorEvent event)

	private void 
	case_TYPE_MAGNETIC_FIELD(SensorEvent event) {
		// TODO Auto-generated method stub

		float[] values = event.values;
		
		String values_str = String.format(
				Locale.JAPAN,
				"%f/%f/%f", 
				values[0], values[1], values[2]);
		////////////////////////////////

		// paint

		////////////////////////////////
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
//		paint.setColor(0xFF4444FF);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(10);
//		paint.setStrokeWidth(30);

//		Color col = new Color();

		
//		this.canvas.drawLine(
		this.canvas.draw_Line(
						values[0] + 100, 
						values[1] + 100,
						values[0] + values[2] * 20, 
						values[1] + values[2] * 20, 
//						Math.abs(values[0]), Math.abs(values[1] * 20), 
						paint);
		
//		////////////////////////////////
//
//		// canvas view
//
//		////////////////////////////////
//		int canvas_Height = this.canvas.getHeight();
//		
//		// Log
//		String msg_Log = "height => " + canvas_Height;
//		Log.d("SEL_Magnetic.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		Canvas c = new Canvas();
//		
//		c.drawLine(10, 10, Math.abs(values[0]), Math.abs(values[1] * 20), paint);
//		
//		c.save();
//		
//		
//		
//		this.canvas.draw(c);
//		
//		this.canvas.setBackgroundColor(Color.YELLOW);
//		
//		this.canvas.invalidate();

		
    	// Log
//		String msg_Log = "magnetic => event.values.lengthevent = "
//						+ event.values.length;
		Log.d("SEL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", values_str);

	}

}
