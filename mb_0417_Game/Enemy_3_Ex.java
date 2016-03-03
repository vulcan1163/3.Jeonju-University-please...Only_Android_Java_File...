package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;

public class Enemy_3_Ex extends Enemy {

	public Enemy_3_Ex(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.ex_3));
		this.InitSpriteDate(120, 120, 10, 8);
		
		m_x = x;
		m_y = y;
		 
		mbReply = false;
	}

	public void Update(long GameTime) {
		super.Update(GameTime);
		m_BoundBox.set(m_x, m_y, m_x + 200, m_y + 320);
	}
}
