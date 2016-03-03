package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;

public class Enemy_2_Dead extends Enemy {

	public Enemy_2_Dead(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.zombie_dead_2));
		super.InitSpriteDate(140, 140, 10, 8);

		m_x = x;
		m_y = y;

		mbReply = false;
	}

	public void Update(long GameTime) {
		super.Update(GameTime);
	}

}
