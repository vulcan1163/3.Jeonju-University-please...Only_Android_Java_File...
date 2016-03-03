package com.example.mb_0417_Game;

import java.util.Random;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.R;

public class Enemy_1 extends Enemy {

	public Enemy_1(int x, int y, int i) {

		super(AppManager.getInstance().getBitmap(R.drawable.zombie01 + i));

		this.InitSpriteDate(120, 120, 10, 9);
		hp = 2;
		speed = 2.0f;
 
		m_x = x;
		m_y = y;
	}

	public void Update(long GameTime) {
		super.Update(GameTime);
		m_BoundBox.set(m_x, m_y, m_x + 120, m_y + 120);
	}

	public void move() {
 
		m_x += 2;

		if (m_x > GameView.width) {

			state = STATE_OUT;
			m_bitmap.recycle();
			m_bitmap = null;
		}
	}

	public void destroyEnemy() {
		hp--;
	}

	public int getLife() {
		return hp;
	}
}
