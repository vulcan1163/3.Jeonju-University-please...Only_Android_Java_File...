package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;



public class Enemy_4_Dead extends Enemy {

	public Enemy_4_Dead(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.zombie_dead_4));
		this.InitSpriteDate(140, 140, 5, 6);

		m_x = x;
		m_y = y;

		mbReply = false;
	}

	public void Update(long GameTime) { 
		super.Update(GameTime);
	}

}
