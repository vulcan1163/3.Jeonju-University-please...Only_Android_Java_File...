package com.example.mb_0417_Game;

import java.util.Random;

import android.graphics.Rect;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;
import com.example.mb_0417_test.SpriteAnimation;

public class Support extends SpriteAnimation {

	Rect m_BoundBox = new Rect();

	public static final int STATE_NORMAL = 0;
	public static final int STATE_OUT = 1;
	public int state = STATE_NORMAL;

	int m_target;

	Random rnd = new Random();

	public Support(int x, int y, int target) {
		super(AppManager.getInstance().getBitmap(R.drawable.bomb_4));
		this.InitSpriteDate(120, 60, 40, 4);

		m_x = x;
		m_y = y;
		m_target = target;

	}

	public void Update(long GameTime) {
		super.Update(GameTime);
		m_BoundBox.set(m_x, m_y, m_x + 60, m_y + 120);
	}

	public void move() {

		m_y += 2;

		if (m_y > 50) {
			m_y += 5;
		} else if (m_y < 50) {
			m_y += 1;
		}

		if (m_y > m_target) {
			AppManager.getInstance().m_state.m_enemy_ex.add(new Enemy_1_Ex(
					m_x - 50, m_y - 80));
			AppManager.getInstance().m_state.m_enemy2_ex.add(new Enemy_2_Ex(
					m_x - 50, m_y - 80));
			AppManager.getInstance().m_state.m_enemy3_ex.add(new Enemy_3_Ex(
					m_x - 50, m_y - 80));
			state = STATE_OUT;

		}

	}
}
