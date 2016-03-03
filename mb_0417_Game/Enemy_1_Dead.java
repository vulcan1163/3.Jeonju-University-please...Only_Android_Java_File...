package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;



public class Enemy_1_Dead extends Enemy {

	public Enemy_1_Dead(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.zombi_dead));
		this.InitSpriteDate(120, 120, 20, 8);

		m_x = x;
		m_y = y;

		mbReply = false;
	}

	public void Update(long GameTime) { 
		super.Update(GameTime);
	}

}
