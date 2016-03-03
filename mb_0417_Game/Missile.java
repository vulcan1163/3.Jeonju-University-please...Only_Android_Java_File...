package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.R;

public class Missile extends Enemy {

	public Missile(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.missile));
		this.InitSpriteDate(60, 60, 10, 16);
		hp = 2;
		speed = 2.0f;

		m_x = x;
		m_y = y;
	}

	public void Update(long GameTime) {
		super.Update(GameTime);
		m_BoundBox.set(m_x, m_y, m_x + 60, m_y + 60);
	}

	public void move() {

		m_x += 4;

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
