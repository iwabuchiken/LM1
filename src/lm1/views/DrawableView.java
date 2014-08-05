package lm1.views;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

// ��ʂ̕`��N���X
public class DrawableView extends View {
	
	private Canvas c;
	
    // �~�̒��a
    private static final int DIAMETER = 50;

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

    // �R���X�g���N�^(�`��I�u�W�F�N�g����)
    public DrawableView(Context context) {
        super(context);

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

    // onDraw���\�b�h(��ʕ`�揈��)
    @Override
    protected void onDraw(Canvas canvas) {

    	//test
    	this.c = canvas;
    	
    	
        // ��ʕ`��̈�
        int width = canvas.getWidth() - OFFSET_X;
        int height = canvas.getHeight() - OFFSET_Y;

        // ��ʒ����ɉ~��\��������W
        int cX = width / 2 - RADIUS;
        int cY = height / 2 - RADIUS;

        // 4�F�̌�����ʂɕ\��
        drawColorHole(canvas, width, height, cX, cY);

        // �{�[���Ɠ����F�̌��ɏd�Ȃ�����
        if (drawables[currentColor].getBounds().contains(movableBallX,
            movableBallY)) {
            // �X�g�b�N�ɋ󂫂��Ȃ��ꍇ
            if (fallCount >= fallBallDrawables.length) {

                // �ߋ��̃X�g�b�N������
                for (@SuppressWarnings("unused")
                Drawable dr : fallBallDrawables) {
                    dr = null;
                }

                // ���ɗ������񐔂����Z�b�g
                fallCount = 0;
            }

            // �������{�[�����X�g�b�N
            fallBallDrawables[fallCount] = ballDrawables;

            // ���ɗ������񐔂𑝉�
            fallCount++;

            // �V�����{�[���𐶐�
            ballDrawables = new ShapeDrawable(new OvalShape());

            // �V�����F��ݒ�
            ballDrawables.getPaint().setShader(getRandomRadialGradient());

            // �{�[���̕\���ʒu����ʒ����ɃZ�b�g
            movableBallX = cX;
            movableBallY = cY;
        }

        // �{�[������ʂɕ\��
        drawMovableBall(canvas, width, height, cX, cY);

        // �������{�[���̃X�g�b�N��\��
        drawFallBall(canvas, width);
    }

    public void _onDraw_Background() {
    	
    	this.setBackgroundColor(Color.YELLOW);
    	
    }
    
    // drawMovableBall���\�b�h(�{�[���̉�ʕ`�揈��)
    private void drawMovableBall(Canvas canvas, int width, int height, int cX,
        int cY) {

        // �{�[������ʊO�ɍs�����ꍇ�̏���
        if (movableBallX < -DIAMETER || width < movableBallX
            || movableBallY < -DIAMETER || height < movableBallY) {

            // �\���ʒu����ʒ����ɃZ�b�g
            movableBallX = cX;
            movableBallY = cY;
        }

        // �{�[����\��
        ballDrawables.setBounds(movableBallX, movableBallY, movableBallX
            + DIAMETER, movableBallY + DIAMETER);
        ballDrawables.draw(canvas);

    }

    // drawFallBall���\�b�h(�������{�[���̃X�g�b�N�`�揈��)
    private void drawFallBall(Canvas canvas, int width) {
        int x = 0;
        int y = 0;
        // �X�g�b�N�����{�[������ʍ��ォ��E���ɕ\��
        for (int i = 0; i < fallCount; i++) {
            // ��ʕ��܂ŒB�����ꍇ
            if (x + RADIUS >= width) {
                // 1���̗�̍��[����\��
                x = 0;
                y += RADIUS;
            }

            // �������{�[����\��
            fallBallDrawables[i].setBounds(x, y, x + RADIUS, y + RADIUS);
            fallBallDrawables[i].draw(canvas);

            // ���̗������{�[����\������ʒu
            x += RADIUS;
        }
    }

    // drawColorHole���\�b�h(�{�[�����󂯂�4�F�̌��̕`�揈��)
    private void drawColorHole(Canvas canvas, int width, int height, int cX,
        int cY) {

        // ��ʏ�[�ɐԋʂ��󂯂錊

        // �n�_(����)�̍��W
        int left = cX;
        int top = OFFSET_Y;

        // �I�_(�E��)�̍��W
        int right = cX + DIAMETER;
        int bottom = OFFSET_Y + DIAMETER;

        // ����\��
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
    }


    // getRandomRadialGradient���\�b�h(�����_���O���f�[�V��������)
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

    // effectAccelaration���\�b�h(�����x�̉e���̔��f����)
    public void effectAccelaration(float x, float y, float z) {
        // �[���E������󂯂�����x�𔽉f
        movableBallX -= x * 2;

        // �[���㑤����󂯂�����x�𔽉f
        movableBallY += y * 2;
    }
}
