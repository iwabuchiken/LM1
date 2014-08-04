package lm1.listeners;

import lm1.utils.CONS;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

// SEL=SensorEventListener
public class SEL implements SensorEventListener {

	Activity actv;
	
	boolean bl_Tmp = false;
	
	
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
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

        // センサーが検知した値を取得
        float x = event.values[SensorManager.DATA_X];
        float y = event.values[SensorManager.DATA_Y];
        float z = event.values[SensorManager.DATA_Z];

        // ローパスフィルタ処理(高周波振動(手の震えや微妙な振動の影響)を除去)
        lowX = getLowPassFilterValue(x, lowX);
        lowY = getLowPassFilterValue(y, lowY);
        lowZ = getLowPassFilterValue(z, lowZ);

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

    private float getLowPassFilterValue(float eventValue, float lowValue) {
        return eventValue * FILTERING_VALUE + lowValue
            * (1.0f - FILTERING_VALUE);
    }

}
