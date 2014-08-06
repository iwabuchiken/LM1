package lm1.utils;

import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;

public class CONS_Canvas {
	
	public static class Main {

		////////////////////////////////

		// Common

		////////////////////////////////
	    public static int DIAMETER = 50;
//	    public static final int DIAMETER = 50;

	    // �~�̔��a
	    public static final int RADIUS = DIAMETER / 2;

	    ////////////////////////////////

		// Offsets

		////////////////////////////////
	    // ��ʏ㉺�̃}�[�W��
	    public static final int OFFSET_Y = 100;

	    // ��ʍ��E�̃}�[�W��
	    public static final int OFFSET_X = 0;

	    // ���ƕ`��I�u�W�F�N�g�p�z��
	    public static ShapeDrawable[] drawables;

	    ////////////////////////////////

		// Balls

		////////////////////////////////
	    // �ԋʂ��󂯂錊�̔z��ԍ�
	    public static final int RED_OVAL = 0;

	    // �΋ʂ��󂯂錊�̔z��ԍ�
	    public static final int GREEN_OVAL = 1;

	    // �ʂ��󂯂錊�̔z��ԍ�
	    public static final int BLUE_OVAL = 2;

	    // ���ʂ��󂯂錊�̔z��ԍ�
	    public static final int WHITE_OVAL = 3;

	    // �{�[���̌��݂̐F
	    public static int currentColor;

	    // �{�[���p�O���f�[�V������`
	    public static final RadialGradient RADIAL_GRADIENT_BLUE =
	        new RadialGradient(10, 10, RADIUS, Color.CYAN, Color.BLUE,
	            Shader.TileMode.MIRROR);
	    public static final RadialGradient RADIAL_GRADIENT_RED =
	        new RadialGradient(10, 10, RADIUS, Color.YELLOW, Color.RED,
	            Shader.TileMode.MIRROR);
	    public static final RadialGradient RADIAL_GRADIENT_GREEN =
	        new RadialGradient(10, 10, RADIUS, Color.WHITE, Color.GREEN,
	            Shader.TileMode.MIRROR);
	    public static final RadialGradient RADIAL_GRADIENT_WHITE =
	        new RadialGradient(10, 10, RADIUS, Color.WHITE, Color.DKGRAY,
	            Shader.TileMode.MIRROR);

	    ////////////////////////////////

		// test

		////////////////////////////////
		public static int RadialGradient_OFFSET_RED_X		= 0;
		public static int RadialGradient_OFFSET_RED_Y		= 0;
		
		public static int Bounds_OFFSET_RED_LEFT			= 0;
		public static int Bounds_OFFSET_RED_TOP			= 0;
		
		public static final int NUM_OF_BALLS				= 4;
		
		
		
	}//public static class Main
	
}//public class CONS
