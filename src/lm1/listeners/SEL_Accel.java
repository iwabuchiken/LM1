package lm1.listeners;

import lm1.main.R;
import lm1.utils.CONS;
import lm1.views.DrawableView;
import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

// SEL=SensorEventListener
public class SEL_Accel implements SensorEventListener {

	Activity actv;
	
	DrawableView drawableView;
	
    private float lowX;
    private float lowY;
    private float lowZ;

    // ���[�p�X�t�B���^�Ώ۔͈�
    private static final float FILTERING_VALUE = 0.2f;

	public SEL_Accel(Activity actv) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		
	}

	public SEL_Accel(Activity actv, DrawableView drawableView) {
		
		this.actv			= actv;
		
		this.drawableView	= drawableView;
		
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

        float x = event.values[SensorManager.DATA_X];
        float y = event.values[SensorManager.DATA_Y];
        float z = event.values[SensorManager.DATA_Z];

        // ���[�p�X�t�B���^����(����g�U��(��̐k��������ȐU���̉e��)������)
        lowX = getLowPassFilterValue(x, lowX);
        lowY = getLowPassFilterValue(y, lowY);
        lowZ = getLowPassFilterValue(z, lowZ);

        switch (event.sensor.getType()) {

        // ���m���ꂽ�Z���T�[��񂪉����x�Z���T�[�̏ꍇ
        case Sensor.TYPE_ACCELEROMETER:
        	
        	if (drawableView != null) {
        		
        		drawableView.effectAccelaration(lowX, lowY, lowZ);
        		
        		// ��ʂ��ĕ`��
        		drawableView.invalidate();
				
			} else {
				
				// Log
				String msg_Log = "drawableView => null";
				Log.d("SEL_Accel.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
			}
        	
            break;

        // �����x�Z���T�[�ȊO�͖���
        default:
            break;
        }
        
	}//public void onSensorChanged(SensorEvent event)

	private float getLowPassFilterValue(float eventValue, float lowValue) {
        return eventValue * FILTERING_VALUE + lowValue
            * (1.0f - FILTERING_VALUE);
    }

}
