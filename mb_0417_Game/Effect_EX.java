package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.R;
import com.example.mb_0417_test.SpriteAnimation;

public class Effect_EX extends SpriteAnimation {
	int d_x, d_y;

	public Effect_EX(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.toto));
		this.InitSpriteDate(120, 120, 40, 10);
		d_x = GameView.width / 4;
		d_y = GameView.height / 4;
		m_x = x;
		m_y = y;

		mbReply = false;
	}

	public void Update(long GameTime) {
		super.Update(GameTime);
	}

}
