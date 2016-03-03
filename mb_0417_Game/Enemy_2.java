package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.R;

public class Enemy_2 extends Enemy {

	public Enemy_2(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.zombie_2));
		this.InitSpriteDate(140, 140, 10, 8);
		hp = 5;
		speed = 2.0f;
		
		m_x = x;
		m_y = y;
	}

	public void Update(long GameTime) {
		super.Update(GameTime);
		m_BoundBox.set(m_x, m_y, m_x + 140, m_y + 140);
	}

	public void move() {

		m_x += 3;

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
