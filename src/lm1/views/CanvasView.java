package lm1.views;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

// �L�����o�X�r���[�N���X
public class CanvasView extends View {
	// �`�悷��_�i�[�p���X�g
	private ArrayList<Point> points;
	// Paint�C���X�^���X
	private Paint paint;

	// �R���X�g���N�^
	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);

		// �`�悷��_�i�[�p���X�g����
		points = new ArrayList<Point>();

		// Paint(�M)�̐ݒ�
		paint = new Paint();
		paint.setColor(0xFF4444FF);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(3);
	}

	// onMeasure���\�b�h(�r���[�̃T�C�Y�ݒ菈��)
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}

	// onTouchEvent���\�b�h(�^�b�`�C�x���g)
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			// ��ʂɎw���t�����܂��͓����Ă���ꍇ�͂��̍��W��ݒ�
			points.add(new Point((int) event.getX(), (int) event.getY()));
			break;
		case MotionEvent.ACTION_UP:
			// ��ʂ���w�������ꂽ�ꍇ�̓f���~�^��ݒ�
			points.add(new Point(-1, -1));
			break;
		default:
			break;
		}

		this.invalidate();

		return true;
	}

	// onDraw���\�b�h(�`�揈��)
	@Override
	protected void onDraw(Canvas canvas) {
		// �L�����o�X�̔w�i�𔒂ɐݒ�
		canvas.drawColor(Color.WHITE);

		// �`�揈��
		Point sp = new Point(-1, -1);
		for (Point ep : points) {
			if (sp.x >= 0) {
				if (ep.x >= 0) {
					canvas.drawLine(sp.x, sp.y, ep.x, ep.y, paint);
				} else {
					canvas.drawPoint(sp.x, sp.y, paint);
				}
			}

			sp = ep;
		}
	}

	// clearDrawList���\�b�h(�N���A����)
	public void clearDrawList() {
		points.clear();
		this.invalidate();
	}
}
