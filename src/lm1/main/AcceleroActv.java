package lm1.main;

import java.util.List;

import lm1.listeners.SEL;
import lm1.listeners.STL;
import lm1.utils.CONS;
import lm1.utils.Methods;
import lm1.utils.Tags;
import lm1.views.DrawableView;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AcceleroActv extends Activity {

    // SensorManager�C���X�^���X
    private SensorManager sensorManager;

    // DrawableView�C���X�^���X
    private DrawableView drawableView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actv_accelero);
//		setContentView(R.layout.actv_main_orig);
		
		this.setTitle(this.getClass().getName());
		
        drawableView = new DrawableView(this);

        // �o�b�N���C�g�������������Ȃ��悤�ɐݒ�
        drawableView.setKeepScreenOn(true);

        // ��ʂɕ\������DrawableView�C���X�^���X���w��
        setContentView(drawableView);

        // SensorManager�C���X�^���X�擾
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        // test
        drawableView._onDraw_Background();
        
        // Log
		String msg_Log = "_onDraw_Background => done";
		Log.d("AcceleroActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
//		CONS..mLocationManager.removeUpdates(this);
		
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
//		Methods.confirm_quit(this, keyCode);
		
//		this.onBackPressed();
		
//		this.finish();
//		
//		overridePendingTransition(0, 0);
//
//		
		// Log
		String msg_Log = "onKeyDown";
		Log.d("SensorsActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		////////////////////////////////

		// Finish actv

		////////////////////////////////
		this.finish();
		
		overridePendingTransition(0, 0);

		////////////////////////////////

		// onStop

		////////////////////////////////
		super.onStop();
		
	}//public void onBackPressed()

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
        // �����x�Z���T�[�I�u�W�F�N�g�擾
        List<Sensor> accelerometerSensors =
            sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        // �����x�Z���T�[�I�u�W�F�N�g���擾�ł����ꍇ
        if (accelerometerSensors.size() > 0) {
            // SensorManager�C���X�^���X�ɃZ���T�[�C�x���g���X�i�[��ݒ�
            sensorManager.registerListener(sensorEventListener,
                accelerometerSensors.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
        
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		
		_Setup_SetListeners();
		
		super.onStart();
		

	}//protected void onStart()

	private void _Setup_SetListeners() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		
		super.onStop();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actv_sensors, menu);
		return true;
	}

    private final SensorEventListener sensorEventListener =
            new SensorEventListener() {
                // ���[�p�X�t�B���^�p�ϐ�
                private float lowX;
                private float lowY;
                private float lowZ;

                // ���[�p�X�t�B���^�Ώ۔͈�
                private static final float FILTERING_VALUE = 0.2f;

                // onAccuracyChanged���\�b�h(���x�ύX���C�x���g)
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    // �����Ȃ�
                }

                // onSensorChanged���\�b�h(�Z���T�[���ύX���C�x���g)
                @Override
                public void onSensorChanged(SensorEvent event) {
                    // �Z���T�[�����m�����l���擾
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
                        drawableView.effectAccelaration(lowX, lowY, lowZ);

                        // ��ʂ��ĕ`��
                        drawableView.invalidate();
                        break;

                    // �����x�Z���T�[�ȊO�͖���
                    default:
                        break;
                    }
                }

                // ���[�p�X�t�B���^����(����g�U��(��̐k��������ȐU���̉e��)������)
                private float getLowPassFilterValue(float eventValue, float lowValue) {
                    return eventValue * FILTERING_VALUE + lowValue
                        * (1.0f - FILTERING_VALUE);
                }
            };

}//public class AcceleroActv extends Activity
