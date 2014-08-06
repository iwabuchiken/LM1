package lm1.views;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

// �L�����o�X�r���[�N���X
public class CanvasView_2 extends View {
	// �`�悷��_�i�[�p���X�g
	private ArrayList<Point> points;
	// Paint�C���X�^���X
	private Paint paint;

	
	////////////////////////////////

	// codes: DrawableView

	////////////////////////////////
	private Canvas c;
	
    // �~�̒��a
    private static int DIAMETER = 50;
//    private static final int DIAMETER = 50;

    // �~�̔��a
    private static final int RADIUS = DIAMETER / 2;

    // ��ʏ㉺�̃}�[�W��
    private static final int OFFSET_Y = 100;

    // ��ʍ��E�̃}�[�W��
    private static final int OFFSET_X = 0;

    // ���ƕ`��I�u�W�F�N�g�p�z��
    private ShapeDrawable[] drawables;

    // ���ƕ`��I�u�W�F�N�g�p�z��v�f��
    private static final int HOLES_SIZE = 5;

    // �ԋʂ��󂯂錊�̔z��ԍ�
    private static final int RED_OVAL = 0;

    // �΋ʂ��󂯂錊�̔z��ԍ�
    private static final int GREEN_OVAL = 1;

    // �ʂ��󂯂錊�̔z��ԍ�
    private static final int BLUE_OVAL = 2;

    // ���ʂ��󂯂錊�̔z��ԍ�
    private static final int WHITE_OVAL = 3;

    // �{�[���̕`��C���X�^���X
    private ShapeDrawable ballDrawables;

    // �{�[���̕\���ʒu
    private int movableBallX;
    private int movableBallY;

    // �{�[���̌��݂̐F
    private int currentColor;

    // �{�[���p�O���f�[�V������`
    private static final RadialGradient RADIAL_GRADIENT_BLUE =
        new RadialGradient(10, 10, RADIUS, Color.CYAN, Color.BLUE,
            Shader.TileMode.MIRROR);
    private static final RadialGradient RADIAL_GRADIENT_RED =
        new RadialGradient(10, 10, RADIUS, Color.YELLOW, Color.RED,
            Shader.TileMode.MIRROR);
    private static final RadialGradient RADIAL_GRADIENT_GREEN =
        new RadialGradient(10, 10, RADIUS, Color.WHITE, Color.GREEN,
            Shader.TileMode.MIRROR);
    private static final RadialGradient RADIAL_GRADIENT_WHITE =
        new RadialGradient(10, 10, RADIUS, Color.WHITE, Color.DKGRAY,
            Shader.TileMode.MIRROR);

    // �������{�[���̕`��I�u�W�F�N�g�z��
    private ShapeDrawable[] fallBallDrawables;

    // �������{�[���̐�
    private int fallCount;

    // �������{�[�����X�g�b�N���鐔
    private static final int FALL_BALL_STOCKS_SIZE = 20;

	
	// �R���X�g���N�^
	public CanvasView_2(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);

		// �`�悷��_�i�[�p���X�g����
		points = new ArrayList<Point>();

		// Paint(�M)�̐ݒ�
		paint = new Paint();
		paint.setColor(0xFF4444FF);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(3);
		
		////////////////////////////////

		// DrawableView.java

		////////////////////////////////
        // ShapeDrawable�̃C���X�^���X����
        drawables = new ShapeDrawable[HOLES_SIZE];
        fallBallDrawables = new ShapeDrawable[FALL_BALL_STOCKS_SIZE];

        // ���̕`��
        drawables[RED_OVAL] = new ShapeDrawable(new OvalShape());
        drawables[GREEN_OVAL] = new ShapeDrawable(new OvalShape());
        drawables[BLUE_OVAL] = new ShapeDrawable(new OvalShape());
        drawables[WHITE_OVAL] = new ShapeDrawable(new OvalShape());

        // ���̐F�̐ݒ�
        drawables[RED_OVAL].getPaint().setShader(
            new RadialGradient(25, 25, 20, Color.BLACK, Color.RED,
                Shader.TileMode.MIRROR));
        drawables[GREEN_OVAL].getPaint().setShader(
            new RadialGradient(25, 25, 20, Color.BLACK, Color.GREEN,
                Shader.TileMode.MIRROR));
        drawables[BLUE_OVAL].getPaint().setShader(
            new RadialGradient(25, 25, 20, Color.BLACK, Color.BLUE,
                Shader.TileMode.MIRROR));
        drawables[WHITE_OVAL].getPaint().setShader(
            new RadialGradient(25, 25, 20, Color.BLACK, Color.WHITE,
                Shader.TileMode.MIRROR));

        // �{�[���̕`��
        ballDrawables = new ShapeDrawable(new OvalShape());

        // �{�[���̐F��ݒ�(����l)
        ballDrawables.getPaint().setShader(getRandomRadialGradient());

