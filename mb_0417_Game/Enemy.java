package com.example.mb_0417_Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.mb_0417_test.SpriteAnimation;

public class Enemy extends SpriteAnimation {

	Rect m_BoundBox = new Rect();

	public static final int STATE_NORMAL = 0;
	public static final int STATE_OUT = 1;

	public int state = STATE_NORMAL;

	public static final int MOVE_PATTERN_1 = 0;
	public static final int MOVE_PATTERN_2 = 1;
	public static final int MOVE_PATTERN_3 = 2;

	public int movetype;

	public long LastRegen = System.currentTimeMillis();

	public int hp;
	public float speed;

	public int _rad, rad;
	
	public Enemy(Bitmap bitmap) {
		super(bitmap);
	}

	public void move() {
	}

	public void Attack() {
	}

}
