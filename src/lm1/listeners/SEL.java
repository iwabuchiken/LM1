package lm1.listeners;

import lm1.main.R;
import lm1.utils.CONS;
import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

// SEL=SensorEventListener
public class SEL implements SensorEventListener {

	Activity actv;
	
	boolean bl_Tmp = false;

	int val_Blue;		// photo sensor
	
    // ローパスフィルタ用変数
    private float lowX;
    private float lowY;
    private float lowZ;

 // ローパスフィルタ対象範囲
    private static final float FILTERING_VALUE = 0.2f;
    
	public SEL(Activity actv) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		
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

//        // センサーが検知した値を取得
//        float x = event.values[SensorManager.DATA_X];
//        float y = event.values[SensorManager.DATA_Y];
//        float z = event.values[SensorManager.DATA_Z];
//
//        // ローパスフィルタ処理(高周波振動(手の震えや微妙な振動の影響)を除去)
//        lowX = getLowPassFilterValue(x, lowX);
//        lowY = getLowPassFilterValue(y, lowY);
//        lowZ = getLowPassFilterValue(z, lowZ);

        switch (event.sensor.getType()) {

        // 検知されたセンサー情報が加速度センサーの場合
        case Sensor.TYPE_ACCELEROMETER:
        	
//        	CONS.PM.tv_X.setText(String.valueOf(lowX));
//        	CONS.PM.tv_X.setText(
//        			Methods.conv_Float2String(actv, lowX, "0.000000", 6));
        	
        	
//            drawableView.effectAccelaration(lowX, lowY, lowZ);
//
//            // 画面を再描画
//            drawableView.invalidate();
            break;

        case Sensor.TYPE_LIGHT:
        	
        	case_TYPE_LIGHT(event);
//        	// Log
//			String msg_Log = "Type => Light";
//			Log.d("SEL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
        	
        	break;
        	
        case Sensor.TYPE_MAGNETIC_FIELD:
        	
        	case_TYPE_MAGNETIC_FIELD(event);
//        	// Log
//			String msg_Log = "Type => Light";
//			Log.d("SEL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
        	
        	break;
        	
        case Sensor.TYPE_GYROSCOPE:
        	
        	case_TYPE_GYROSCOPE(event);
//        	// Log
//			String msg_Log = "Type => Light";
//			Log.d("SEL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
        	
        	break;
        	
//        case Sensor.TYPE_TEMPERATURE:
//        	
//        	case_TYPE_TEMPERATURE(event);
////        	// Log
////			String msg_Log = "Type => Light";
////			Log.d("SEL.java" + "["
////					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////					+ "]", msg_Log);
//        	
//        	break;
        	
        // 加速度センサー以外は無視
        default:
            break;
        }

		
//		if (bl_Tmp == false) {
//			
//			// Log
//			String log_msg = "onSensorChanged";
//			
//			Log.d("[" + "SEL.java : "
//					+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ " : "
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", log_msg);
//			
//			bl_Tmp = true;
//			
//		}//if (bl_Tmp == false)
		
	}//public void onSensorChanged(SensorEvent event)