        // �{�[���̏���\���ʒu����ʊO�ɐݒ�
        movableBallX = -DIAMETER - 1;
        movableBallY = movableBallX;
        
		
	}

	// onMeasure���\�b�h(�r���[�̃T�C�Y�ݒ菈��)
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
		
		// Log
		String msg_Log = "Dimension => set";
		Log.d("CanvasView_2.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
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
		
		// Log
		String msg_Log = "onDraw";
		Log.d("CanvasView_2.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// �L�����o�X�̔w�i�𔒂ɐݒ�
//		canvas.drawColor(Color.WHITE);
//
//		// �`�揈��
//		Point sp = new Point(-1, -1);
//		for (Point ep : points) {
//			if (sp.x >= 0) {
//				if (ep.x >= 0) {
//					canvas.drawLine(sp.x, sp.y, ep.x, ep.y, paint);
//				} else {
//					canvas.drawPoint(sp.x, sp.y, paint);
//				}
//			}
//
//			sp = ep;
//		}
		
		////////////////////////////////

		// DrawableView

		////////////////////////////////
    	//test
    	this.c = canvas;
    	
//        // ��ʕ`��̈�
//        int width = canvas.getWidth() - OFFSET_X;
//        int height = canvas.getHeight() - OFFSET_Y;
//
//        // Log
//		msg_Log = String.format("w = %d, h = %d", width, height);
//		Log.d("CanvasView_2.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//        
//        // ��ʒ����ɉ~��\��������W
//        int cX = width / 2 - RADIUS;
//        int cY = height / 2 - RADIUS;

		// Log
		msg_Log = "draw hole";
		Log.d("CanvasView_2.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		canvas.getHeight();
		
        // 4�F�̌�����ʂɕ\��
        drawColorHole(canvas, 100, 100, 50, 50);
//        drawColorHole(canvas, width, height, cX, cY);

		
	}

	public void _onDraw_DrawLine() {
		
		// Log
		String msg_Log = "_onDraw_DrawLine => started";
		Log.d("CanvasView_2.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//        drawables[RED_OVAL].setBounds(200, 200, 250, 250);
//        drawables[RED_OVAL].draw(this.c);

		DIAMETER += 10;
//		DIAMETER = 80;
		
        // Log
		msg_Log = "_onDraw_DrawLine => done";
		Log.d("CanvasView_2.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		this.invalidate();
		
	}
	
	public void _onDraw_Clear() {
		
		// Log
		String msg_Log = "_onDraw_Clear => started";
		Log.d("CanvasView_2.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//        drawables[RED_OVAL].setBounds(200, 200, 250, 250);
//        drawables[RED_OVAL].draw(this.c);
		
		DIAMETER -= 10;
//		DIAMETER = 80;
		
		// Log
		msg_Log = "_onDraw_Clear => done";
		Log.d("CanvasView_2.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		this.invalidate();
		
	}
	
	// clearDrawList���\�b�h(�N���A����)
	public void clearDrawList() {
		points.clear();
		this.invalidate();
	}


	////////////////////////////////

	// DrawableView.java

	////////////////////////////////
    private Shader getRandomRadialGradient() {

        // �O���f�[�V�����̎��
        final int type = 4;
        currentColor = new Random().nextInt(type);

        // �����_���ɃO���f�[�V�����̎�ނ�I��
        switch (currentColor) {
        case RED_OVAL:
            return RADIAL_GRADIENT_RED;

        case GREEN_OVAL:
            return RADIAL_GRADIENT_GREEN;

        case BLUE_OVAL:
            return RADIAL_GRADIENT_BLUE;

        default:
            return RADIAL_GRADIENT_WHITE;
        }
    }

    private void 
    drawColorHole
    (Canvas canvas, 
    	int width, int height, int cX, int cY) {

            // ��ʏ�[�ɐԋʂ��󂯂錊

            // �n�_(����)�̍��W
            int left = cX;
            int top = OFFSET_Y;

            // �I�_(�E��)�̍��W
            int right = cX + DIAMETER;
            int bottom = OFFSET_Y + DIAMETER;

            // ����\��
            drawables[RED_OVAL].getPaint().setShader(
                    new RadialGradient(25, 25, 20, Color.BLACK, Color.RED,
                        Shader.TileMode.MIRROR));

            drawables[RED_OVAL].setBounds(left, top, right, bottom);
            drawables[RED_OVAL].draw(canvas);

            // ��ʉE�[�ɗ΋ʂ��󂯂錊

            // �n�_(����)�̍��W
            left = width - DIAMETER;
            top = cY;

            // �I�_(�E��)�̍��W
            right = width;
            bottom = cY + DIAMETER;

            // ����\��
            drawables[GREEN_OVAL].setBounds(left, top, right, bottom);
            drawables[GREEN_OVAL].draw(canvas);

            // ��ʉ��[�ɐʂ��󂯂錊

            // �n�_(����)�̍��W
            left = cX;
            top = height - DIAMETER;

            // �I�_(�E��)�̍��W
            right = cX + DIAMETER;
            bottom = height;

            // ����\��
            drawables[BLUE_OVAL].setBounds(left, top, right, bottom);
            drawables[BLUE_OVAL].draw(canvas);

            // ��ʍ��[�ɔ��ʂ��󂯂錊

            // �n�_(����)�̍��W
            left = OFFSET_X;
            top = cY;

            // �I�_(�E��)�̍��W
            right = OFFSET_X + DIAMETER;
            bottom = cY + DIAMETER;

            // ����\��
            drawables[WHITE_OVAL].setBounds(left, top, right, bottom);
            drawables[WHITE_OVAL].draw(canvas);
            
	}//drawColorHole

}
