package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;


public class Enemy_3_Dead extends Enemy {

	public Enemy_3_Dead(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.boss_dead));
		this.InitSpriteDate(160, 160, 10, 8);

		m_x = x;
		m_y = y;

		mbReply = false;
	}

	public void Update(long GameTime) {
		super.Update(GameTime);
	}

}