//    private void 
//    case_TYPE_TEMPERATURE(SensorEvent event) {
//		// TODO Auto-generated method stub
//		
//    	// Log
//		String msg_Log = "Temperature => event.values.lengthevent = "
//						+ event.values.length;
//		Log.d("SEL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//    	
//	}

	private void case_TYPE_GYROSCOPE(SensorEvent event) {
		// TODO Auto-generated method stub
    	// Log
		String msg_Log = "magnetic => event.values.lengthevent = "
						+ event.values.length;
		Log.d("SEL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	private void case_TYPE_MAGNETIC_FIELD(SensorEvent event) {
		// TODO Auto-generated method stub

//    	// Log
//		String msg_Log = "magnetic => event.values.lengthevent = "
//						+ event.values.length;
//		Log.d("SEL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);

	}

	private void case_TYPE_LIGHT(SensorEvent event) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// text

		////////////////////////////////
    	TextView tv_Photo = (TextView) actv.findViewById(R.id.actv_sensors_2_tv_photo_val);
    	
    	tv_Photo.setText(String.format("%05.1f", event.values[0]));

    	////////////////////////////////

		// color

		////////////////////////////////
    	val_Blue = _conv_RawValue_To_RGB(event);
    	
    	
//    	val_Blue = (int) Math.floor(255 * (event.values[0] / 28000));
//    	
//    	val_Blue += 50;
//    	
//    	if (val_Blue > 255) {
//			
//    		val_Blue = 255;
//    		
//		}
    	
//    	// Log
//    	String msg_Log = "val_Blue => " + val_Blue;
//    	Log.d("SEL.java" + "["
//    			+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//    			+ "]", msg_Log);
//    	
//    	float gamma = 0.5f;
//    	
//    	val_Blue = (int) Math.pow(val_Blue, (1 / gamma));
//    	
//    	// Log
//		msg_Log = "val_Blue(processed) => " + val_Blue;
//		Log.d("SEL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
    	
    	TextView tv_Color = 
    				(TextView) actv.findViewById(R.id.actv_sensors_2_tv_photo_color);
    	
    	tv_Color.setBackgroundColor(Color.rgb(0, 0, val_Blue));
//    	tv_Color.setTextColor(Color.rgb(0, 0, val_Blue));
    	
//    	tv_Photo.setText(String.format("%5.1f", event.values[0]));
//    	tv_Photo.setText(String.valueOf(event.values[0]));
    	
//    	// Log
//		String msg_Log = "Type: Light => values.length = " + event.values.length;
//		Log.d("SEL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		for (int i = 0; i < event.values.length; i++) {
//			
//			//REF http://developer.android.com/reference/android/hardware/SensorEvent.html#values
//			// Log
//			msg_Log = String.format(
//					"values: %f, %f, %f", 
//					event.values[0], event.values[1], event.values[2]);
//			
//			Log.d("SEL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//		}
    	
	}

	private int 
	_conv_RawValue_To_RGB(SensorEvent event) {
		// TODO Auto-generated method stub
		
		// Prepare values used in the powering
		float powered = event.values[0] / CONS.Sensors.PhotoSensor_MaxVal;
		float powerer = (1 / (float)CONS.Sensors.gammaVal);
//		float powerer = (1 / CONS.Sensors.gammaVal);

		
		// Gamma-adjust the raw data
		int gammaAdjusted = (int) (
					CONS.Sensors.PhotoSensor_MaxVal * 
					Math.pow(
							powered, 
//							event.values[0] / CONS.Sensors.PhotoSensor_MaxVal, 
//							(1 / CONS.Sensors.gammaVal)
							powerer
							)
					);
		
		// Convert the raw data to RGB value
		float temp = CONS.Sensors.RGB_MaxVal *
				((float)gammaAdjusted / CONS.Sensors.PhotoSensor_MaxVal);
		
		// Return the floor
		return (int) Math.floor(temp);
//		int rgbVal = (int) Math.floor(temp);
		
//		int rgbVal = CONS.Sensors.RGB_MaxVal *
//				(gammaAdjusted / CONS.Sensors.PhotoSensor_MaxVal);
		
//		// Log
//		String msg_Log = 
//						"powerer = " + powerer
//						+ " / "
//						+ "powered = " + powered
//						+ " / "
//						+ "original = " + event.values[0]
//						+ " / "
//						+ "gammaAdjusted = " + gammaAdjusted
//						+ " / "
//						+ "rgbVal = " + rgbVal;
//		Log.d("SEL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
//		return rgbVal;
		
	}//_conv_RawValue_To_RGB(SensorEvent event)

	private float getLowPassFilterValue(float eventValue, float lowValue) {
        return eventValue * FILTERING_VALUE + lowValue
            * (1.0f - FILTERING_VALUE);
    }

}
