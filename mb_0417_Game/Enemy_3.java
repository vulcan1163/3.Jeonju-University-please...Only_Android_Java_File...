package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;

import com.example.mb_0417_test.R;

public class Enemy_3 extends Enemy {
	public int a = 2;

	public Enemy_3(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.boss));
		this.InitSpriteDate(200, 200, 5, 4);
		hp = 3;
		speed = 2.0f;
		
		m_x = x;
		m_y = y;
	} 

	public void Update(long GameTime) {
		super.Update(GameTime);
		m_BoundBox.set(m_x, m_y, m_x + 200, m_y + 200);
	}

	public void move() {
 
		m_x += a;

		if (m_x > 5)
			a = 0;
	}
	
	public void destroyEnemy() {
		hp--;
	}

	public int getLife() {
		return hp;
	}
}
