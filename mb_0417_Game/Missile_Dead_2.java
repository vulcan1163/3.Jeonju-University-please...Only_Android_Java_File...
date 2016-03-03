package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;

public class Missile_Dead_2 extends Enemy {

	public Missile_Dead_2(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.enemy_dead));
		// TODO Auto-generated constructor stub
		super.InitSpriteDate(120, 120, 20, 8);

		m_x = x;
		m_y = y;

		mbReply = false;
	}

	public void Update(long GameTime) {
		super.Update(GameTime);
	}

}
